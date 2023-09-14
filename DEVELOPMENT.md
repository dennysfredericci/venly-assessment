# Venly Assessment

### How to Build

On the root folder run:

```bash
mvn clean package
```

### How to Run

On the root folder run:

```bash
mvn spring-boot:run
```

Or run the main class io.venly.assessment.VenlyAssessmentApplication

### Manual Testing

You can use the Swagger UI to test the endpoints.

http://localhost:8080/swagger-ui/index.html

The file src/main/resource/import.sql will be executed during the startup to facilitate your manual tests.
If you don't want to use it, you can remove the file.

### Backlog

There are some improvements that could be done:

1. Implement the test for Task 7 / 8
2. Add pagination for the endpoint that returns a list.
3. Optimize error handling with @ControllerAdvice
4. Time is almost over... git push!!

