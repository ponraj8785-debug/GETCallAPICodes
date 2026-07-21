package GETAPIWithBDDAndNonBDD;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ContactListGetCalls {
	
	@Test
	public void getContactlist() {
		
	RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
	 given().log().all()
	 .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTIxMGY1ZjgxNGZjMTAwMTU2YzRkMWMiLCJpYXQiOjE3ODQ1MzkwNjJ9.qcHW59Htx740Ywd3kv0f23YU8F9GnFfAC06luJVy-Is")
	 .when().get("/contacts/")
		.then().log().all()
		.assertThat()
		.statusCode(200).and()
		.contentType(ContentType.JSON);
		
	}
	
	@Test
	public void getContactlistAssertions() {
		
	RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
	Response response= given().log().all()
	 .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTIxMGY1ZjgxNGZjMTAwMTU2YzRkMWMiLCJpYXQiOjE3ODQ1MzkwNjJ9.qcHW59Htx740Ywd3kv0f23YU8F9GnFfAC06luJVy-Is")
	 .when().get("/contacts/");
	
	JsonPath js = response.jsonPath();
	
	String userId = js.getString("_id");
	System.out.println("userId is " + userId);
	
	List<Object> ids=js.getList("_id");
	for(Object list:ids) {
		System.out.println("list of id's : "+list);
		Assert.assertNotNull(list);
	}
	
	String firstName=js.getString("firstName");
	System.out.println("firstName " +firstName);
	
	String lastName=js.getString("lastName");
	System.out.println("lastName " +lastName);
	}
	
	
	
	@Test
	public void getAuthTest() {
		
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		 given().log().all()
		 .header("Authorization","Bearer Ponraj")
		 .when().get("/contacts/")
			.then().log().all()
			.assertThat()
			.statusCode(401);	
		}
	
	
	@Test
	public void getAuthErrorMessageTest() {
		
	RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
	String errorMessage =given().log().all()
		 .header("Authorization","Bearer Ponraj")
		 .when().get("/contacts/")
			.then().log().all()
			.extract()
			.path("error");
	System.out.println("Response valuse is " +errorMessage);
	Assert.assertEquals(errorMessage, "Please authenticate.");
	
	}
	
}
