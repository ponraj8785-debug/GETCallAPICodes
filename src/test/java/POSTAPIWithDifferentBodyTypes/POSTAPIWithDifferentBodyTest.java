package POSTAPIWithDifferentBodyTypes;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class POSTAPIWithDifferentBodyTest {
	
	@Test
	public void createBodyWithTextTest() {
		
		
		RestAssured.baseURI="https://www.postman-echo.com/";
		String payload="Hi this is ponraj";
		
		given().log().all()
		 .contentType(ContentType.TEXT)
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}

}
