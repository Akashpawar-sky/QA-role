# QA Practice Test Cases

Start with these manually in Postman.

## Product API

| ID | Scenario | Expected |
|---|---|---|
| TC-001 | GET all products | 200 OK |
| TC-002 | GET product by valid ID | 200 OK |
| TC-003 | GET product by invalid ID | 404 |
| TC-004 | Create valid product | 201 |
| TC-005 | Create with blank name | 400 |
| TC-006 | Create with 2-char name | 400 |
| TC-007 | Create with price 0.00 | 400 |
| TC-008 | Create with negative quantity | 400 |
| TC-009 | Create with quantity 10000 | 201 |
| TC-010 | Create with quantity 10001 | 400 |
| TC-011 | Category lowercase | 400 |
| TC-012 | Update existing product | 200 |
| TC-013 | Update non-existing product | 404 |
| TC-014 | Delete existing product | 204 |
| TC-015 | Search by name | 200 |
| TC-016 | Search by category | 200 |

## QA exercises
1. Execute every test case.
2. Record actual result and Pass/Fail.
3. Create a bug report for every failure.
4. Repeat TC-001 through TC-016 after changes as regression testing.
5. Validate database data through the H2 console.
