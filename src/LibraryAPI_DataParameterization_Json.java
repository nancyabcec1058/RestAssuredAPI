import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;

public class LibraryAPI_DataParameterization_Json {

	
	@Test
	public void addDeleteBook() {
		HashMap<String,Object> map=new HashMap<>();
		map.put("name", "Learn API");
		map.put("isbn", "der");
		map.put("aisle", "456");
		map.put("author", "Rahul");
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(map).when().post("/Library/Addbook.php").then().log().all().assertThat()
				.statusCode(200).body("Msg", equalTo("successfully added")).extract().response().asString();
		JsonPath js = new JsonPath(response);
		String bookId = js.getString("ID");

		// delete book
		given().log().all().header("Content-Type", "application/json").body(payload.deleteBook(bookId)).when()
				.post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("book is successfully deleted"));
	}

}
