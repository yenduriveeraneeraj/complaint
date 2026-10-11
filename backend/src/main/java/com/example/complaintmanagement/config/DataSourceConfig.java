package com.example.complaintmanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String rawUrl;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Bean
    @Primary
    public DataSource dataSource() {
        String finalUrl = rawUrl != null ? rawUrl.trim() : "";
        String finalUser = username;
        String finalPass = password;

        // Auto-sanitize raw database URLs like:
        // mysql://username:password@host:port/database
        // or jdbc:mysql://username:password@host:port/database
        try {
            String checkUrl = finalUrl;
            if (checkUrl.startsWith("jdbc:")) {
                checkUrl = checkUrl.substring(5);
            }

            if (checkUrl.startsWith("mysql://") || checkUrl.startsWith("mariadb://")) {
                URI uri = URI.create(checkUrl);
                String userInfo = uri.getUserInfo();
                if (StringUtils.hasText(userInfo)) {
                    String[] parts = userInfo.split(":", 2);
                    if (!StringUtils.hasText(finalUser)) {
                        finalUser = parts[0];
                    }
                    if (parts.length > 1 && !StringUtils.hasText(finalPass)) {
                        finalPass = parts[1];
                    }
                }

                String host = uri.getHost();
                int port = uri.getPort() > 0 ? uri.getPort() : 3306;
                String path = uri.getPath();
                if (!StringUtils.hasText(path) || "/".equals(path)) {
                    path = "/sys";
                }
                String query = uri.getQuery();

                StringBuilder cleanJdbc = new StringBuilder("jdbc:mysql://")
                        .append(host)
                        .append(":")
                        .append(port)
                        .append(path);

                if (StringUtils.hasText(query)) {
                    cleanJdbc.append("?").append(query);
                    if (!query.contains("useSSL") && !query.contains("sslMode")) {
                        cleanJdbc.append("&useSSL=true");
                    }
                    if (!query.contains("allowPublicKeyRetrieval")) {
                        cleanJdbc.append("&allowPublicKeyRetrieval=true");
                    }
                } else {
                    cleanJdbc.append("?createDatabaseIfNotExist=true&useSSL=true&allowPublicKeyRetrieval=true");
                }

                finalUrl = cleanJdbc.toString();
                log.info("Sanitized database JDBC URL: host={}:{}, database={}", host, port, path);
            } else if (!finalUrl.startsWith("jdbc:")) {
                finalUrl = "jdbc:" + finalUrl;
            }
        } catch (Exception e) {
            log.warn("Failed to parse datasource URL as URI, using configured URL: {}", e.getMessage());
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(finalUrl);
        config.setUsername(finalUser);
        config.setPassword(finalPass);
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        return new HikariDataSource(config);
    }
}
