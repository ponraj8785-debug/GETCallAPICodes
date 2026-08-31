package PETAPIWithLombokBuilderPattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import PETAPIWithLombokBuilderPattern.Pet.Category;
import PETAPIWithLombokBuilderPattern.Pet.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AssertTheResponsePETAPITest {

	@Test
	public void createPetAPITest() {

		System.out.println("----------1.POST CALL---------------");

		RestAssured.baseURI = "https://petstore.swagger.io";

		// Create the object f the pet class

		Pet.Category category = new Category(111, "dog");

		List<String> photoURLs = Arrays.asList("https://www.photo123.com", "https://www.photo123.com",
				"https://www.photo123.com");

		Pet.Tag tag1 = new Pet.Tag(101, "White");
		Pet.Tag tag2 = new Pet.Tag(102, "Black");
		Pet.Tag tag3 = new Pet.Tag(103, "Brown");
		Pet.Tag tag4 = new Pet.Tag(104, "Red");

		List<Tag> tags = Arrays.asList(tag1, tag2, tag3, tag4);

		Pet pet = new Pet(001, "dogs", "available", photoURLs, tags, category);

		Response response = given().log().all().contentType(ContentType.JSON).body(pet).when().post("/v2/pet");

		JsonPath js = response.jsonPath();

		Assert.assertEquals(js.getInt("id"), pet.getId());
		Assert.assertEquals(js.getString("name"), pet.getName());
		Assert.assertEquals(js.getString("status"), pet.getStatus());

		Assert.assertEquals(js.getInt("category.id"), category.getId());
		Assert.assertEquals(js.getString("category.name"), category.getName());

		Assert.assertEquals(js.getList("photoUrls"), pet.getPhotoUrls());

		// Eitehr single assertion or iteration way to validate

		/*
		 * Assert.assertEquals(js.getInt("tags[0].id"), pet.getTags().get(0).getId());
		 * Assert.assertEquals(js.getInt("tags[1].id"), pet.getTags().get(1).getId());
		 * Assert.assertEquals(js.getInt("tags[2].id"), pet.getTags().get(2).getId());
		 * Assert.assertEquals(js.getInt("tags[3].id"), pet.getTags().get(3).getId());
		 */

		for (int i = 0; i < tags.size(); i++) {
			Assert.assertEquals(js.getInt("tags[" + i + "].id"), pet.getTags().get(i).getId());
			Assert.assertEquals(js.getString("tags[" + i + "].name"), pet.getTags().get(i).getName());
		}
		
		   System.out.println("----------2.GET CALL---------------");

		    //Step 2 :: Get user by the same user id
		   RestAssured.baseURI = "https://petstore.swagger.io";
		   
		given().log().all()
				.contentType(ContentType.JSON)
				.body(pet)
				.when()
				.get("/v2/pet/" + pet.getId())
				.then().log().all()
				   .assertThat()
					.statusCode(200);

	    
	    
	    
	    
	    
	    

	}

}