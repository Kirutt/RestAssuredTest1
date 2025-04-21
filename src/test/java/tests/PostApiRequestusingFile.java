package tests;

import Utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.path.json.JsonPath.*;

public class PostApiRequestusingFile {
    @Test
    public void postApiRequest() throws IOException {
        //Convert the data in the text file to string and the pass it as body to methdo
        String PostRequestBody=FileUtils.readFileToString(new File(Constants.Post_Api_Request_Body),"UTF-8");
        System.out.println(PostRequestBody);
        Response response=RestAssured.given().contentType(ContentType.JSON).body(PostRequestBody).baseUri("https://restful-booker.herokuapp.com/booking").
                when().post().then().assertThat().statusCode(200).extract().response();
        //Taking the respone data from the json objects.Here we are validating the first name
        com.jayway.jsonpath.JsonPath.read(response.body().asString(),"$.booking.firstname");
        Object result=com.jayway.jsonpath.JsonPath.read(response.body().asString(),"$.booking.firstname");
        System.out.println(result);
        Assert.assertEquals(result,"api testing");
        Object result1=com.jayway.jsonpath.JsonPath.read(response.body().asString(),"$.booking.lastname");
        Assert.assertEquals(result1,"tutorial");
        Object bookingid= com.jayway.jsonpath.JsonPath.read(response.body().asString(),"$.bookingid");
        int bookingid1=Integer.parseInt(bookingid.toString());
        //now the get method for getting the booking details
        //booking id details will be fetched from line number 33 And we will apply api chaining
        RestAssured.given().contentType(ContentType.JSON).baseUri("https://restful-booker.herokuapp.com/booking").
                when().get("/{bookingid}",bookingid1).then().assertThat().statusCode(200);

    }
}
