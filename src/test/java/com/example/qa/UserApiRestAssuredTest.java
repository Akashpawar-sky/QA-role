
package com.example.qa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class UserApiRestAssuredTest {

    @Test
    void testCreateUser() {

        String requestBody = """
                {
                    "name": "REST Assured User",
                    "email": "restassured234@gmail.com"
                }
                """;

        given()
            .baseUri("http://localhost:8080")
            .contentType("application/json")
            .body(requestBody)

        .when()
            .post("/users")

        .then()
            .statusCode(200)
            .body("name", equalTo("REST Assured User"))
            .body("email", equalTo("restassured234@gmail.com"));
    }
    @Test
    void testGetUsers() {

        given()
            .baseUri("http://localhost:8080")

        .when()
            .get("/users")

        .then()
            .statusCode(200);
    }
    @Test
    void testInvalidEmail() {

        String requestBody = """
                {
                    "name": "Test User",
                    "email": "invalid-email"
                }
                """;

        given()
            .baseUri("http://localhost:8080")
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(400);
    }
    @Test
    void testGetNonExistingUser() {

        given()
            .baseUri("http://localhost:8080")

        .when()
            .get("/users/99999")

        .then()
            .statusCode(404);
    }
    
    @Test
    void testUpdateUser() {

        String requestBody = """
                {
                    "name": "Updated REST User",
                    "email": "updatedrest@gmail.com"
                }
                """;

        given()
            .baseUri("http://localhost:8080")
            .contentType("application/json")
            .body(requestBody)
        .when()
            .put("/users/2")
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated REST User"))
            .body("email", equalTo("updatedrest@gmail.com"));
    }
    @Test
    void testDeleteUser() {

        given()
            .baseUri("http://localhost:8080")
        .when()
            .delete("/users/2")
        .then()
            .statusCode(204);
    }
    @Test
    void testCompleteUserFlow() {

        String requestBody = """
                {
                    "name": "Automation Flow User",
                    "email": "flowuser@gmail.com"
                }
                """;

        // 1. CREATE
        int userId =
            given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(requestBody)
            .when()
                .post("/users")
            .then()
                .statusCode(200)
                .extract()
                .path("id");

        // 2. UPDATE
        String updateBody = """
                {
                    "name": "Updated Flow User",
                    "email": "updatedflow@gmail.com"
                }
                """;

        given()
            .baseUri("http://localhost:8080")
            .contentType("application/json")
            .body(updateBody)
        .when()
            .put("/users/" + userId)
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Flow User"))
            .body("email", equalTo("updatedflow@gmail.com"));

        // 3. GET / VERIFY
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/users/" + userId)
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Flow User"));

        // 4. DELETE
        given()
            .baseUri("http://localhost:8080")
        .when()
            .delete("/users/" + userId)
        .then()
            .statusCode(204);

        // 5. VERIFY DELETED
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/users/" + userId)
        .then()
            .statusCode(404);
    }
    
}