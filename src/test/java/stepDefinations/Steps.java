package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Steps {
    public static String token = "Bearer BQDZpQs1BDs7nSXW8-1Q669DJsQLth2RMp0hm0l88phTY81GLmGYHN9g09BhQgorQNj1F02KPTvW_WKrzkrjjkfX3hvOIHwUa3eme1oa5l8qtlyWgBHiCbeFeieBfbDvnQI-HlxmhZv8bmFg1PaymBIl-4wLlfbRinMdUNR9R74BX_cXJOY6ftxBvNfCwTmH5ULBjNKno591EaXF9lPW2Vw12vWd2V8ujd5Ew6AwEhxD8Abxbi-uvwB1T3OpoJFUhAXOC3mB_m5VKHFhDnEEFMWiVfAnC47Vlbg-CZJn";
    public static Response response;
    public static String plyName;
    static RequestSpecification httpRequest;

    @Given("I have authorization details")
    public void iAmAnAuthorizedUser() {
        httpRequest = RestAssured.given();
        // contentType will provide content type of specified request, here it is JSON
        httpRequest.contentType(ContentType.JSON);
        // Specify a header that'll be sent with the request, here we are passing Authorization token in header
        httpRequest.header("Authorization", token);
        // it will specify response of a request made by REST Assured, specify the method type (GET) and the parameters if any
//        response = httpRequest.request(Method.GET,"https://api.spotify.com/v1/me");
//        // it will provide the response in the pretty format
//        response.prettyPrint();
//        // and validating the results with status codes
//        int statusCode = response.getStatusCode();
//        System.out.println("status code:" +statusCode);
    }

    @When("I add a tracks")
    public void addTracksToPlaylist_StatusCodeReturn201() {
        httpRequest.queryParam("uris","spotify:track:6U6Lieem2VpReKCxyvDeRs","spotify:track:0Oe49j06Bjrxs8PltuVeaW");
        response = httpRequest.request(Method.POST,"https://api.spotify.com/v1/playlists/36H9cOCAgBCYF5k7U7Feod/tracks");
        response.prettyPrint();
        //int statusCode = response.getStatusCode();
        //Assert.assertEquals(statusCode, 201);
    }

    @Then("The track is added")
    public void bookIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Given("I have current user playlist details")
    public void iAmAnAuthorized1User() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization", token);
        response = httpRequest.request(Method.GET,"https://api.spotify.com/v1/me/playlists");
        response.prettyPrint();
    }

    @When("I am matching with playlist name")
    public void matchWithPlaylistName() {
        JsonPath jsonPathObj = response.jsonPath();
        plyName = jsonPathObj.get("items[1].name");
        System.out.println("playlist name:" +plyName);
    }

    @Then("The playlist name is matched")
    public void playlistNameMatched() {
        Assert.assertEquals(plyName, "New Playlist13");
    }
}
