import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.EcommerceLogin;
import POJO.EcommerceLoginResponse;
import POJO.EcoomerceaddOrder;
import POJO.OrderDetails;

public class EcommerceAPI {

	public static void main(String[] args) {
		
		//Login
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		EcommerceLogin login=new EcommerceLogin();
		login.setUserEmail("mukti@myemail.com");
		login.setUserPassword("Abc&xyz123");
		RequestSpecification r=given().log().all().spec(req).body(login);
		EcommerceLoginResponse response=r.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(EcommerceLoginResponse.class);
         String token=response.getToken();
         String userId=response.getUserId();
          
         //add product
          RequestSpecification addProduct=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
  				.addHeader("Authorization", token).build();
          RequestSpecification addProd=given().relaxedHTTPSValidation().log().all().spec(addProduct).formParams("productName","qwerty","productAddedBy",userId,"productCategory","fashion",
        		  "productSubCategory","shirts","productPrice","11500","productDescription","Addias Originals","productFor","women")
          .multiPart("productImage",new File("C:\\Users\\nancy\\Downloads\\Postman\\shirts.png"));
          
          String addProdResposne=addProd.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
          JsonPath js=new JsonPath(addProdResposne);
          String productId=js.getString("productId");
          //create product
          
          RequestSpecification createProduct=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
        		  .addHeader("Authorization", token).setContentType(ContentType.JSON).build();
          OrderDetails order=new OrderDetails();
          order.setCountry("India");
          order.setProductOrderedId(productId);
          
          List<OrderDetails> orderList=new ArrayList<>();
          orderList.add(order);
          
          EcoomerceaddOrder orders=new EcoomerceaddOrder();
          orders.setOrders(orderList);
          
          RequestSpecification createProd=given().log().all().spec(createProduct).body(orders);
          
          String addOrderResponse=createProd.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
          
          System.out.println(addOrderResponse);
          
          //Delete order
          
          RequestSpecification deleteProd=given().log().all().spec(addProduct).pathParam("productId", productId);
          
          String delResponse=deleteProd.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
          JsonPath j=new JsonPath(delResponse);
          Assert.assertEquals(j.getString("message"), "Product Deleted Successfully");
	}

}
