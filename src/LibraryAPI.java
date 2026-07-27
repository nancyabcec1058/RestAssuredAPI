import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.payload;

public class LibraryAPI {
	
	String bookId;
	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
	}
	@Test
	public void addBook()
	{
		
		String response=given().log().all().header("Content-Type","application/json").body(payload.addBook("rtu","590"))
		.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).body("Msg",equalTo("successfully added"))
		.extract().response().asString();
		JsonPath js=new JsonPath(response);
		bookId=js.getString("ID");
	}
	@Test
	public void deleteBook()
	{
		given().log().all().header("Content-Type","application/json").body(payload.deleteBook(bookId))
		.when().post("/Library/DeleteBook.php").then().log().all().assertThat().statusCode(200)
		.body("msg",equalTo("book is successfully deleted"));
	}

}
