package seleniumcucumbertest.RestAssured;


import io.restassured.RestAssured;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Util.WireMockUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MockApiTest {

    private static final int MOCK_PORT = 8089;

    @BeforeClass
    public void setupSuite() {
        WireMockUtil.startServer(MOCK_PORT);
        RestAssured.baseURI = "http://localhost:" + MOCK_PORT;
    }

    @BeforeMethod
    public void resetStubs() {
        WireMockUtil.reset();
    }

    @AfterClass
    public void tearDownSuite() {
        WireMockUtil.stopServer();
    }

    @Test
    public void testMockedPostUser() {
        // Mock POST /api/users to return 201 Created
        WireMockUtil.stubPost("/api/users", 429, "{\"id\": \"101\", \"name\": \"John Doe\", \"job\": \"Engineer\"}");

        given()
        	.log().all()
            .header("Content-Type", "application/json")
            .body("{\"name\": \"John Doe\", \"job\": \"Engineer\"}")
        .when()
            .post("/api/users")
        .then()
        	.log().all()
            .statusCode(429)
            .body("id", equalTo("101"))
            .body("name", equalTo("John Doe"));
    }
}