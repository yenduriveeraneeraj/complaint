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

## ☁️ Option 3: Cloud Deployment (Render / Railway / AWS / Heroku)

### Backend Deployment (Spring Boot):
1. Push project repository to GitHub/GitLab.
2. Connect repository to **Render**, **Railway**, or **AWS Elastic Beanstalk**.
3. Set **Build Command**: `./mvnw package -DskipTests` (or use [`backend/Dockerfile`](file:///d:/online%20management%20system/backend/Dockerfile)).
4. Set **Start Command**: `java -jar target/complaint-management-1.0.0.jar`.
5. Set Environment Variables:
   - `SPRING_DATASOURCE_URL`: `jdbc:mysql://<your-db-host>:3306/complaint_management_db`
   - `SPRING_DATASOURCE_USERNAME`: `<db-user>`
   - `SPRING_DATASOURCE_PASSWORD`: `<db-password>`

### Frontend Deployment (React):
1. Connect repository to **Vite / Vercel / Netlify**.
2. Set **Root Directory**: `frontend`.
3. Set **Build Command**: `npm run build`.
4. Set **Publish Directory**: `dist`.

---

## 👤 Default System Credentials

| Role | Email | Password |
| :--- | :--- | :--- |
| **System Administrator** | `admin@cms.com` | `Admin@123` |
| **Registered Citizen** | `user@cms.com` | `User@123` |
