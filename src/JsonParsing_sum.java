import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class JsonParsing_sum {
	@Test
	public void sumofCouses()
	{
		JsonPath js = new JsonPath(payload.coursePrice());
		int courseCount = js.getInt("courses.size()");
		int totalAmount=0;
		int sum=0;
		for(int i=0;i<courseCount;i++)
		{
			totalAmount=(js.getInt("courses[" + i + "].price"))*(js.getInt("courses[" + i + "].copies"));
			sum=sum+totalAmount;
            System.out.println(sum);
		}
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		Assert.assertEquals(purchaseAmount, sum);
	}

}
