package pt.caires.lottery.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import pt.caires.lottery.domain.shared.IdentifierGenerator;
import pt.caires.lottery.domain.shared.Randomizer;
import pt.caires.lottery.infrastructure.WrapperStorageLottery;

import java.net.HttpURLConnection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

class CreateLotteryITTest extends BaseIntegrationTest {

    private static final String ID = "e3211be6-d0cc-4718-905d-ab933cc91ecb";
    private static final String REQUEST_BODY = "{\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"numberTickets\":2}";
    private static final String RESPONSE_BODY = "{\"id\":\"e3211be6-d0cc-4718-905d-ab933cc91ecb\",\"name\":\"Lottery 1\",\"date\":\"2021-04-25\",\"finished\":false,\"tickets\":[1234567,9876543]}";

    @MockBean
    private IdentifierGenerator identifierGenerator;
    @MockBean
    private Randomizer randomizer;

    @BeforeEach
    void setup() {
        WrapperStorageLottery.getLotteryStorage().clear();
    }

    @Test
    void should_create_a_lottery() {
        given(identifierGenerator.generate()).willReturn(ID);
        given(randomizer.getPositiveValues(2)).willReturn(List.of(1234567, 9876543));

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(REQUEST_BODY)
                .when()
                .post("v1/lotteries")
                .then()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .contentType(ContentType.JSON)
                .body(is(RESPONSE_BODY));

        assertThat(WrapperStorageLottery.getLotteryStorage().containsKey(ID)).isTrue();
    }

}
