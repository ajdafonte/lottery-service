package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

class GetLotteriesITTest extends BaseIntegrationTest {

    private static final String DATE = "2021-04-25";
    private static final String RESPONSE_BODY = "{\"lotteries\":[{\"id\":\"e3211be6-d0cc-4718-905d-ab933cc91ecb\",\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"finished\":false,\"tickets\":[1234567,9876543]}]}";

    @Test
    void should_retrieve_lotteries() {
        RestAssured.given()
                .when()
                .queryParam("date", DATE)
                .get("v1/lotteries")
                .then()
                .statusCode(HTTP_OK)
                .contentType(ContentType.JSON)
                .body(is(RESPONSE_BODY));
    }

}
