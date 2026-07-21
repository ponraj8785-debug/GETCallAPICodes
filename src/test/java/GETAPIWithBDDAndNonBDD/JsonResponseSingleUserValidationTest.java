package GETAPIWithBDDAndNonBDD;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonResponseSingleUserValidationTest {

	
	//BDD + Non BDD
	
	@Test
	public void getSingleUserTest() {
        //BDD
		
		RestAssured.baseURI = "https://gorest.in/";
		Response response = given().log().all()
				                   .header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
				                       .when()
				                         .get("/public/v2/users/1012");
		
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");
		
		
		//Non BDD

		System.out.println("Status code " + response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);

		System.out.println("Status line " + response.statusLine());
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

		String contentType = response.header("Content-Type");
		System.out.println("Header is " + contentType);
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");

		System.out.println("OverAll header is " + response.getHeaders());
		response.prettyPrint();

		// Fetch the JOSN response body

		JsonPath js = response.jsonPath();
		
		int userId = js.getInt("id");
		System.out.println("userId is " + userId);
		Assert.assertEquals(userId, 1012);

		String name = js.getString("name");
		System.out.println("Name is " + name);
		Assert.assertEquals(name, "Deepika Pillai");

		String email = js.getString("email");
		System.out.println("Email is " + email);
		Assert.assertEquals(email, "deepika.pillai@example.com");

		String gender = js.getString("gender");
		System.out.println("Gender is " + gender);
		Assert.assertEquals(gender, "female");

		String status = js.getString("status");
		System.out.println("Status is " + status);
		Assert.assertEquals(status, "inactive");

	}

}
