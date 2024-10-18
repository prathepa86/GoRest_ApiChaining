package student;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;


public class PostRequestTypes {
	String id;
	
	//@Test(priority=1)
	public void postRequestUsingHashMap() {
	HashMap<String,String> hashMap=new HashMap<String,String>();
	hashMap.put("name","Raja");
	hashMap.put("location","UK");
	hashMap.put("phone","123456789");
	//String courseArr[]= {"React","DotNet"};
	//hashMap.put("courses",courseArr);
	hashMap.put("courses[0]","React");
	hashMap.put("courses[1]","DotNet");
	
	given()
	.contentType("application/json")
	.body(hashMap)
	.when()
	.post("http://localhost:3000/students")
	.then()
	.statusCode(201)
	.body("name", equalTo("Raja"))
	.body("location", equalTo("UK"))
	.body("phone", equalTo("123456789"))
	.body("courses[0]", equalTo("React"))
	.body("courses[1]", equalTo("DotNet"))
	.header("Content-Type", "application/json;charset=utf-8")
	.log().all();
	
	
	}
	
	//@Test(priority=2,dependsOnMethods = {"postRequestUsingHashMap"})
	public void deleteRequest() {
		given()
		.when()
		.delete("http://localhost:3000/students/4")
		.then()
		.statusCode(204);
	}
	
	
	//@Test(priority=1)
	public void postRequestThruOrgJson() {
		JSONObject obj=new JSONObject();
		obj.put("name", "Raja");
		obj.put("location", "UK");
		obj.put("phone", "123456789");
		String[] courseArr= {"DotNet","React"};
		obj.put("courses", courseArr);
		id=given()
		.contentType("application/json")
		.body(obj.toString())
		.when()
		.post("http://localhost:3000/students")
		.jsonPath().getString("id");
		/*
		 * .then() .statusCode(201) .body("name", equalTo("Raja")) .body("location",
		 * equalTo("UK")) .body("phone", equalTo("123456789")) .body("courses[0]",
		 * equalTo("DotNet")) .body("courses[1]", equalTo("React"))
		 * .header("Content-Type","application/json") .log().all();
		 */
		
	}
	
	//@Test(priority=2)
	public void deleteOrgJson() {
		when()
		.delete("http://localhost:3000/students/"+id)
		.then()
		.statusCode(200);
	}
	
	//@Test
	public void PostRequestUsingPojo() {
		Pojo_OrgJson pojoData=new Pojo_OrgJson();
		pojoData.setName("Raja");
		pojoData.setLocation("UK");
		pojoData.setPhone("123455");
		String[] courseArr= {"React","Dotnet"};
		pojoData.setCourses(courseArr);
		given()
		.contentType("application/json")
		.body(pojoData)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("Raja"))
		.body("location", equalTo("UK"))
		.body("phone", equalTo("123455"))	
		.header("Content-Type","application/json")
		.log().all();
	}
	
	@Test
	public void PostRequestUsingExternalFiles() throws FileNotFoundException {
		File file=new File(".//postbody.json");
		FileReader fr=new FileReader(file);
		JSONTokener token=new JSONTokener(fr);
		JSONObject jsonObj=new JSONObject(token);
		given()
		.contentType("application/json")
		.body(jsonObj.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.log().all();
	}
	
}
