# Sparta Academy Full Stack Management System

A full-stack Spring Boot academy management system built using REST APIs, Spring MVC, Thymeleaf, and Spring Security.

The application allows trainers and trainees to manage academy data through both REST endpoints and a web dashboard interface.

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
| Spring Security | Authentication & Authorization |
| Thymeleaf | Server-side HTML Rendering |
| Bootstrap | Frontend Styling |
| Spring MVC | Web Layer |

---

## Project Structure

```text
src/main/java/com/sparta/apidev

├── config
├── controllers
├── webControllers
├── services
├── repositories
├── entities
├── dtos
├── enums

src/main/resources

├── templates
│   ├── trainees
│   ├── trainers
│   ├── courses
│   └── fragments
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

## Web Features

### Authentication
- Custom login page
- Spring Security integration
- BCrypt password encryption
- Role-based authorization

### Role-Based Dashboard
- Trainer dashboard
- Trainee dashboard
- Dynamic content rendering with Thymeleaf Security

### Thymeleaf Web Interface
- View trainees
- View trainers
- View courses
- Create/Edit/Delete records
- Responsive dashboard UI

## Frontend Features

### Dashboard
- Animated landing page
- Responsive card layout
- Role-based navigation
- Secure authentication

### Thymeleaf Pages
- Trainee Management Pages
- Trainer Management Pages
- Course Management Pages
- Custom Login Page

## Security

Spring Security is implemented to provide:

- Authentication
- Authorization
- Role-based access control
- Secure password encryption using BCrypt

### Roles

| Role | Permissions |
|---|---|
| TRAINER | Full CRUD access |
| TRAINEE | View-only trainee access |

### Login

The application uses a custom Thymeleaf login page.

Default demo credentials:

```text
Username: cfrench@spartaglobal.com
Password: password
```

## Web Layer

The application includes a full MVC web layer using:

- Spring MVC
- Thymeleaf
- Bootstrap

### Web Controllers

```text
/trainees
/trainers
/courses
```

### REST API Controllers

```text
/api/trainees
/api/trainers
/api/courses
```

## API Documentation
**Swagger UI:**
(http://localhost:8080/swagger-ui.html)

## API Documentation
**OpenAPI JSON:**
(http://localhost:8080/v3/api-docs)

## Database Design
**Entities**
- Trainer
- Trainee
- Course
  
**Relationships**

- One Trainer → Many Courses
- Many Trainees ↔ Many Courses

## Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/your-username/project-name.git
```

---

### 2. Open Project

Open the project in your preferred IDE:
- IntelliJ IDEA
- VS Code
- Eclipse

---

### 3. Configure Database

Update the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/academy
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 4. Install Dependencies

```bash
mvn clean install
```

---

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application should start on:

```text
http://localhost:8080
```

---

## Example Endpoints

### Get All Courses

```http
GET /courses
```

---

### Get Course By ID

```http
GET /courses/{id}
```

---

### Create Course

```http
POST /courses
```

#### Request Body

```json
{
  "courseName": "Java Fundamentals",
  "description": "Introduction to Java",
  "duration": 12
}
```

---

### Update Course

```http
PUT /courses/{id}
```

---

### Delete Course

```http
DELETE /courses/{id}
```

---

### Enrol Trainee

```http
POST /courses/{courseId}/enrol/{traineeId}
```

---

### Remove Trainee From Course

```http
DELETE /courses/{courseId}/trainees/{traineeId}
```

---

## Validation

The application uses validation annotations to ensure data integrity.

### Example

```java
@NotBlank(message = "Course name is required")
private String courseName;

@Email(message = "Invalid email format")
private String email;
```

### Validation Features
- Required fields
- Email validation
- Length restrictions
- Positive number checks

---

## Error Handling

Centralized exception handling is implemented using:

```java
@ControllerAdvice
```

### Common HTTP Status Codes

| Status Code | Description |
|---|---|
| 200 | OK |
| 201 | Created |
| 400 | Bad Request |
| 404 | Not Found |
| 409 | Conflict |
| 500 | Internal Server Error |

### Example Error Response

```json
{
  "timestamp": "2026-05-08T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Course not found",
  "path": "/courses/100"
}
```

---

## DTO Architecture

This project uses DTOs (Data Transfer Objects) to separate:
- Internal entity structure
- External API responses

### Benefits
- Cleaner API responses
- Better security
- Easier validation
- Improved maintainability

### Example DTOs

```text
CourseRequestDTO
CourseResponseDTO
TrainerRequestDTO
TrainerResponseDTO
```

---

## Repository Layer

Repositories extend `JpaRepository`.

### Example

```java
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseNameContaining(String name);

}
```

### Features
- CRUD operations
- Custom repository methods
- Database abstraction

---

## Service Layer

The service layer contains the application business logic.

### Responsibilities
- DTO mapping
- Validation
- Entity management
- Business rules
- Exception handling

### Example Methods

```java
createCourse()
updateCourse()
deleteCourse()
enrolTrainee()
removeTrainee()
```

---

## Controller Layer

Controllers expose REST endpoints to clients.

### Example Annotations

```java
@RestController
@RequestMapping("/courses")
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
```

---

## Entity Relationships

### Trainer → Course

```java
@OneToMany(mappedBy = "trainer")
private List<Course> courses;
```

### Course ↔ Trainee

```java
@ManyToMany
@JoinTable(
    name = "course_trainees",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "trainee_id")
)
private List<Trainee> trainees;
```

---

## Swagger Documentation

Swagger provides:
- Interactive API testing
- API endpoint documentation
- Request examples
- Response examples

### Swagger UI

```text
http://localhost:8080/swagger-ui.html
```

### OpenAPI JSON

```text
http://localhost:8080/v3/api-docs
```

---

## Unit Testing

Unit tests are implemented using:
- JUnit 5
- Mockito

### Testing Goals
- Verify service logic
- Validate business rules
- Mock repositories
- Test exception handling

### Example Test

```java
@Test
void shouldCreateCourseSuccessfully() {

    when(courseRepository.save(any(Course.class)))
            .thenReturn(course);

    CourseResponseDTO result = courseService.createCourse(requestDTO);

    assertNotNull(result);
}
```

---

## GitHub Workflow

This project follows collaborative GitHub development practices.

### Branch Strategy

```text
main
trainer-feature
trainee-feature
course-feature
enrolment-feature
```

### Development Workflow

1. Pull latest changes
2. Create feature branch
3. Develop feature
4. Commit changes
5. Push branch
6. Open Pull Request
7. Review and merge

---

## Git Commands

### Clone Repository

```bash
git clone https://github.com/your-username/project-name.git
```

### Create Branch

```bash
git checkout -b feature-name
```

### Add Changes

```bash
git add .
```

### Commit Changes

```bash
git commit -m "Added course controller"
```

### Push Changes

```bash
git push origin feature-name
```

---

## Scrum Board

GitHub Project Board Columns:
- Project Backlog
- Sprint Backlog
- In Progress
- In Review
- Completed
- Notes

### Definition of Done

A task is considered complete when:
- Code compiles successfully
- Feature works correctly
- Validation implemented
- Tests pass
- Pull request reviewed
- Documentation updated

---

## Continuous Integration

Optional GitHub Actions workflow for:
- Automatic builds
- Running tests
- Pull request validation

### Example Workflow

```yaml
name: Java CI

on:
  push:
    branches: [ main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '21'

      - name: Build with Maven
        run: mvn clean install
```

---

## Screenshots

### Login Page
<img width="1881" height="855" alt="image" src="https://github.com/user-attachments/assets/4f1c9752-0d67-4e23-85ff-c03962bed9c9" />

### Dashboard
<img width="1914" height="889" alt="image" src="https://github.com/user-attachments/assets/ee4faa36-94c5-4933-addf-dc670a93073d" />

### Trainee Management
<img width="1880" height="874" alt="image" src="https://github.com/user-attachments/assets/0190437b-8948-4eab-af4a-a5d73e249735" />

### Course Management
<img width="1906" height="730" alt="image" src="https://github.com/user-attachments/assets/d5a4da74-99c6-4839-96fc-8bb58208131b" />

### Swagger Documentation
<img width="1914" height="886" alt="image" src="https://github.com/user-attachments/assets/e75a4447-1de3-4d35-9200-88516ee3f8e9" />

---

## Future Improvements

- Authentication & Authorization
- Docker Support
- Pagination
- Search & Filtering
- Deployment
- Role-based access control

---

## Lessons Learned

Through this project, the team gained experience with:
- REST API development
- Spring Boot architecture
- DTO patterns
- Unit testing
- Git collaboration
- Agile teamwork
- Swagger documentation

---

## Team Members

| Name | Responsibility |
|---|---|
| Mo | README & Documentation |
| Member 2 | Trainer Module |
| Member 3 | Trainee Module |
| Member 4 | Course Module |
| Member 5 | Enrolment & Testing |

---

## Acknowledgements

Special thanks to:
- Team contributors

---



