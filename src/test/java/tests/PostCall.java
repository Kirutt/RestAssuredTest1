package tests;

import Utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import net.minidev.json.JSONObject;

public class PostCall extends BaseTest {
    @Test
    public void createBooking(){
        //preapre request body
        JSONObject booking = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        booking.put("firstname","api testing");
        booking.put("lastname","tutorial");
        booking.put("totalprice",1000);
        booking.put("depositpaid",true);
        booking.put("additionalneeds","super bowls");
        booking.put("bookingdates",bookingDates);
        bookingDates.put("checkin","2025-03-03");
        bookingDates.put("checkout","2025-03-04");
        Response response=
        RestAssured.given().contentType(ContentType.JSON).body(booking.toString()).
                baseUri("https://restful-booker.herokuapp.com/booking").
                when().post().then().
                assertThat().statusCode(200).body("booking.firstname", Matchers.equalTo("api testing")).
                body("booking.lastname",Matchers.equalTo("tutorial")).
                body("booking.totalprice",Matchers.equalTo(1000)).
                body("booking.bookingdates.checkin",Matchers.equalTo("2025-03-03")).extract().response()
                ;
        //storing the booking id from the response so that it can pass in to another method
        int bookingid=response.path("bookingid");
        System.out.println(bookingid);
        //Calling the next method for validate wether the booking is got created or not
        RestAssured.given().contentType(ContentType.JSON).pathParam("bookingid",bookingid).
                baseUri("https://restful-booker.herokuapp.com/booking").
                //validating the status code using assertion
                when().get("{bookingid}").then().assertThat().statusCode(200).
                //validating the responses in the body using matchers method
                body("firstname",Matchers.equalTo("api testing")).
                body("totalprice",Matchers.equalTo(1000))
                ;


    }
}
