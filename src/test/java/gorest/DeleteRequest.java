package gorest;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DeleteRequest {
	
	public String baseURI="https://gorest.co.in";
	@Test
	public void deleteUsers(ITestContext context) {
		String bearerToken="1bb7d91d6a572012ecea2a95d8abb6f6b2165943b8c89d5cca90110a67b527ed";
		int id=(int) context.getSuite().getAttribute("user_id");
		
		given()
		.header("Authorization","Bearer "+bearerToken)
		.baseUri(baseURI)
		.pathParam("id", id)
		.when()
		.delete("/public/v2/users/{id}")
		.then()
		.statusCode(204)
		.log().all();
	}

}
