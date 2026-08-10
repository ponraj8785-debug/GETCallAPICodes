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
		 .contentType("application/javascript;charset=utf-8")
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	@Test
	public void createBodyWithJSONTest() {
		
		
		RestAssured.baseURI="https://www.postman-echo.com/";
		String payload="{\r\n"
				+ "  \"id\": 102,\r\n"
				+ "  \"firstName\": \"John\",\r\n"
				+ "  \"lastName\": \"Doe\",\r\n"
				+ "  \"email\": \"johndo@example.com\",\r\n"
				+ "  \"age\": 28,\r\n"
				+ "  \"isActive\": true,\r\n"
				+ "  \"roles\": [\"User\", \"Admin\"],\r\n"
				+ "  \"address\": {\r\n"
				+ "    \"street\": \"123 Main Street\",\r\n"
				+ "    \"city\": \"New York\",\r\n"
				+ "    \"zipcode\": \"10001\"\r\n"
				+ "  }\r\n"
				+ "}";
		
		given().log().all()
		 .contentType(ContentType.JSON)
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	@Test
	public void createBodyWithHTMLTest() {
		
		
		RestAssured.baseURI="https://www.postman-echo.com/";
		String payload="<h1>Welcome to My Website</h1>";
		
		given().log().all()
		 .contentType(ContentType.HTML)
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	@Test
	public void createBodyWithXMLTest() {
		
		
		RestAssured.baseURI="https://www.postman-echo.com/";
		String payload="<dependency>\r\n"
				+ "    <groupId>io.rest-assured</groupId>\r\n"
				+ "    <artifactId>rest-assured</artifactId>\r\n"
				+ "    <version>5.5.6</version>\r\n"
				+ "    <scope>test</scope>\r\n"
				+ "</dependency>";
		
		given().log().all()
		 .contentType(ContentType.XML)
		 .body(payload)
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	

}
