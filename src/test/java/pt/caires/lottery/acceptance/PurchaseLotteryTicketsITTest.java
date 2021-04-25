package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class PurchaseLotteryTicketsITTest extends BaseIntegrationTest {

    private static final String REQUEST_BODY = "{\"userId\":\"ec16959e-a5c7-11eb-bcbc-0242ac130002s\",\"tickets\":[1234567]}";

    @Test
    void should_purchase_lottery_tickets() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(REQUEST_BODY)
                .when()
                .pathParam("id", "ec16959e-a5c7-11eb-bcbc-0242ac130002s")
                .post("v1/lotteries/{id}/purchase")
                .then()
                .statusCode(HttpURLConnection.HTTP_NO_CONTENT)
                .body(is(emptyString()));
    }

}
