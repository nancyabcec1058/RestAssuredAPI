import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import Files.payload;
import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;

public class Serialization {
	public static void main(String[] args) {

		AddPlace a = new AddPlace();
		a.setLocation(null);
		a.setAccuracy(50);
		a.setName("My house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("29, manhaaten, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		List<String> l = new ArrayList<>();
		l.add("ballerina");
		l.add("candy shop");
		a.setTypes(l);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(a).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();
		System.out.println(response);
	}
}