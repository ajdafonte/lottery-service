package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.infrastructure.WrapperStorageLottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import java.time.LocalDate;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

class GetLotteriesITTest extends BaseIntegrationTest {

    private static final String DATE = "2021-04-25";
    private static final String RESPONSE_BODY = "{\"lotteries\":[{\"id\":\"e3211be6-d0cc-4718-905d-ab933cc91ecb\",\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"finished\":false,\"tickets\":[1234567,9876543]}]}";
    private static final String ID_1 = "e3211be6-d0cc-4718-905d-ab933cc91ecb";
    private static final String ID_2 = "f5671be6-d0cc-4718-905d-ab933vv778afg";
    private static final LotteryEntity LOTTERY_ENTITY_1 = new LotteryEntity(
            ID_1,
            "Lottery 1",
            LocalDate.of(2021, 4, 25),
            false,
            List.of(1234567, 9876543));
    private static final LotteryEntity LOTTERY_ENTITY_2 = new LotteryEntity(
            ID_2,
            "Lottery 2",
            LocalDate.of(2021, 4, 26),
            false,
            List.of(67890, 45678));

    @BeforeEach
    void setup() {
        WrapperStorageLottery.getLotteryStorage().clear();
    }

    @Test
    void should_retrieve_lotteries() {
        WrapperStorageLottery.getLotteryStorage().put(ID_1, LOTTERY_ENTITY_1);
        WrapperStorageLottery.getLotteryStorage().put(ID_2, LOTTERY_ENTITY_2);

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
