package POSTAPIWithDifferentBodyTypes;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;

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
	
	@Test
	public void createBodyWithMultiPartTest() {
		
		RestAssured.baseURI="https://www.postman-echo.com/";
			
		given().log().all()
		 .contentType(ContentType.MULTIPART)
		 .multiPart("name", "Ponraj")
		 .multiPart("resume",new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\6Yrs_Exp_QA.docx"))
		 .multiPart("picture", new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\Collection.png"))
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	

	@Test
	public void createBodyWithSinglePDFTest() {
		
		RestAssured.baseURI="https://www.postman-echo.com/";
			
		given().log().all()
		 .contentType("application/pdf;charset=utf-8")
		 .body(new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\dummy-pdf.pdf"))
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	
	@Test
	public void createBodyWithCSVTest() {
		
		RestAssured.baseURI="https://www.postman-echo.com/";
			
		given().log().all()
		 .contentType("text/csv")
		 .body(new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\CSVTestData.csv.csv"))
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	@Test
	public void createBodyWithXLSXTest() {
		
		RestAssured.baseURI="https://www.postman-echo.com/";
			
		given().log().all()
		 .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
		 .body(new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\Credentials.xlsx"))
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
	
	
	@Test
	public void createBodyWithDOCXTest() {
		
		RestAssured.baseURI="https://www.postman-echo.com/";
			
		given().log().all()
		 .contentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
		 .body(new File("C:\\Users\\Ponraj Natarajan\\OneDrive\\Desktop\\xpath.docx"))
		 .when()
		 .post("/post")
		 .then().log().all()
		 .assertThat()
		 .statusCode(200);
		
	}
}
