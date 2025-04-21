package tests;

import Pojos.Booking;
import Pojos.BookingDates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.Test.*;


public class PostApiRequestUsingPojos {

    @Test
    public void postapipojos() throws JsonProcessingException {
        BookingDates bookingDates=new BookingDates("2023-03-05","2023-06-06");
        Booking booking = new Booking("api testing","tutorial","breakfast",1000,true,bookingDates);
        ObjectMapper objectMapper=new ObjectMapper();
         String requestBody=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
         //here the requestBody holding the json body as string type of data
        System.out.println(requestBody);
        //deserialisation
        Booking booking1=objectMapper.readValue(requestBody,Booking.class);
        System.out.println(booking1.getFirstname());
        System.out.println(booking1.getTotalprice());
        System.out.println(booking1.getBookingdates().getCheckin());
        System.out.println(booking1.getBookingdates().getCheckout());
        Response response=RestAssured.given().contentType(ContentType.JSON).baseUri("https://restful-booker.herokuapp.com/booking")
                .body(requestBody).when().post().then()
                .assertThat().statusCode(200).extract().response();
        int bookingid= response.path("bookingid");
        RestAssured.given().contentType(ContentType.JSON).baseUri("https://restful-booker.herokuapp.com/booking").
                when().get("/{bookingid}",bookingid).then().assertThat().statusCode(200);



    }
}
 