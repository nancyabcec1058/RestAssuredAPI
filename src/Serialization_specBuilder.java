import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import Files.payload;
import POJO.AddPlace;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Serialization_specBuilder {
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

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		RequestSpecification response = given().spec(req).body(a);
		Response r = response.when().post("/maps/api/place/add/json").then().spec(rs).extract().response();
		System.out.println(r);
	}
}