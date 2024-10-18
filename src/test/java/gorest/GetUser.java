package gorest;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUser {
	
	public String baseURI="https://gorest.co.in";
	
	@Test
	public void getSingleUsers(ITestContext context) {
		
		int id=(int) context.getSuite().getAttribute("user_id");
		
		String bearerToken="1bb7d91d6a572012ecea2a95d8abb6f6b2165943b8c89d5cca90110a67b527ed";
		given()
		.baseUri(baseURI)
		.pathParam("id", id)
		
		.header("Authorization","Bearer "+bearerToken)
		.when()
		.get("/public/v2/users/{id}")
		.then()
		.statusCode(200)
		.log().all();
		
		
	}

}
