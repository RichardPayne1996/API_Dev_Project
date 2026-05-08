# Sparta Academy REST API

A production-ready REST API built using Spring Boot for managing trainers, trainees, and courses within Sparta Academy.

---

## Project Overview

This project was developed as part of the Sparta Global Academy API Development Mini Project.

The application allows academy administrators and trainers to manage:
- Trainers
- Trainees
- Courses
- Course enrolments

The API follows RESTful principles and includes:
- Full CRUD functionality
- DTO architecture
- Validation
- Exception handling
- Swagger/OpenAPI documentation
- Unit testing with Mockito and JUnit

---

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Web | REST API Development |
| Spring Data JPA | Database Access |
| MySQL | Database |
| Maven | Dependency Management |
| Swagger/OpenAPI | API Documentation |
| JUnit 5 | Unit Testing |
| Mockito | Mock Testing |
| Git & GitHub | Version Control |

---

## Project Structure

```text
src/main/java/com/sparta/academy

├── controllers
├── services
├── repositories
├── entities
├── dtos
├── exceptions
├── configs
```

## Features
**Trainers**
- Create trainer
- View trainers
- Update trainer
- Delete trainer
- View assigned courses
  
**Trainees**
- Create trainee
- View trainees
- Update trainee
- Delete trainee
  
**Courses**
- Create course
- View courses
- Update course
- Delete course
- Enrol trainees
- Remove trainees from courses

