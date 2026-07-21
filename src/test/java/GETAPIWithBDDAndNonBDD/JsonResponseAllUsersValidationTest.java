package GETAPIWithBDDAndNonBDD;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonResponseAllUsersValidationTest {

	@Test
	public void getAllUserTest() {

		RestAssured.baseURI = "https://gorest.co.in";
		Response response = given().log().all()
				.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
				.when().get("/public/v2/users");

		JsonPath js = response.jsonPath();

		List<Integer> idList = js.getList("id");
		// System.out.println(idList);
		for (Integer id : idList) {
			System.out.println(id);
			Assert.assertNotNull(id);
		}

		List<String> nameList = js.getList("name");
		// System.out.println(nameList);
		for (String name : nameList) {
			System.out.println(name);
			Assert.assertNotNull(name);
		}

		List<String> emailList = js.getList("email");
		// System.out.println(emailList);
		for (String email : emailList) {
			System.out.println(email);
			Assert.assertNotNull(email);
		}

		List<String> genderList = js.getList("gender");
		// System.out.println(genderList);
		for (String gender : genderList) {
			System.out.println(gender);
			Assert.assertNotNull(gender);
		}

		List<String> statusList = js.getList("status");
		// System.out.println(statusList);
		for (String status : statusList) {
			System.out.println(status);
			Assert.assertNotNull(status);
		}
	}

}
