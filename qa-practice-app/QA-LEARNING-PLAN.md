# Practical QA Learning Plan

## Phase 1 - Understand the application
Run the Spring Boot app in Eclipse and learn each endpoint.

## Phase 2 - Manual API testing
Use Postman to execute QA-TEST-CASES.md.

Learn:
- HTTP methods
- Status codes
- Request/response
- Headers
- JSON
- Positive and negative testing

## Phase 3 - Test design
For each field apply:
- Boundary Value Analysis
- Equivalence Partitioning
- Null/blank testing
- Invalid format testing

Examples:
Name: minimum 3, maximum 100
Quantity: minimum 0, maximum 10000
Price: minimum 0.01

## Phase 4 - Database testing
Open:
http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:qadb

Username:
sa

Password:
leave blank

Run SQL:
SELECT * FROM PRODUCTS;
SELECT COUNT(*) FROM PRODUCTS;
SELECT * FROM PRODUCTS WHERE QUANTITY < 0;

## Phase 5 - Defect lifecycle
Find a defect -> create bug report -> developer fix -> retest -> regression -> close.

## Phase 6 - Automation
Next build:
- Selenium UI automation
- JUnit/TestNG
- REST Assured
- Maven test execution
- Git
- Jenkins/CI

## Phase 7 - Interview readiness
Practice explaining:
- SDLC vs STLC
- Severity vs Priority
- Smoke vs Sanity
- Regression vs Retesting
- Test case vs test scenario
- Bug life cycle
- API testing
- SQL validation
