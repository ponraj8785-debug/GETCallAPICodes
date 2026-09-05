package SerializationDeserialization;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetProductAPITest {
	
	
	
	@Test
	public void CreateUserTest() {
		
		System.out.println("----------1.GET CALL---------------");
		//auto-serialization =  POJO(Java object  -> JSON using jackson lib
		
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		Response response=RestAssured.given()
		.when()
		.get("/products");
		
		response.prettyPrint();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//De serialization -> JSON response to POJO
	  	
		//Create a second object --> De Serialization (JSON -> POJO)
		  	
		 ObjectMapper mapper=new ObjectMapper();
		 try {
			ProductLombok[]products= mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			for(ProductLombok prod:products) {
				System.out.println("id :" +prod.getId());
				System.out.println("Title :" +prod.getTitle());
				System.out.println("Price :" +prod.getPrice());
				System.out.println("Description :" +prod.getDescription());
				System.out.println("category :" +prod.getCategory());
				System.out.println("Image :" +prod.getImage());
				System.out.println("Rate :" +prod.getRating().getRate());
				System.out.println("Count :" +prod.getRating().getCount());
				System.out.println("-------------------------------------");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	
		
	}

}
