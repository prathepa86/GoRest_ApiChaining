package regres;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class RegresApi {
	int id;
	
	 @Test(priority=1)
	public void getUsers() {
		
		
		
		when()
		   .get("https://reqres.in/api/users?page=2")
		.then()
		   .statusCode(200)
		   .body("page",equalTo(2))
		   .log().all();
	}
	
	@Test(priority=2)
	public void createUser() {
		
		Map<String,String> hashMap=new HashMap<String,String>();
		hashMap.put("name", "morpheus");
		hashMap.put("job", "leader");
		id=given()
	    .contentType("application/json")
	    .body(hashMap)
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
	}
	
	@Test(priority=3,dependsOnMethods = {"createUser"})
	public void updateUser() {
		
		Map<String,String> hashMap=new HashMap<String,String>();
		hashMap.put("name", "Prathepa");
		hashMap.put("job", "Trainee");
		given()
		.contentType("application/json")
		.body(hashMap)
		.when()
		.put("https://reqres.in/api/users/"+id)
		.then()
		.statusCode(200)
		.log().all();
		
	}
	
	@Test(priority=4)
	public void deleteRequest() {
		given()
		.when()
		.delete("https://reqres.in/api/users/2")
		.then()
		.statusCode(204)
		.log().all();
	}
	
	
	

}
