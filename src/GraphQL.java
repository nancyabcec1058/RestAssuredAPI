import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;

import Files.payload;

public class GraphQL {

	public static void main(String[] args) {

		// Mutation

		String locResponse = given().log().all().contentType("application/json").body(
				"{\"query\":\"mutation($locationName:String!)\\n{\\n  createLocation(location:{name:$locationName,type:\\\"North-east\\\",dimension:\\\"456\\\"})\\n  {\\n    id\\n  }\\n}\\n\",\"variables\":{\"locationName\":\"Jeju Island\"}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().extract().response()
				.asString();
		JsonPath js = new JsonPath(locResponse);
		int locationId = js.getInt("data.createLocation.id");

		String charresponse = given().log().all().contentType("application/json").body(
				"{\"query\":\"mutation($characterName:String!,$episodeName:String!)\\n{\\n  createCharacter(character:{name:$characterName,type:\\\"abc\\\",status:\\\"alive\\\",species:\\\"human\\\",gender:\\\"female\\\",image:\\\"jpg\\\",originId:567,locationId:"
						+ locationId
						+ "})\\n  {\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"02-09-1996\\\",episode:\\\"25\\\"})\\n  {\\n    id\\n  }\\n}\",\"variables\":{\"characterName\":\"Alice Susan\",\"episodeName\":\"The Night Party\"}}\r\n"
						+ "")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().extract().response()
				.asString();
		JsonPath j = new JsonPath(charresponse);
		int characterId = j.getInt("data.createCharacter.id");
		int episodeId = j.getInt("data.createEpisode.id");

		// query
		String response2 = given().log().all().contentType("application/json").body(
				"{\"query\":\"query($characterId:Int!,$episodeId:Int!,$locationId:Int!)\\n{\\n  character(characterId: $characterId) {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: $locationId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters:{name:\\\"s f\\\"})\\n  {\\n    info\\n    {\\n      count\\n    }\\n    result\\n    {\\n      name\\n      type\\n    }\\n    \\n  }\\n  episodes(filters:{name:\\\"df dh\\\"})\\n  {\\n    result\\n    {\\n      id\\n      name\\n      air_date\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":"
						+ characterId + ",\"episodeId\":" + episodeId + ",\"locationId\":" + locationId + "}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().extract().response()
				.asString();

		JsonPath a = new JsonPath(response2);
		String charName = a.getString("data.character.name");
		Assert.assertEquals("Alice Susan", charName);
		String locName = a.getString("data.location.name");
		Assert.assertEquals("Jeju Island", locName);
		String episodeName = a.getString("data.episode.name");
		Assert.assertEquals("The Night Party", episodeName);

		// delete
		String delResponse = given().log().all().contentType("application/json").body(
				"{\"query\":\"mutation($locationIds:[Int!],$episodeIds:[Int!],$characterIds:[Int!])\\n{\\n  \\n  deleteLocations(locationIds:$locationIds)\\n  {\\n    locationsDeleted\\n  }\\n  deleteCharacters(characterIds:$characterIds)\\n  {\\n    charactersDeleted\\n  }\\n  deleteEpisodes(episodeIds:$episodeIds)\\n  {\\n    episodesDeleted\\n  }\\n}\",\"variables\":{\"locationIds\":["
						+ locationId + "],\"episodeIds\":[" + episodeId + "],\"characterIds\":[" + characterId + "]}}")
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().log().all().extract().response()
				.asString();

	}

}
