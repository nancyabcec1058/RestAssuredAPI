import Files.payload;
import io.restassured.path.json.JsonPath;

public class jsonParsing {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(payload.coursePrice());
		// print no of courses count
		int courseCount = js.getInt("courses.size()");
		System.out.println("total number of courses:" + courseCount);
		// print purchase amount
		System.out.println("purchase amount:" + js.getInt("dashboard.purchaseAmount"));
		// print title of first course
		System.out.println("title of first course:" + js.getString("courses[0].title"));
		// print all course title and their prices
		for (int i = 0; i < courseCount; i++) {
			System.out.println("Title=>" + js.getString("courses[" + i + "].title") + " Price=>"
					+ js.getInt("courses[" + i + "].price"));
		}
		// print no of copies for RPA course
		for (int i = 0; i < courseCount; i++) {
			if (js.getString("courses[" + i + "].title").equals("RPA")) {
				System.out.println("No of RPA course copies:" + js.getString("courses[" + i + "].copies"));
				break;
			}
		}
		//Verify sum of copies*prices is equal to purchase amount
		
	}

}
