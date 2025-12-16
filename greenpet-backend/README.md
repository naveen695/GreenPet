# GreenPet Backend (Spring Boot 3 / Java 17)

This skeleton is a starting point to migrate GreenPet to a Spring Boot REST API with JWT auth and MongoDB.

Quick start (local)
1. Start MongoDB (optional if you have a DB already):
   docker compose up -d

2. Build and run:
   mvn -DskipTests package
   java -jar target/greenpet-backend-0.0.1-SNAPSHOT.jar

Environment variables
- MONGODB_URI (defaults to mongodb://localhost:27017/greenpet)
- MAIL_HOST, MAIL_PORT, MAIL_USERNAME, MAIL_PASSWORD (for sending email)
- JWT_SECRET (set a long random secret in production)

API
- POST /api/auth/register -> register new user {name,email,password}
- POST /api/auth/login -> login {email,password} returns Bearer token
- All other endpoints require Authorization: Bearer <token>

Next steps
- Add domain models (pets, contact messages) and controllers.
- Convert existing DAOs/logic to use Spring Data and services.
- Add DTOs, validation, error handling, logging, and tests.
- Consider enabling HTTPS and secure cookie storage for tokens, or use refresh tokens.
