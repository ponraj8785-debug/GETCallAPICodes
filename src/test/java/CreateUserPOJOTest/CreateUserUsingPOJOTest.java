package CreateUserPOJOTest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class CreateUserUsingPOJOTest {

	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}
	
	@Test
	public void CreateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in/";
		String emailId = getRandomEmailId();
		
		//Create a object
		User user=new User("Ponraj", emailId, "male", "active");
		
	System.out.println("----------POST CALL METHOD---------------");
		
	int userID=	given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.body(user)//serialization =  POJO  -> JSON
		.when()
		.post("public/v2/users")
		.then().log().all()
		    .assertThat()
		      .statusCode(201)
		         .extract()
		           .path("id");

    System.out.println("User Id is " + userID);

    System.out.println("----------GET CALL METHOD---------------");

//Step 2 :: Get user by the same user id

		given().log().all()
		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.when()
		.get("/public/v2/users/" + userID)
		.then().log().all()
		   .assertThat()
			.statusCode(200)
			 .and()
				.body("id", equalTo(userID))
				.body("name", equalTo("Ponraj"))
				.body("email", equalTo(emailId))
				.body("status", equalTo("active"))
				.body("gender", equalTo("male"));

	}

}
