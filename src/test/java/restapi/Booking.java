package restapi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;


public class Booking {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		// getbookingids
		/*String st = given().header("Content-Type", "application/json").when().get("booking").then().statusCode(200)
				.extract().response().asString();*/
		//System.out.println(st);
//create	
		String cBooking = given().header("Content-Type", "application/json")
				.body("{\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\n" + "}")
				.when().post("booking").then().statusCode(200).extract().response().asString();
		System.out.println(cBooking);
		System.out.println("-----------------------------");

		JsonPath js = new JsonPath(cBooking);
		String bookingid = js.getString("bookingid");
		System.out.println(bookingid);
		System.out.println("------------------------------");
//update
//for updating information we have to generate token
		String jsontoken = given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"passw"
						+ "ord123\"\r\n" + "}")
				.when().post("auth").then().statusCode(200).extract().response().asString();
		System.out.println(jsontoken);
		System.out.println("------------------------------");


//now fetch the token using json class
		JsonPath js1 = new JsonPath(jsontoken);
		String token = js1.getString("token");
		System.out.println(token);

		String update = given().header("Content-Type", " application/json").header("Accept", " application/json")
				.header("Cookie", " token=" + token)
				.body("{\r\n"
						+ "    \"firstname\": \"James\",\r\n"
						+ "    \"lastname\": \"Brown\",\r\n"
						+ "    \"totalprice\": 111,\r\n"
						+ "    \"depositpaid\": true,\r\n"
						+ "    \"bookingdates\": {\r\n"
						+ "        \"checkin\": \"2018-01-01\",\r\n"
						+ "        \"checkout\": \"2019-01-01\"\r\n"
						+ "    },\r\n"
						+ "    \"additionalneeds\": \"Breakfast\"\r\n"
						+ "}")
				.when().put("booking/"+bookingid).then().statusCode(200).extract().response().asString();
		System.out.println(update);
		System.out.println("------------------------------");

		//patch
		
//update
//for updating information we have to generate token
		String jsontoken1= given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"passw"
						+ "ord123\"\r\n" + "}")
				.when().post("auth").then().statusCode(200).extract().response().asString();
		System.out.println(jsontoken1);
		System.out.println("------------------------------");


//now fetch the token using json class
		JsonPath js2 = new JsonPath(jsontoken1);
		String token1 = js1.getString("token");
		System.out.println(token);

		String patch = given().header("Content-Type", " application/json").header("Accept", " application/json")
				.header("Cookie", " token=" + token)
				.body("{\r\n"
						+ "    \"firstname\" : \"Jamuna\",\r\n"
						+ "    \"lastname\" : \"Borade\"}")
				.when().patch("booking/"+bookingid).then().statusCode(200).extract().response().asString();
		System.out.println(patch);
		System.out.println("------------------------------");

	}
}
