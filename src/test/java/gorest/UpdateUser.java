package gorest;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	public String baseURI="https://gorest.co.in";
	
	@Test
	public void updateUser(ITestContext context) {
		int id=(int) context.getSuite().getAttribute("user_id");
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken="1bb7d91d6a572012ecea2a95d8abb6f6b2165943b8c89d5cca90110a67b527ed";
		
		given()	
		.body(data.toString())
		.header("Authorization","Bearer "+bearerToken)
		.baseUri(baseURI)
		.pathParam("id", id)
		.header("Content-Type","application/json")
		.when()
		.put("/public/v2/users/{id}")
		.then()
		.statusCode(200)
		.log().all();
	}

}
