import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ExcelUtility;
import Files.payload;

public class LibraryAPI_DataParameterization_Excel {

	@Test

	public void addBook() throws Exception {

		ExcelUtility excel = new ExcelUtility("C:\\Users\\nancy\\eclipse-workspace\\RestAssuredPractice\\Library.xlsx");

		String name = excel.getCellData(1, 0);
		String isbn = excel.getCellData(1, 1);
		String aisle = excel.getCellData(1, 2);
		String author = excel.getCellData(1, 3);

		HashMap<String, Object> map = new HashMap<>();

		map.put("name", name);
		map.put("isbn", isbn);
		map.put("aisle", aisle);
		map.put("author", author);

		RestAssured.baseURI = "https://rahulshettyacademy.com";

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
		

		excel.setCellData(1, 4, bookId);

	}

}
