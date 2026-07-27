import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.payload;

public class AddDeletePlaceAPI {

	public static void main(String[] args) {
		// Add Google place API
		// given-all input details,when-submit api-esource,http method,then-validation
		// Add Place

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.addPlacePayload()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();
		// System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

		// Update place
		String expectedAddress = "70 Bermingham, Paris";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeId + "\",\r\n" + "\"address\":\"" + expectedAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// Get Place
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).when()
				.get("/maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();

		JsonPath a = new JsonPath(getResponse);
		String actualAddress = a.getString("address");

		Assert.assertEquals(expectedAddress, actualAddress);
		// Delete Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\":\"" + placeId + "\"\r\n" + "}\r\n" + "").when()
				.delete("/maps/api/place/delete/json").then().log().all().statusCode(200).body("status", equalTo("OK"));
		

	}

}
