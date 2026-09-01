package SerializationDeserialization;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserAndAssertion {
	
	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}
	
	
	@Test
	public void CreateUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in/";
		String emailId = getRandomEmailId();
		
		//Create a  first object --> Serialization (POJO -> JSON)
		System.out.println("----------OBJECT MAPPER -> Expected User Object---------------");
		
		User user=new User(null,"Ponraj Natarajan", emailId, "male", "active");
		
		System.out.println("----------1.POST CALL---------------");

		Integer userID = given().log().all()
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

    //Step 2 :: Get user by the same user id
    
    
    System.out.println("----------2.GET CALL---------------");

  	Response response =	given().log().all()
  		.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
  		.when()
  		.get("/public/v2/users/" + userID);
  	response.prettyPrint();
  	
  	//De serialization -> JSON response to POJO
  	
   //Create a second object --> De Serialization (JSON -> POJO)
  	
  	ObjectMapper mapper=new ObjectMapper();
  	
  	try {
  	System.out.println("----------OBJECT MAPPER -> Actual User Object---------------");
                       	 
	User userRes=	mapper.readValue(response.getBody().asString(), User.class);
	System.out.println("Object Mapper User Response Is " +userRes);
	
	Assert.assertEquals(userID,userRes.getId());
 	Assert.assertEquals(userRes.getName(),user.getName());
 	Assert.assertEquals(userRes.getStatus(),user.getStatus());
 	Assert.assertEquals(userRes.getEmail(),user.getEmail());
 	Assert.assertEquals(userRes.getGender(),user.getGender());
 	
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
  	
  	//So we validate expected user object to actual user object 
  	
 
		
	}

}
