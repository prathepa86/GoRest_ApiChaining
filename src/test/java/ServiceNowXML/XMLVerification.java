package ServiceNowXML;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class XMLVerification {

	public String baseURI="https://dev264081.service-now.com/api/now/table/incident";

	@Test
	public void XMLResponse() {
		Response response = given()
		.baseUri(baseURI)
		.header("Authorization","Basic YWRtaW46UHJhdGhlcGFAMTk4Ng==")
		.header("Accept", "application/xml")
		.when()
		.get();
		//System.out.println(response.asPrettyString());
		XmlPath xmlPath = response.xmlPath();
		String Number = xmlPath.get("response.result[0].number").toString();
		System.out.println("Incident number:"+Number);
		Assert.assertEquals(Number, "INC0000060");
		Assert.assertEquals(response.statusCode(),200);
		Assert.assertEquals(response.header("Content-Type"), "application/xml;charset=UTF-8");
		List<Object> list = xmlPath.getList("response.result");
		System.out.println("Size is :"+list.size());
		boolean status=false;
		for(int i=0;i<list.size();i++) {
			System.out.println(xmlPath.get("response.result["+i+"].number"));
		  String AllNumbers = xmlPath.get("response.result["+i+"].number").toString();
			if(AllNumbers.equals("INC0009002")) {
				status=true;
				break;
				
			}
		}
		Assert.assertEquals(status, true);
		
		int count=0;
		for(int i=0;i<list.size();i++) {
			String stateList = xmlPath.get("response.result["+i+"].state").toString();
		if(stateList.equals("1")) {
			count++;
			
		}
		}
		System.out.println(count);
		
	
		
	
	}
	
	@Test
	public void responseValidation() {
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization","Basic YWRtaW46UHJhdGhlcGFAMTk4Ng==")
				.header("Accept", "application/xml")
				.when()
				.get();
		
		XmlPath xmlPath = response.xmlPath();
		System.out.println(xmlPath.getList("response.result"));
		
	}
	
	@Test
	public void usingXMLPathClass() {
		Response response = given()
				.baseUri(baseURI)
				.header("Authorization","Basic YWRtaW46UHJhdGhlcGFAMTk4Ng==")
				.header("Accept", "application/xml")
				.when()
				.get();
		XmlPath obj=new XmlPath(response.asString());
		System.out.println(obj.getList("response.result")); 
		
	}

}
