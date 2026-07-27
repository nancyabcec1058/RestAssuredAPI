import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.payload;

public class OAuth2 {

	public static void main(String[] args) {

//		String authCoderesponse = given().log().all()
//				.queryParams("scope", "https://www.googleapis.com/auth/userinfo.email", "auth_url",
//						"https://accounts.google.com/o/oauth2/v2/auth", "client_id",
//						"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com", "response_type",
//						"code", "redirect_uri", "https://rahulshettyacademy.com/getCourse.php", "state", "de67ygr")
//				.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

		String accessTokenresponse = given().urlEncodingEnabled(false).log().all()
				.queryParams("code", "4%2F0AXEQxIACFG7GdJCO0RvT4kYB_uisiDsVv4LZWVibf0PHjMhu2qV--wb29puDNcZAq1IOMg",
						"client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com",
						"client_secret", "erZOWM9g3UtwNRj340YYaK_W", "redirect_uri",
						"https://rahulshettyacademy.com/getCourse.php", "grant_type", "authorization_code")
				.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenresponse);
		String access_token = js.getString("access_token");

		String response = given().log().all().queryParam("access_token", access_token).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);

	}

}
