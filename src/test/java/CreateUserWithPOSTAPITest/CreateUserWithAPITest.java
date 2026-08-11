package CreateUserWithPOSTAPITest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithAPITest {

	public String getRandomEmailId() {
		return "apiautomation" + System.currentTimeMillis() + "@opencart.com";
	}

	@Test
	public void CreateUserTest() throws IOException {

		RestAssured.baseURI = "https://gorest.co.in/";
		String emailId = getRandomEmailId();

		// Convert the JSON File Content to String

		String rawFile = new String(Files.readAllBytes(Paths.get(".\\src\\test\\resources\\JSON\\user.json")));
		String updatedFile = rawFile.replace("{{email}}", emailId);

		int userID = given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer c9debfcd908f8b4e46428181b1301810c2e79439bfdc0c0c47b9b089e8cdfcbb")
				.body(updatedFile)

				.when().post("public/v2/users")

				.then().log().all().assertThat().statusCode(201).extract().path("id");

		System.out.println("User Id is " + userID);
	}

}
