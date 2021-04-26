package pt.caires.lottery.api.v1.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class LotteryV1DTOToJsonITTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final List<Integer> TICKETS = List.of(123);

    @Inject
    private JacksonTester<LotteryV1DTO> jacksonTester;

    @Test
    void should_serialize() throws IOException {
        String expected = "{\"id\":\"id\",\"name\":\"name\",\"date\":\"2021-04-25\",\"finished\":false,\"tickets\":[123]}";
        LotteryV1DTO lotteryV1DTO = new LotteryV1DTO(ID, NAME, DATE, false, TICKETS);

        JsonContent<LotteryV1DTO> result = jacksonTester.write(lotteryV1DTO);

        assertThat(result)
                .isNotNull()
                .isEqualToJson(expected);
    }

    @Test
    void should_serialize_with_winning_ticket() throws IOException {
        String expected = "{\"id\":\"id\",\"name\":\"name\",\"date\":\"2021-04-25\",\"finished\":true,\"tickets\":[123],\"winningTicket\":123}";
        LotteryV1DTO lotteryV1DTO = new LotteryV1DTO(ID, NAME, DATE, true, TICKETS, 123);

        JsonContent<LotteryV1DTO> result = jacksonTester.write(lotteryV1DTO);

        assertThat(result)
                .isNotNull()
                .isEqualToJson(expected);
    }

}