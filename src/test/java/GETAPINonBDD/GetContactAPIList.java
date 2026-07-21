package GETAPINonBDD;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
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
	
	
	@Test
	public void getReqRestNonBDD() {
		
	RestAssured.baseURI="https://gorest.in/";
		
	RequestSpecification request=	RestAssured.given();
	                  request.headers("Authorization","Ponraj");
	Response response= request.get("/public/v2/users/");
	                  System.out.println(response.prettyPrint() +" " +response.statusCode() + " " + response.statusLine());
		
	}

	@Test
	public void getReqRestHeadersAssertionNonBDD() {
		
		RestAssured.baseURI="https://gorest.in/";
		RequestSpecification request=	RestAssured.given();
		          request.headers("Authorization","Ponraj");
		Response response= request.get("/public/v2/users/");
		
		
		//List Down the headers
		
	List<Header>headers=response.headers().asList();
	System.out.println("Total number of header size is "+headers.size());
	for(Header e : headers) {
	   String headerName=	e.getName();
	   String headerValue= e.getValue();
	   System.out.println(headerName +" : " +headerValue);
	}
		
		
	}
	}
	

