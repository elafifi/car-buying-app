# 🚗 Car Buying App

This is a **Spring Boot** backend application for managing car buying requests, offers from suppliers, and inspections by inspection companies.

---

## 📋 Features

- Customers create car purchase requests (imported or local cars).
- Suppliers submit one offer per customer request.
- Inspection companies inspect cars upon request.
- Status updates for customer requests and offers.
- Pagination and filtering.
- Supports large-scale data (+100k requests and +50 offers per request).
- Database migrations with Flyway.

---

## 🛠 Tech Stack

- **Spring Boot 3.4.5**
- **PostgreSQL 15**
- **Docker / Docker Compose**
- **Flyway**
- **Maven**
- **JPA / Hibernate**

---

## 🧩 Prerequisites

- **JDK 17+**
- **Maven 3.8+**
- **Docker installed**
- **Postman (for testing APIs)**

---

## 🚀 Setup & Run Instructions

### 1. Clone the Project

```bash
git clone https://github.com/your-username/car-buying-app.git
cd car-buying-app


2. Start PostgreSQL Database (Docker)
```docker run -d \
  --name carbuyingdb \
  -e POSTGRES_USER=car_user \
  -e POSTGRES_PASSWORD=car_pass \
  -e POSTGRES_DB=carbuyingdb \
  -p 5432:5432 \
  postgres:15.12```


3. Configure application.properties
Edit src/main/resources/application.properties:
```
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/carbuyingdb
spring.datasource.username=car_user
spring.datasource.password=car_pass
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Flyway
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

4. Build the Project
mvn clean install

5. Run the Application
mvn spring-boot:run

the app will be on
http://localhost:8080

Action | Method | URL
Create Customer Request | POST | /api/customer-requests
List Customer Requests (with filters) | GET | /api/customer-requests?page=0&size=10&status=PENDING
Update Customer Request Status | PATCH | /api/customer-requests/{id}/status?status=APPROVED
Supplier Submit Offer | POST | /supplier-offers
List Offers for Customer Request | GET | /supplier-offers/by-request/{requestId}
List Offers for Supplier | GET | /supplier-offers/by-supplier/{supplierId}


