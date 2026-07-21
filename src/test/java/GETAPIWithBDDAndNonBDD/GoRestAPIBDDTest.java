package GETAPIWithBDDAndNonBDD;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GoRestAPIBDDTest {

	
	@Test
	public void getAllUserTest() {
		
		RestAssured.baseURI="https://gorest.in/";
		given().log().all()
		     .header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		            .when()
		                .get("/public/v2/users")
	                      .then().log().all()
		                        .assertThat()
		                             .statusCode(200)
		                                   .and()
		                                      .statusLine("HTTP/1.1 200 OK");
	}
		
	
	@Test
	public void getSingleUserTest() {
		
		RestAssured.baseURI="https://gorest.in/";
		given().log().all()
		     .header("Authorization","Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		            .when()
		                .get("/public/v2/users/1008")
	                      .then().log().all()
		                        .assertThat()
		                             .statusCode(200)
		                                   .and()
		                                      .statusLine("HTTP/1.1 200 OK");
	}
	
	
	
	@Test
	public void authTest() {
		
		RestAssured.baseURI="https://gorest.in/";
		given().log().all()
		     .header("Authorization","Bearer PonrajNatarajan")
		            .when()
		                .get("/public/v2/users")
	                      .then()
		                        .assertThat()
		                             .statusCode(200)
		                                   .and()
		                                      .statusLine("HTTP/1.1 200 OK");
	}
}
