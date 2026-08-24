import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuthCourses {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Get access_token
		String response=given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials").formParam("scope","trust")
		.when().log().all().post("/oauthapi/oauth2/resourceOwner/token").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		String accessToken=js.getString("access_token");
		
		//get course details
		given().queryParam("access_token", accessToken).when().log().all().get("/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401);

	}

}
