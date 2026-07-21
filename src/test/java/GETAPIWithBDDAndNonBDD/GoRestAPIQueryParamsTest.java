package GETAPIWithBDDAndNonBDD;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GoRestAPIQueryParamsTest {
	
	
	@Test
	public void getAllUserQueryParamTest() {

		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
				.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
				.queryParam("name", "Chaten Gill")
				.queryParam("status", "active")
				.when().get("/public/v2/users")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON);	
	}

	@Test
	public void getAllUserUsingHashmapQueryParamTest() {

		RestAssured.baseURI = "https://gorest.co.in";
		
		HashMap<String, String>map=new HashMap<String, String>();
		map.put("name", "Chaten Gill");
		map.put("status", "active");
		map.put("gender", "male");
		
		given().log().all()
				.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
				.queryParams(map)
				.when().get("/public/v2/users")
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON);	
	}

	
	@Test
	public void QueryParamTest() {

		RestAssured.baseURI = "https://gorest.in/";
		
		HashMap<String, String>map1= new HashMap<String, String>();
		map1.put("page", "1");
		
		given().log().all()
		   .header("Autherization","Ponraj")
		   .queryParams(map1)
		   .when()
		   .get("public/v2/users")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200);
		   
	}
	
	@Test
	public void PathParamTest() {

		RestAssured.baseURI = "https://gorest.in/";

		HashMap<String, String> query = new HashMap<String, String>();
		query.put("page", "2");

		HashMap<String, String> path = new HashMap<String, String>();
		path.put("versions", "v2");
		path.put("resources", "users");

		given().log().all().header("Autherization", "Ponraj")
		.pathParams(path)
		.queryParams(query)
		.when()
		.get("public/{versions}/{resources}")
		.then().log().all()
		.assertThat()
		.statusCode(200);

	}

	@Test
	public void responseNonBDDTest() {

		RestAssured.baseURI = "https://gorest.in/";
		
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("page", "2");

		HashMap<String, String> path = new HashMap<String, String>();
		path.put("versions", "v2");
		path.put("resources", "users");

	Response res =	given().log().all().header("Autherization", "Ponraj")
		.pathParams(path)
		.queryParams(query)
		.when()
		.get("public/{versions}/{resources}");
	
	  JsonPath js = res.jsonPath();
	  List<Integer>id= js.getList("id");
	  for(Integer ids:id) {
		  System.out.println(ids);
		  Assert.assertNotNull(ids);
	  }
	}
}
