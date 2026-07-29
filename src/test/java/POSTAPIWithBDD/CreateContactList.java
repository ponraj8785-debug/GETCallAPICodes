package POSTAPIWithBDD;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateContactList {
	
	
	public String getRandomEmailId() {
		return "Ponraj"+System.currentTimeMillis()+"@opencart.com";
	}
	public String getfirstName() {
		return "Ponraj"+System.currentTimeMillis();
	}
	public String getLastName() {
		return "Nat"+System.currentTimeMillis();
	}
	
	@Test
	public void CreateUserWithJSONEmailReplacementTest() throws IOException {
		
		RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
		String emailId= getRandomEmailId();
		String firstName=getfirstName();
		String lastName=getLastName();
	
		//Convert the JSON File Content to String
		
		String rawFile=new String(Files.readAllBytes(Paths.get(".\\src\\test\\resources\\JSON\\contact.json")));
	
		String updateFirstName=rawFile.replace("{{firstname}}", firstName);
		String updateLastName=rawFile.replace("{{lastname}}", lastName);
		String updatedFile=rawFile.replace("{{email}}", emailId);
		
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2OTIxMGY1ZjgxNGZjMTAwMTU2YzRkMWMiLCJpYXQiOjE3ODQ3MDk1ODR9.uDJjo9Oszf3eqjtUFTOpyNJMA85PKvK0sXSgRyMlXR4")
	    .body(updateFirstName)
	    .body(updateLastName)
	    .body(updatedFile)
	    
	    
	    .when()
		.post("/contacts")
		
		.then().log().all()
		.assertThat()
		.statusCode(201);

	}

}
