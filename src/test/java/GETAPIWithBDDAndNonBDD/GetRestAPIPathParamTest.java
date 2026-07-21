package GETAPIWithBDDAndNonBDD;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRestAPIPathParamTest {

	@Test

	public void getSingleUserUsingPathParamTest() {

		RestAssured.baseURI = "https://gorest.in/";

		given().log().all()
				.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
			      	.pathParam("userId", 1008)
				         .when()
				              .get("/public/v2/users/{userId}")
				                   .then().log().all()
				                          .assertThat()
				                                   .statusCode(200);

	}

	// Non BDD

	@Test

	public void getUserUsingPathParamTest() {

		RestAssured.baseURI = "https://gorest.in/";

		Response response = given().log().all()
				.header("Authorization", "Bearer c9debfcd908f8b4e4 6428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
			    	.pathParam("userId", 1008)
			        	.when()
				           .get("/public/v2/users/{userId}");

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.header("Content-Type"), "application/json; charset=utf-8");

	}

	@Test

	public void getUserUsingQueryPathParamFromGoResTest(     ) {

		RestAssured.baseURI = "https://gorest.in/public/v2/";
		
		HashMap<String, String>pathParam=new HashMap<String, String>();
		pathParam.put("resources", "users");
		
		HashMap<String, Integer>queryParam=new HashMap<String, Integer>();
		queryParam.put("page", 1);
		
		
	given().log().all()
		  .header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
	     	.pathParams(pathParam)
	             .queryParams(queryParam)
	                 .when()
	                	.get("{resources}")
	                    	.then().log().all()
		                        .assertThat()
	                               	.statusCode(200);
	}
	
	
	
}
