package PETAPIWithLombokBuilderPattern;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import PETAPIWithLombokBuilderPattern.Pet.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAndGetPetAPIUsingBuilderPattern {
	

	@Test
	public void createPetAPITestUsingBuilderPattern() {
		
	System.out.println("----------1.POST CALL---------------");
		
	RestAssured.baseURI="https://petstore.swagger.io";
		
	Pet.Category category= new Pet.Category.CategoryBuilder()
		.id(1)
		.name("dog")
		.build();
		
	List<String>photoURLs=  Arrays.asList
			("https://www.photo123.com","https://www.photo123.com","https://www.photo123.com");
	
    Pet.Tag tag1=new Pet.Tag.TagBuilder()
	    .id(101)
	    .name("dogs")
	    .build();
    Pet.Tag tag2=new Pet.Tag.TagBuilder()
    	.id(102)
    	.name("dogers")
    	.build();
    
    List<Tag>tags= Arrays.asList(tag1,tag2);
    
    Pet pet1=new Pet.PetBuilder()
    		.id(1)
    		.name("Rogers")
    		.status("Available")
    		.photoUrls(photoURLs)
    		.tags(tags)
    		.category(category)
    		.build();
    

	int id=given().log().all()
	        .contentType(ContentType.JSON)
	          .body(pet1)
	           .when()
	            .post("/v2/pet")
	              .then()
	                .log().all()
                      .assertThat()
                        .statusCode(200)
                          .extract()
                            .path("id");
	 System.out.println("pet Id is " +id);
	 
	 System.out.println("----------2.GET CALL---------------");
	 
	 
	 RestAssured.baseURI="https://petstore.swagger.io";
	 
	 given().log().all()
     .contentType(ContentType.JSON)
       .body(pet1)
        .when()
         .get("/v2/pet/"+id)
           .then()
             .log().all()
               .assertThat()
                 .statusCode(200);
	}

}
