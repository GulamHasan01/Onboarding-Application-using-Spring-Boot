# Onboarding-Application-using-Spring-Boot

# 🛠️ Onboarding Application – Backend

This is the **Spring Boot** backend for the Onboarding Application. It handles **user authentication**, **preference management**, and **profile operations**, using **JWT** for secure access and **MySQL** as the database.

---

## 📌 Features

- ✅ User Registration and Login
- 🔐 JWT-based Authentication & Authorization
- 👤 User Profile Management
- ⚙️ Onboarding Preferences API
- 🧪 Input Validation & Error Handling
- 💾 MySQL Database Integration
- 🧱 Modular Spring Boot Structure

---

## 🛠️ Tech Stack

| Layer    | Technology                  |
|----------|-----------------------------|
| Language | Java 17+                    |
| Framework| Spring Boot (Spring Web, Security, JPA) |
| Auth     | JWT (JSON Web Tokens)       |
| DB       | MySQL                       |
| ORM      | Hibernate / JPA             |
| Tools    | Maven, Postman              |

---

## 📁 Project Structure

src/main/java/com/gulamhasan/onboarding_app/
├── config/ # Security configuration
│ └── SecurityConfig.java
│
├── controller/ # REST API Controllers
│ ├── AuthenticationController.java
│ ├── DashboardController.java
│ └── PreferencesController.java
│
├── model/ # Entity classes
│ ├── Preferences.java
│ └── User.java
│
├── repository/ # JPA Repositories
│ ├── PreferencesRepository.java
│ └── UserRepository.java
│
├── security/ # JWT and Auth utilities
│ ├── CustomUserDetailsService.java
│ ├── JwtFilter.java
│ └── JwtUtil.java
│
├── service/ # Business Logic
│ ├── DashboardService.java
│ ├── PreferencesService.java
│ └── UserService.java
│
└── OnboardingApplication.java # Main app entry


---

## 🔐 Authentication Flow (JWT)

- **Registration**: `/api/auth/register`
- **Login**: `/api/auth/login`
- **JWT Token** returned on login
- **Authorization**: Secured endpoints require the token via `Authorization: Bearer <token>`

---

## 📬 API Endpoints Overview

### 🧾 Authentication
| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| POST   | `/api/auth/register` | User Registration        |
| POST   | `/api/auth/login`    | Login and JWT issuance   |

### 👤 Preferences
| Method | Endpoint               | Description                 |
|--------|------------------------|-----------------------------|
| POST   | `/api/preferences`     | Save onboarding preferences |
| GET    | `/api/preferences`     | Get user's preferences      |

### 📊 Dashboard
| Method | Endpoint               | Description                |
|--------|------------------------|----------------------------|
| GET    | `/api/dashboard`       | Return user dashboard data |

---

## ⚙️ Setup Guide

### ✅ Prerequisites
- Java 17+
- Maven
- MySQL
- Postman (optional, for testing)

### 📦 Configuration

In `src/main/resources/application.properties`:

```properties
# MySQL Config
spring.datasource.url=jdbc:mysql://localhost:3306/onboarding
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=mysecretkey12345
jwt.expiration=86400000

 Running the Application
bash
./mvnw spring-boot:run

📌 Highlights
Modular and scalable architecture

Token-based stateless authentication

Clean separation of concerns: controller → service → repository

Validations and exception handling

Ready for integration with React frontend

📬 Sample Headers (for protected APIs)
http
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
🧑‍💻 Author
Gulam Hasan
🔗 LinkedIn
💻 GitHub
✉️ Email: ghulamhasan665@gmail.com

📜 License
This project is licensed under the MIT License.

yaml

---

Let me know if you also want:
- Sample request/response JSON
- Swagger integration
- `.env` and `application-dev.properties` setup
- Dockerfile for containerization

Would you like a Postman collection exported with all endpoints too?







