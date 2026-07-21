package POSTAPIWithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateAUser {
	
	@Test
	public void CreateUser() {
		
		RestAssured.baseURI="https://gorest.co.in/";
		
		given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.body("{\r\n"
				+ "    \"name\": \"Ethel_Strosin26\",\r\n"
				+ "    \"gender\": \"male\",\r\n"
				+ "    \"email\": \"apiIssac.Davisss@gmail.com\",\r\n"
				+ "    \"status\": \"active\"\r\n"
				+ "}")
		.when()
		.post("public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201);
		
	}

}
