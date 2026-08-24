package PETAPIWithLombokBuilderPattern;

import org.testng.annotations.Test;

import PETAPIWithLombokBuilderPattern.Pet.Category;
import PETAPIWithLombokBuilderPattern.Pet.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

public class CreatePETAPITest {
	
	
	@Test
	public void createPetAPITest() {
		
		System.out.println("----------1.POST CALL---------------");
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		//Create the object f the pet class
		
		Pet.Category category=new Category(111, "dog");
		
		List<String>photoURLs=  Arrays.asList
				("https://www.photo123.com","https://www.photo123.com","https://www.photo123.com");
		
		Pet.Tag tag1=new Pet.Tag(101, "White");
		Pet.Tag tag2= new Pet.Tag(102, "Black");
		
		List<Tag>tags= Arrays.asList(tag1,tag2);
		
		Pet pet= new Pet(001, "dogs", "available", photoURLs, tags, category);
		
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(pet)
		.when()
		.post("/v2/pet")
		.then()
		.log().all()
	    .assertThat()
	      .statusCode(200);
		
	}

}
	
