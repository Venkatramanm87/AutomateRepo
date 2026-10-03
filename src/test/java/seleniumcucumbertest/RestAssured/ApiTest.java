package seleniumcucumbertest.RestAssured;

import java.io.File;
import java.util.concurrent.Callable;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import Util.ApiDataProvider;
import Util.ApiRetry;
import Util.TestListener;
import Util.UserData;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.Matchers.equalTo;

@Listeners(TestListener.class)
public class ApiTest {

	
	@Test(dataProvider = "userData", dataProviderClass = ApiDataProvider.class)
			
	public void postApi(UserData user) throws Exception {	
		final File jsonFile = new File("src/main/resources/testdata.json");

		Response response = ApiRetry.execute(new Callable<Response>() {
			@Override
			public Response call() {
				return RestAssured.given()
						.spec(SpecFactory.getRequestSpecification("token123"))
						.body(user)
						.when()
						.post("/users");
			}
		});

		response.then()
				.spec(SpecFactory.getResponseSpecification(201))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user-schema.json"))
				.body("name", equalTo(user.getName()))
				.body("name", equalTo(user.getName()));

		System.out.println("Response time: " + response.time() + " ms");
				
	}

	@Test
	public void getApi() throws Exception {	
		Response response = ApiRetry.execute(new Callable<Response>() {
			@Override
			public Response call() {
				return RestAssured.given()
						.spec(SpecFactory.getRequestSpecification("token123"))
						.pathParam("id", "2")
						.when()
						.get("/users/{id}");
			}
		});

		response.then()
				.spec(SpecFactory.getResponseSpecification(200));
		System.out.println("Response time: " + response.time() + " ms");

	}
}