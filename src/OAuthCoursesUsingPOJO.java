import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.GetCourse;
import POJO.apiCourse;
import POJO.webAutomationCourse;

public class OAuthCoursesUsingPOJO {

	public static void main(String[] args) {
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		List<String> expected=Arrays.asList(courseTitles);
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Get access_token
		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
				.formParams("scope", "trust").when().log().all().post("/oauthapi/oauth2/resourceOwner/token").then()
				.log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");

		// get course details
		GetCourse gc = given().queryParam("access_token", accessToken).when().log().all()
				.get("/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		// print price of course SoapUI Webservices testing
		List<apiCourse> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		//print web automation courses
		ArrayList<String> a=new ArrayList<>();
		List<webAutomationCourse> web = gc.getCourses().getWebAutomation();
		for (int i = 0; i < web.size(); i++) {

			a.add(web.get(i).getCourseTitle());

		}
		Assert.assertEquals(expected, a);

	}

}
