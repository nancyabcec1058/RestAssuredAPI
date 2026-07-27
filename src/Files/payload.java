package Files;

public class payload {
	public static String addPlacePayload()
	{
	 return "{\r\n"
	 		+ "  \"location\": {\r\n"
	 		+ "    \"lat\": -47.383494,\r\n"
	 		+ "    \"lng\": 50.427362\r\n"
	 		+ "  },\r\n"
	 		+ "  \"accuracy\": 60,\r\n"
	 		+ "  \"name\": \"Mosaic house\",\r\n"
	 		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
	 		+ "  \"address\": \"29, Bahamas beach, korea 09\",\r\n"
	 		+ "  \"types\": [\r\n"
	 		+ "    \"shoe park\",\r\n"
	 		+ "    \"shop\"\r\n"
	 		+ "  ],\r\n"
	 		+ "  \"website\": \"http://google.com\",\r\n"
	 		+ "  \"language\": \"French-IN\"\r\n"
	 		+ "}\r\n"
	 		+ "";	
	}
	public static String coursePrice()
	{
		return "{\r\n"
				+ "    \"dashboard\": {\r\n"
				+ "        \"purchaseAmount\": 910,\r\n"
				+ "        \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "    },\r\n"
				+ "    \"courses\": [\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Selenium Python\",\r\n"
				+ "            \"price\": 50,\r\n"
				+ "            \"copies\": 6\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"Cypress\",\r\n"
				+ "            \"price\": 40,\r\n"
				+ "            \"copies\": 4\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"title\": \"RPA\",\r\n"
				+ "            \"price\": 45,\r\n"
				+ "            \"copies\": 10\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
	}
	public static String addBook(String isbn,String aisle)
	{
		String Payload="{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John foe\"\r\n"
				+ "}\r\n"
				+ " \r\n"
				+ "";
		return Payload;
	}
	public static String deleteBook(String id)
	{
		return "{\r\n"
				+ "    \"ID\": \""+id+"\"\r\n"
				+ "}";
	}
	public static String addJiraIssue()
	{
		return "{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"Text field not working\",\r\n"
				+ "    \"description\": {\r\n"
				+ "      \"type\": \"doc\",\r\n"
				+ "      \"version\": 1,\r\n"
				+ "      \"content\": [\r\n"
				+ "        {\r\n"
				+ "          \"type\": \"paragraph\",\r\n"
				+ "          \"content\": [\r\n"
				+ "            {\r\n"
				+ "              \"type\": \"text\",\r\n"
				+ "              \"text\": \"Creating of an issue using project keys and issue type names using the REST API\"\r\n"
				+ "            }\r\n"
				+ "          ]\r\n"
				+ "        }\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
	}

}
