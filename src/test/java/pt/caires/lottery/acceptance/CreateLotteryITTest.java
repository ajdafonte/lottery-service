package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.is;

class CreateLotteryITTest extends BaseIntegrationTest {

    private static final String REQUEST_BODY = "{\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"numberTickets\":2}";
    private static final String RESPONSE_BODY = "{\"id\":\"e3211be6-d0cc-4718-905d-ab933cc91ecb\",\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"finished\":false,\"tickets\":[1234567,9876543]}";

    @Test
    void should_create_a_lottery() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(REQUEST_BODY)
                .when()
                .post("v1/lotteries")
                .then()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .contentType(ContentType.JSON)
                .body(is(RESPONSE_BODY));
    }

}
