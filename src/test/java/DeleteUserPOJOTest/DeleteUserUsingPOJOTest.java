package DeleteUserPOJOTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import UpdateUserPOJOTest.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteUserUsingPOJOTest {
	
	
	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}
	
	@Test
	public void CreateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in/";
		String emailId = getRandomEmailId();
		
	// Step 1 :: Create a user
		
		//Create a object
		User user=new User("Ponraj Raja", emailId, "male", "active");
		
		
		System.out.println("----------1.POST CALL---------------");
		
		int userID=	given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
			.body(user)//auto-serialization =  POJO(Java object  -> JSON using jackson lib
			.when()
			.post("public/v2/users")
			.then().log().all()
			    .assertThat()
			      .statusCode(201)
			         .extract()
			           .path("id");

	    System.out.println("User Id is " + userID);
	    
	    System.out.println("----------2.GET CALL---------------");

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
					.body("name", equalTo(user.getName()))
					.body("email", equalTo(emailId))
					.body("status", equalTo(user.getStatus()))
					.body("gender", equalTo(user.getGender()));
			
	    System.out.println("----------3.DELETE CALL---------------");
	    
	    given().log().all()
		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.when()
		.delete("/public/v2/users/" + userID)
		.then().log().all()
		   .assertThat()
			.statusCode(204);
	    
	    System.out.println("----------4.GET CALL---------------");
	    
	    given().log().all()
		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
		.when()
		.get("/public/v2/users/" + userID)
		.then().log().all()
		   .assertThat()
			.statusCode(404)
			 .and()
				.body("message", equalTo("Resource not found"));
	    
	    
	    
	}
	
	
	

}
