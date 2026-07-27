import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;

import Files.payload;

public class JiraBug {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://nancyec1058.atlassian.net";
		// Add Issue
		String response = given()
				.header("Content-Type", "application/json")
				.header("Authorization",
						"Basic bmFuY3llYzEwNThAZ21haWwuY29tOkFUQVRUM3hGZkdGMEZvbFVidldMOTVOUTdBUXUyMVR4NlFYaEo2WWhEOW5jeGVBbmN0dHltckx1YTVBd2ZyckFFM2ExNUUtRE8zTW56ajBQMU9UaVh5OXNLTFJOcHkzakFvY2hLODJZamVNSkY2MkVJRmFRN1FkN0w0VWZId1hhTGtUc29YR2I2Q0tvdmtndlFoLTVrWUVaSzlsaVE0WjB5TGZBdGcxM0hCelJ5Z21PUTNSSnJ1WT1FRDQxMjkwQQ==")
				.body(payload.addJiraIssue()).when().post("/rest/api/3/issue").then().assertThat().statusCode(201)
				.extract().response().asString();

		JsonPath js = new JsonPath(response);
		String issueId = js.getString("id");
		System.out.println(issueId);

		// Add attachment
		given().log().all().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header("Authorization",
				"Basic bmFuY3llYzEwNThAZ21haWwuY29tOkFUQVRUM3hGZkdGMEZvbFVidldMOTVOUTdBUXUyMVR4NlFYaEo2WWhEOW5jeGVBbmN0dHltckx1YTVBd2ZyckFFM2ExNUUtRE8zTW56ajBQMU9UaVh5OXNLTFJOcHkzakFvY2hLODJZamVNSkY2MkVJRmFRN1FkN0w0VWZId1hhTGtUc29YR2I2Q0tvdmtndlFoLTVrWUVaSzlsaVE0WjB5TGZBdGcxM0hCelJ5Z21PUTNSSnJ1WT1FRDQxMjkwQQ==")
				.multiPart("file", new File("C:\\Users\\nancy\\Downloads\\Postman\\JiraIssue.png")).when()
				.post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		// get issue
		given().log().all().header("Content-Type", "application/json").header("Authorization",
				"Basic bmFuY3llYzEwNThAZ21haWwuY29tOkFUQVRUM3hGZkdGMEZvbFVidldMOTVOUTdBUXUyMVR4NlFYaEo2WWhEOW5jeGVBbmN0dHltckx1YTVBd2ZyckFFM2ExNUUtRE8zTW56ajBQMU9UaVh5OXNLTFJOcHkzakFvY2hLODJZamVNSkY2MkVJRmFRN1FkN0w0VWZId1hhTGtUc29YR2I2Q0tvdmtndlFoLTVrWUVaSzlsaVE0WjB5TGZBdGcxM0hCelJ5Z21PUTNSSnJ1WT1FRDQxMjkwQQ==")
				.when().get("/rest/api/3/issue/"+issueId+"").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath j = new JsonPath(response);
		String Id = j.getString("id");
		// String image=j.getString("fields.attachment[0].filename");
		Assert.assertEquals(issueId, Id);
		// Assert.assertEquals("JiraIssue.png", image);

		// delete attachment
		given().log().all().header("Authorization",
				"Basic bmFuY3llYzEwNThAZ21haWwuY29tOkFUQVRUM3hGZkdGMEZvbFVidldMOTVOUTdBUXUyMVR4NlFYaEo2WWhEOW5jeGVBbmN0dHltckx1YTVBd2ZyckFFM2ExNUUtRE8zTW56ajBQMU9UaVh5OXNLTFJOcHkzakFvY2hLODJZamVNSkY2MkVJRmFRN1FkN0w0VWZId1hhTGtUc29YR2I2Q0tvdmtndlFoLTVrWUVaSzlsaVE0WjB5TGZBdGcxM0hCelJ5Z21PUTNSSnJ1WT1FRDQxMjkwQQ==")
				.when().delete("/rest/api/3/issue/"+issueId +"").then().log().all().assertThat().statusLine("204 No Content")
				.extract().response().asString();
	}

}
