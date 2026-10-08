package com.example.qa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserApiRestAssuredTestHiLev extends BaseTest {

    private int createdUserId;

    @Test
    void testCreateUser() {

        String email = generateEmail();

        String requestBody = """
                {
                    "name": "Automation User",
                    "email": "%s"
                }
                """.formatted(email);

        createdUserId =
                given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .body("email", equalTo(email))
                    .extract()
                    .path("id");
    }

    @Test
    void testCreateUserWithInvalidEmail() {

        String requestBody = """
                {
                    "name": "Invalid User",
                    "email": "invalid-email"
                }
                """;

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(400);
    }

    @Test
    void testGetUsers() {

        given()
            .spec(requestSpec)
        .when()
            .get("/users")
        .then()
            .statusCode(200);
    }

    @Test
    void testGetNonExistingUser() {

        given()
            .spec(requestSpec)
        .when()
            .get("/users/99999")
        .then()
            .statusCode(404);
    }

    @Test
    void testUpdateUser() {

        String email = generateEmail();

        String createBody = """
                {
                    "name": "Before Update",
                    "email": "%s"
                }
                """.formatted(email);

        createdUserId =
                given()
                    .spec(requestSpec)
                    .body(createBody)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("id");

        String updateBody = """
                {
                    "name": "After Update",
                    "email": "updated%s@gmail.com"
                }
                """.formatted(System.currentTimeMillis());

        given()
            .spec(requestSpec)
            .body(updateBody)
        .when()
            .put("/users/" + createdUserId)
        .then()
            .statusCode(200)
            .body("name", equalTo("After Update"));
    }

    @Test
    void testDeleteUser() {

        String email = generateEmail();

        String requestBody = """
                {
                    "name": "Delete Test User",
                    "email": "%s"
                }
                """.formatted(email);

        createdUserId =
                given()
                    .spec(requestSpec)
                    .body(requestBody)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("id");

        given()
        .when()
            .delete("/users/" + createdUserId)
        .then()
            .statusCode(204);
    }
    @Test
    void testDeleteNonExistingUser() {

        given()
        .when()
            .delete("/users/99999")
        .then()
            .statusCode(404);
    }

    @AfterEach
    void cleanup() {

        if (createdUserId > 0) {

            given()
            .when()
                .delete("/users/" + createdUserId);
        }

        createdUserId = 0;
    }
    @Test
    void testCreateUserWithoutName() {

        String email = generateEmail();

        String requestBody = """
                {
                    "email": "%s"
                }
                """.formatted(email);

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(400);
    }
    @Test
    void testCreateUserWithoutEmail() {

        String requestBody = """
                {
                    "name": "Missing Email User"
                }
                """;

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(400);
    }
    @Test
    void testCreateDuplicateUser() {

        String email = generateEmail();

        String firstUser = """
                {
                    "name": "First User",
                    "email": "%s"
                }
                """.formatted(email);

        createdUserId =
                given()
                    .spec(requestSpec)
                    .body(firstUser)
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("id");

        String duplicateUser = """
                {
                    "name": "Second User",
                    "email": "%s"
                }
                """.formatted(email);

        given()
            .spec(requestSpec)
            .body(duplicateUser)
        .when()
            .post("/users")
        .then()
            .statusCode(400);
    }
}
