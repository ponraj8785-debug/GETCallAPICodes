package CreateUserPOJOTest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateContactListUsingPOJO {
	
	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}
	
	public long getRandomPhoneNumber() {
	    // Generate a random 10-digit number starting with 9 (like Indian mobile numbers)
	    long min = 9000000000L;  // smallest 10-digit number starting with 9
	    long max = 9999999999L;  // largest 10-digit number
	    return min + (long)(Math.random() * (max - min));
	}

	
	
	@Test
	public void CreateContactListUsingPOJOTest() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/";
		String emailId = getRandomEmailId();
		long phoneNumber=getRandomPhoneNumber();
		
		//Create a object
		ContactList contatcs=new ContactList("Ponraj"," DhivyaP", emailId, phoneNumber, "Dindigul", "India");
		
	System.out.println("----------1.POST CALL---------------");
		
	String userID=	given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTIxMGY1ZjgxNGZjMTAwMTU2YzRkMWMiLCJpYXQiOjE3ODY4NDQyNTR9.46XEncBwYQCgZ7oQ0n-hXxT25lAU09YY6mwB9phAEyc")
		.body(contatcs)//auto-serialization =  POJO(Java object  -> JSON using jackson lib)
		.when()
		.post("/contacts")
		.then().log().all()
		    .assertThat()
		      .statusCode(201)
		         .extract()
		           .path("id");

    System.out.println("User Id is " + userID);
		
	}
}
