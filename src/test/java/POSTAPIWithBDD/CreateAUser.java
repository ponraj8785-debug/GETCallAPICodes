package POSTAPIWithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateAUser {
	
	public String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@opencart.com";
	}
	
	@Test
	public void CreateUserWithJSONStringTest() {
		
		RestAssured.baseURI="https://gorest.co.in/";
		String emailId= getRandomEmailId();
		
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.body("{\r\n"
				+ "    \"name\": \"Ethel_Strosin26\",\r\n"
				+ "    \"gender\": \"male\",\r\n"
				+ "    \"email\": \""+emailId+"\",\r\n"
				+ "    \"status\": \"active\"\r\n"
				+ "}")
		.when()
		.post("public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201);	
	}
	
	
	@Test
	public void CreateUserWithJSONFileTest() {
		
		RestAssured.baseURI="https://gorest.co.in/";

		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
	    .body(new File("./src/test/resources/JSON/user.json"))
	    
		.when()
		.post("public/v2/users")
		
		.then().log().all()
		.assertThat()
		.statusCode(201);	
	}
	
	

	@Test
	public void CreateUserWithJSONEmailReplacementTest() throws IOException {
		
		RestAssured.baseURI="https://gorest.co.in/";
		String emailId= getRandomEmailId();
		
		//Convert the JSON File Content to String
		
		String rawFile=new String(Files.readAllBytes(Paths.get(".\\src\\test\\resources\\JSON\\user.json")));
		String updatedFile=rawFile.replace("{{email}}", emailId);

		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
	    .body(updatedFile)
	    
		.when()
		.post("public/v2/users")
		
		.then().log().all()
		.assertThat()
		.statusCode(201);	
	}
	
}
