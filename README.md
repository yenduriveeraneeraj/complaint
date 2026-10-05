# 🎯 Online Complaint Management System

> A complete full-stack web application for managing complaints efficiently.
> Built with **React.js** frontend and **Java Spring Boot** backend.

---

## 📌 Project Overview

The Online Complaint Management System allows:
- **Users** to submit complaints, track their status, and receive notifications
- **Admins** to manage complaints, assign departments/staff, and generate reports

---

## ✨ Features

### 👤 User Features
- Register & Login with secure authentication (JWT)
- Submit complaints with file attachments
- Track complaint status in real-time
- Receive notifications on status changes
- View complaint history
- Provide feedback after resolution

### 🔧 Admin Features
- Comprehensive dashboard with charts
- Manage users, departments, and staff
- Assign complaints to departments/staff
- Update complaint status with remarks
- Generate and export reports (CSV)
- View and manage feedback

---

## 🛠️ Technology Stack

| Layer       | Technology                        |
|-------------|-----------------------------------|
| Frontend    | React.js 18, Vite, Bootstrap 5    |
| Backend     | Java 17, Spring Boot 3.2          |
| Database    | MySQL 8                           |
| ORM         | Hibernate / Spring Data JPA       |
| Security    | Spring Security + JWT             |
| Charts      | Recharts                          |
| HTTP Client | Axios                             |
| Build Tool  | Maven (Backend), npm (Frontend)   |

---

## 🏗️ Architecture

```
React Frontend (port 5173)
        |
        | HTTP/REST API (JSON)
        ↓
Spring Boot Backend (port 8080)
        |
    ┌───┴───┐
    │ Controller Layer │  ← Handles HTTP requests
    │ Service Layer    │  ← Business logic
    │ Repository Layer │  ← Database queries
    │ Entity/JPA Layer │  ← ORM mapping
    └───────────────────
        |
    MySQL Database (port 3306)
```

---

## 🗄️ Database Setup

1. Install MySQL 8 on your machine
2. Open MySQL Workbench or command line
3. The database `complaint_management_db` will be **created automatically** on first run
4. Update `application.properties` with your MySQL credentials:

```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## ⚙️ Backend Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8

### Steps
```bash
# Navigate to backend folder
cd backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Backend starts at: `http://localhost:8080`

---

## 🖥️ Frontend Setup

### Prerequisites
- Node.js 18+
- npm 9+

### Steps
```bash
# Navigate to frontend folder
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev
```

Frontend starts at: `http://localhost:5173`

---

## 🔑 Sample Credentials (Development Only)

> ⚠️ These are development credentials only. Change before production!

| Role  | Email                    | Password   |
|-------|--------------------------|------------|
| Admin | admin@complaint.com      | Admin@123  |
| User  | user@complaint.com       | User@123   |

---

## 📡 API Documentation

| Method | Endpoint                         | Description              | Auth     |
|--------|----------------------------------|--------------------------|----------|
| POST   | /api/auth/register               | Register new user        | Public   |
| POST   | /api/auth/login                  | Login                    | Public   |
| GET    | /api/complaints                  | Get user's complaints    | USER     |
| POST   | /api/complaints                  | Submit complaint         | USER     |
| GET    | /api/complaints/{id}             | Get complaint details    | USER     |
| GET    | /api/admin/complaints            | Get all complaints       | ADMIN    |
| PUT    | /api/admin/complaints/{id}/assign| Assign complaint         | ADMIN    |
| PUT    | /api/admin/complaints/{id}/status| Update status            | ADMIN    |
| GET    | /api/departments                 | Get departments          | AUTH     |
| POST   | /api/departments                 | Add department           | ADMIN    |
| GET    | /api/staff                       | Get staff                | AUTH     |
| POST   | /api/staff                       | Add staff                | ADMIN    |
| GET    | /api/notifications               | Get notifications        | USER     |
| PUT    | /api/notifications/{id}/read     | Mark as read             | USER     |
| POST   | /api/feedback                    | Submit feedback          | USER     |
| GET    | /api/admin/reports               | Get reports              | ADMIN    |

---

## 📁 Project Structure

```
online-complaint-management-system/
│
├── backend/                              ← Spring Boot App
│   ├── src/main/java/com/example/complaintmanagement/
│   │   ├── controller/                   ← REST API controllers
│   │   ├── service/                      ← Business logic
│   │   ├── repository/                   ← Database queries (JPA)
│   │   ├── entity/                       ← Database table models
│   │   ├── dto/                          ← Data Transfer Objects
│   │   ├── security/                     ← JWT & Spring Security
│   │   ├── exception/                    ← Global error handling
│   │   └── config/                       ← CORS & app configuration
│   └── pom.xml                           ← Maven dependencies
│
└── frontend/                             ← React App (Vite)
    ├── src/
    │   ├── components/
    │   │   ├── common/                   ← Reusable UI components
    │   │   └── layout/                   ← Sidebar, Navbar etc.
    │   ├── pages/
    │   │   ├── auth/                     ← Login, Register pages
    │   │   ├── user/                     ← User dashboard, complaints
    │   │   └── admin/                    ← Admin dashboard, management
    │   ├── services/                     ← Axios API calls
    │   ├── context/                      ← React Context (auth state)
    │   ├── hooks/                        ← Custom React hooks
    │   └── utils/                        ← Helper functions
    └── package.json
```

---

## 🚀 How to Run (Both Together)

### Terminal 1 — Backend
```bash
cd backend
mvn spring-boot:run
```

### Terminal 2 — Frontend
```bash
cd frontend
npm run dev
```

Open your browser at: **http://localhost:5173**

---

## 🔮 Future Enhancements

- Email notifications via Spring Mail
- Mobile application (React Native)
- Real-time updates using WebSockets
- Admin analytics with AI insights
- Multi-language support
- Dark mode toggle
- Two-factor authentication (2FA)

---

## 🏆 Project Info

- **Type**: College Full-Stack Project
- **Tech Stack**: Java Spring Boot + React.js
- **Database**: MySQL 8
- **Purpose**: Complaint Management for institutions

---

*Built with ❤️ for college project demonstration*
