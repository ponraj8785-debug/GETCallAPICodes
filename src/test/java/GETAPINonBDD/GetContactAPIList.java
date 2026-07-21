package GETAPINonBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetContactAPIList {
	
	@Test
	public void getContactListNonBDD() {
		
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		
		
		RequestSpecification request= RestAssured.given();
		request.header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTIxMGY1ZjgxNGZjMTAwMTU2YzRkMWMiLCJpYXQiOjE3ODQ1MzkwNjJ9.qcHW59Htx740Ywd3kv0f23YU8F9GnFfAC06luJVy-Is");
		
		Response response=request.get("/contacts/");
		
		int statusCode=response.statusCode();
		String statusLine=response.statusLine();
		
		System.out.println(statusCode + " " +statusLine);
		
		response.prettyPrint();
	}

}
