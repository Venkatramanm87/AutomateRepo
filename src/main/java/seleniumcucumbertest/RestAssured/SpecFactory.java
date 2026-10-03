package seleniumcucumbertest.RestAssured;

//import io.restassured.builder.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecFactory {

	
	public static RequestSpecification getRequestSpecification(String token) {
	RequestSpecification requestSpecification = new RequestSpecBuilder()
			.setBaseUri(ConfigManager.getProperty("base.url"))
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Bearer" + token)
			.log(LogDetail.ALL)
			.build();
	return requestSpecification;
	}

	public static ResponseSpecification getResponseSpecification(int statusCode) {
		
		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
					.expectStatusCode(statusCode)
					.expectContentType(ContentType.JSON)
					.log(LogDetail.ALL)
					.build();
		return responseSpecification;
	}	
	
}
