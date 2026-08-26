package FakeUserAPILombokBuilderPattern;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import FakeUserAPILombokBuilderPattern.FakeUser.Address;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class FakeUserBuilderPattern {
	
	
	@Test
	public void createFakeUserAPITestUsingBuilderPattern() {
		
	System.out.println("----------1.POST CALL---------------");
		
	RestAssured.baseURI="https://fakestoreapi.com";
	
	FakeUser.Name name=new FakeUser.Name.NameBuilder()
			           .firstname("Ponraj")
			           .lastname("Natarajan")
			           .build();
	
	Address.Geolocation geolocation= new Address.Geolocation.GeolocationBuilder()
            .lat("23.36")
            .longtitude("50.00")
            .build();
	
	FakeUser.Address address= new FakeUser.Address.AddressBuilder()
			                  .city("Chennai")
			                  .number("60007")
			                  .street("Selaiyur")
			                  .zipcode("600073")
			                  .geolocation(geolocation)
			                  .build();

	FakeUser user = new FakeUser(1000,
			                    "Ponraj@gmail.com",
			                    "Ponraj",
			                    "Admins@123",
			                    "7373964284", 
			                    1, 
			                    name, 
			                    address);
			                  
	int id=given().log().all()
	  .contentType(ContentType.JSON)
	  .body(user)
	.when()
	  .post("/users")
	.then().log().all()
	  .statusCode(201)
	  .and()
	  .extract()
	  .path("id");
	
	System.out.println("The Fake Id User Is " +id);
	
	
	System.out.println("----------2.GET CALL---------------");
	
	RestAssured.baseURI="https://fakestoreapi.com";
	
	given().log().all()
	  .contentType(ContentType.JSON)
	.when()
	  .get("/users/"+id)
	.then().log().all()
	  .statusCode(200);
	
	
	
	
}
	
}
