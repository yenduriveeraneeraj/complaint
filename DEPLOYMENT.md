# 🚀 Deployment Guide — Online Complaint Management System

This guide provides instructions for deploying the **Online Complaint Management System** (Spring Boot Backend + React Frontend + MySQL 8 Database) locally or to production environments using **Docker**, **Executable JAR**, or **Cloud Providers**.

---

## 📦 Production Build Artifacts

The system has been compiled and packaged into production-ready artifacts:
- **Backend Production Executable JAR**: [`backend/target/complaint-management-1.0.0.jar`](file:///d:/online%20management%20system/backend/target/complaint-management-1.0.0.jar)
- **Frontend Production Web Assets**: [`frontend/dist/`](file:///d:/online%20management%20system/frontend/dist)

---

## 🐳 Option 1: Full-Stack Deployment via Docker Compose (Recommended)

Run the entire full stack (MySQL 8, Spring Boot Backend, and Nginx React Frontend) with a single command:

```bash
docker compose up --build -d
```

### Access Ports:
- **React Web Application**: `http://localhost` (Port 80)
- **Spring Boot REST API**: `http://localhost:8080` (Port 8080)
- **MySQL Database**: `localhost:3306`

To stop all containers:
```bash
docker compose down
```

---

## ☕ Option 2: Standalone Local Production JAR Deployment

### 1. Start MySQL Database
Ensure MySQL is running on `localhost:3306` with database `complaint_management_db`.

### 2. Run Backend Executable JAR
```bash
java -jar backend/target/complaint-management-1.0.0.jar
```
Backend will be available at `http://localhost:8080`.

### 3. Serve Frontend Web Assets
Option A (Development server):
```bash
cd frontend
npm run dev
```
Option B (Static file server):
```bash
npx serve -s frontend/dist -l 5173
```

---

## ☁️ Option 3: Production Free Cloud Deployment (Vercel + Render + TiDB Cloud)

### 1. Cloud Database (TiDB Cloud Serverless MySQL)
- Create a free cluster on [TiDB Cloud](https://tidbcloud.com) (5 GB free forever, MySQL 8.0 protocol).
- Note your **Host**, **Port** (usually `4000`), **Username**, and **Password**.
- JDBC URL format:
  ```text
  jdbc:mysql://<HOST>:<PORT>/complaint_management_db?createDatabaseIfNotExist=true&useSSL=true&allowPublicKeyRetrieval=true
  ```

### 2. Backend API Deployment (Render Web Service)
- Connect repository [`complaint-backend`](https://github.com/yenduriveeraneeraj/complaint-backend) or [`complaint`](https://github.com/yenduriveeraneeraj/complaint) (Root Directory: `backend`) to [Render](https://dashboard.render.com).
- **Environment**: `Docker` (uses `Dockerfile`).
- **Health Check Path**: `/api/health`.
- Set Environment Variables:
  - `SPRING_DATASOURCE_URL`: `jdbc:mysql://<HOST>:<PORT>/complaint_management_db?createDatabaseIfNotExist=true&useSSL=true`
  - `SPRING_DATASOURCE_USERNAME`: `<your-db-user>`
  - `SPRING_DATASOURCE_PASSWORD`: `<your-db-password>`
  - `SPRING_JPA_HIBERNATE_DDL_AUTO`: `update`
  - `APP_CORS_ALLOWED_ORIGINS`: `*`
  - `JWT_SECRET`: `MySecretKeyForComplaintManagementSystemJWT2026`
  - `MAIL_ENABLED`: `false`

### 3. Frontend Web Deployment (Vercel)
- Connect repository [`complaint-frontend`](https://github.com/yenduriveeraneeraj/complaint-frontend) or [`complaint`](https://github.com/yenduriveeraneeraj/complaint) (Root Directory: `frontend`) to [Vercel](https://vercel.com).
- **Framework Preset**: `Vite`
- **Build Command**: `npm run build`
- **Output Directory**: `dist`
- Set Environment Variable:
  - `VITE_API_URL`: `https://<your-render-backend-name>.onrender.com/api`

---

## 👤 Default System Credentials

| Role | Email | Password |
| :--- | :--- | :--- |
| **System Administrator** | `admin@cms.com` | `Admin@123` |
| **Registered Citizen** | `user@cms.com` | `User@123` |
