package seleniumcucumbertest.RestAssured;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import Util.TestListener;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(TestListener.class)
public class ApiTest {
	
	@Test
	public void postApi()
	{	
		File jsonFIle = new File("src/main/resources/testdata.json");
//		String jsonBody = "src/test/resources/testdata.json";
		
//		Map<String, Object> payload = new HashMap<>();
//		payload.put("name", "JohnDoe");
//		payload.put("job", "QA Engineer");
		Response response = RestAssured.given()
				.spec(SpecFactory.getRequestSpecification("token123"))
				.body(jsonFIle)
				.when()
				.post("/users");
			
				response.then()
				.spec(SpecFactory.getResponseSpecification(201));
				String id = response.jsonPath().getString("id");
				response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"));

	}
	@Test
	public void getApi()
	{	
		
		Map<String, Object> payload = new HashMap<>();

		Response response = RestAssured.given()
				.spec(SpecFactory.getRequestSpecification("token123"))
				.pathParam("id", "2")
				.when()
				.get("/users/{id}");
				
				response.then()
				.spec(SpecFactory.getResponseSpecification(200));
	}

}
