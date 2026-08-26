package FakeUserAPILombokBuilderPattern;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import FakeUserAPILombokBuilderPattern.FakeUser.Address;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

public class FakeUserAPITest {
	
	
	@Test
	public void fakeUserAPITest() {
		
		System.out.println("----------1.POST CALL---------------");
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Address.Geolocation geoLocation=new Address.Geolocation("-37.3159", "81.1496");
		FakeUser.Address address= new FakeUser.Address("Dindigul","Kaveri Nager", "12345", "624003", geoLocation);
		FakeUser.Name name= new FakeUser.Name("Ponraj", "Natarajan");
		
		
		
		FakeUser user = new FakeUser(100, "Ponrajnatarajan@gamil.com", "johnd", "m38rmF$", "1-570-236-7033", 10, name, address);
	int id=	given().log().all()
		  .contentType(ContentType.JSON)
		  .body(user)
		.when()
		  .post("/users")
		.then().log().all()
		  .statusCode(201)
		  .and()
		  .extract()
		  .path("id");
	System.out.println("Id is" +id);
		
		System.out.println("----------2.GET CALL---------------");
		
		RestAssured.baseURI="https://fakestoreapi.com";
		
		given().log().all()
		  .contentType(ContentType.JSON)
		.when()
		  .get("/users/"+id)
		.then().log().all()
		  .statusCode(200)
		  .body("phone", equalTo("1-570-236-7033"))
		  .body("email", equalTo("john@gmail.com"))
		  .body("username", equalTo("johnd"))
		  .body("password", equalTo("m38rmF$"));
		
		
	}
	
	

}
