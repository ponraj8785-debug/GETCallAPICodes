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
	
	@Test
	public void createBodyWithJavaScriptTest() {
		
		
		RestAssured.baseURI="https://www.postman-echo.com/";
		String payload="// Simple console log test\r\n"
				+ "console.log(\"Hello, World! JavaScript is working.\");\r\n"
				+ "\r\n"
				+ "// Simple popup alert test\r\n"
				+ "alert(\"This is a dummy alert!\");";
		
		given().log().all()
		 .contentType("application/javascript")
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	

}
