package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.caires.lottery.domain.shared.Clock;
import pt.caires.lottery.infrastructure.WrapperStorageLottery;
import pt.caires.lottery.infrastructure.WrapperStorageLotteryPurchaseEvent;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.entity.LotteryPurchaseEventEntity;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

public class PurchaseLotteryTicketsITTest extends BaseIntegrationTest {

    private static final String REQUEST_BODY = "{\"userId\":\"ec16959e-a5c7-11eb-bcbc-0242ac130002s\",\"tickets\":[1234567]}";
    private static final String ID = "e3211be6-d0cc-4718-905d-ab933cc91ecb";
    private static final LotteryEntity LOTTERY_ENTITY = new LotteryEntity(
            ID,
            "Lottery 1",
            LocalDate.of(2021, 4, 25),
            false,
            List.of(1234567, 9876543));
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);
    private static final String USER_ID = "ec16959e-a5c7-11eb-bcbc-0242ac130002s";
    private static final LotteryPurchaseEventEntity LOTTERY_PURCHASE_EVENT_ENTITY = new LotteryPurchaseEventEntity(
            ID,
            USER_ID,
            List.of(1234567),
            OCCURRED_AT);

    @MockBean
    private Clock clock;

    @BeforeEach
    void setup() {
        WrapperStorageLottery.getLotteryStorage().clear();
        WrapperStorageLotteryPurchaseEvent.getLotteryPurchaseEventsStorage().clear();
    }

    @Test
    void should_purchase_lottery_tickets() {
        WrapperStorageLottery.getLotteryStorage().put(ID, LOTTERY_ENTITY);
        given(clock.now()).willReturn(OCCURRED_AT);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(REQUEST_BODY)
                .when()
                .pathParam("id", ID)
                .post("v1/lotteries/{id}/purchase")
                .then()
                .statusCode(HttpURLConnection.HTTP_NO_CONTENT)
                .body(is(emptyString()));

        assertThat(WrapperStorageLotteryPurchaseEvent.getLotteryPurchaseEventsStorage().get(OCCURRED_AT))
                .isNotNull()
                .isEqualTo(LOTTERY_PURCHASE_EVENT_ENTITY);
    }

}
