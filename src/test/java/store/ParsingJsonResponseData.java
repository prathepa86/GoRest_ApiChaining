package store;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	
	public String baseUri="http://localhost:3000/store";
	
	
	//@Test
	public void parsingData() {
		Response response = given()
		.baseUri(baseUri)
		.when()
		.get()
		.then()
		.statusCode(200)
		.header("Content-Type","application/json")
		.extract()
		.response();
		JsonPath jsonPath = response.jsonPath();
		System.out.println("Title is :"+jsonPath.get("book[3].title"));
		Assert.assertEquals(jsonPath.get("book[3].title").toString(), "Test Engg");
		Assert.assertEquals(response.statusCode(), 200);
		//Assert.assertEquals(response.statusCode(), 201, "Status code is  not passed");
	}
	
	@Test
	public void getAllTitles() {
		Response response = given()
		.baseUri(baseUri)
		.when()
		.get();
		
		JsonPath jsonPath = response.jsonPath();
		List<Object> list = jsonPath.getList("book");
		boolean status=false;
		
		for(int i=0;i<list.size();i++) {
			String bookTitles = jsonPath.get("book["+i+"].title").toString();
			System.out.println(bookTitles);
			if(bookTitles.equals("Test Engg")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		
		
	}
	
	@Test
	public void totalPrice() {
		int total=0;
		Response response = given()
		.baseUri(baseUri)
		.when()
		.get();
		
		JsonPath jsonPath = response.jsonPath();
		List<Object> list = jsonPath.getList("book");
		for(int i=0;i<list.size();i++) {
			String price = jsonPath.get("book["+i+"].price").toString();
			total=total+Integer.parseInt(price);
		}
		
		System.out.println("Total amount is:"+total);
		Assert.assertEquals(total, 310);
		
	}

}
