package regres;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class QueryAndPathParam {
	
	public String baseURI="https://reqres.in/api";
	
	@Test
	public void queryAndPathParam() {
		given()
		.baseUri(baseURI)
		.pathParam("myparam", "users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		
		.when()
		.get("/{myparam}")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	

}
