package pt.caires.lottery.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryV1ControllerTest {

    private static final CreateLotteryV1DTO CREATE_LOTTERY_DTO = new CreateLotteryV1DTO(
            "Lottery 1",
            LocalDate.of(2021, 4, 25),
            2);

    private LotteryV1Controller lotteryV1Controller;

    @BeforeEach
    void setUp() {
        this.lotteryV1Controller = new LotteryV1Controller();
    }

    @Test
    void should_create_a_lottery() {
        LotteryV1DTO result = lotteryV1Controller.createLottery(CREATE_LOTTERY_DTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryV1DTO());
    }

    private LotteryV1DTO anExpectedLotteryV1DTO() {
        return new LotteryV1DTO(
                "e3211be6-d0cc-4718-905d-ab933cc91ecb",
                "Lottery 1",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(1234567, 9876543));
    }

}