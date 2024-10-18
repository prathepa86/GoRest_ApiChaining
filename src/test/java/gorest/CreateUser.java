package gorest;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	
	String baseURL="https://gorest.co.in";
	@Test
	public void createUser(ITestContext context) {
		Faker faker=new Faker();
		JSONObject data=new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "female");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		String bearerToken="1bb7d91d6a572012ecea2a95d8abb6f6b2165943b8c89d5cca90110a67b527ed";
		
		int id= given()
		.baseUri(baseURL)
		.header("Authorization","Bearer "+bearerToken )
		.header("Content-Type","application/json")
		.body(data.toString())
		.when()
		.post("/public/v2/users")
		.jsonPath()	
		.getInt("id");
		System.out.println("The generated id is :"+id);
		context.getSuite().setAttribute("user_id", id);
		}

}
