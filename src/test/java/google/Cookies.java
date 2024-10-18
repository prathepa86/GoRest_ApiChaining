package google;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Map;

public class Cookies {
	
	public String baseURI="https://www.google.com/";
	
	@Test
	public void getCookies() {
		
		Response response = given()
		.baseUri(baseURI)
		.when()
		.get();
		
		String cookie_value = response.getCookie("AEC");
		System.out.println(cookie_value);
		
		Map<String, String> cookies_value = response.getCookies();
		for(String val:cookies_value.keySet()) {
			System.out.println(val+"--"+response.getCookie(val));
		}
		
	}
	
	

}
