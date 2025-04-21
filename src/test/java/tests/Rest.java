package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class Rest {
    @Test
    public void getAllBookings(){
        String response= String.valueOf(RestAssured.given().contentType(ContentType.JSON).
                baseUri("https://restful-booker.herokuapp.com/booking").when().
                get().then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").
                header("Content-Type","application/json; charset=utf-8").
                extract().response().asString());
        Assert.assertTrue(response.contains("bookingid"));
        System.out.println(response);
    }
}
