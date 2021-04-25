package pt.caires.lottery.api.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.api.v1.mapper.CreateLotteryV1DTOToLotteryMapper;
import pt.caires.lottery.api.v1.mapper.LotteryToLotteryV1DTOMapper;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.usecase.CreateLottery;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LotteryV1ControllerTest {

    private static final CreateLotteryV1DTO CREATE_LOTTERY_DTO = new CreateLotteryV1DTO(
            "Lottery 1",
            LocalDate.of(2021, 4, 25),
            2);
    private static final Lottery LOTTERY = new Lottery(
            "id",
            "name",
            LocalDate.of(2021, 4, 25),
            false,
            List.of(123, 456));
    private static final LotteryV1DTO LOTTERY_DTO = new LotteryV1DTO(
            "id",
            "name",
            LocalDate.of(2021, 4, 25),
            false,
            List.of(123, 456));

    @Mock
    private CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper;
    @Mock
    private CreateLottery createLottery;
    @Mock
    private LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;

    private LotteryV1Controller lotteryV1Controller;

    @BeforeEach
    void setUp() {
        this.lotteryV1Controller = new LotteryV1Controller(
                createLotteryV1DTOToLotteryMapper,
                createLottery,
                lotteryToLotteryV1DTOMapper);
    }

    @Test
    void should_create_a_lottery() {
        given(createLotteryV1DTOToLotteryMapper.map(CREATE_LOTTERY_DTO)).willReturn(LOTTERY);
        given(createLottery.execute(LOTTERY)).willReturn(LOTTERY);
        given(lotteryToLotteryV1DTOMapper.map(LOTTERY)).willReturn(LOTTERY_DTO);

        LotteryV1DTO result = lotteryV1Controller.createLottery(CREATE_LOTTERY_DTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryV1DTO());
    }

    private LotteryV1DTO anExpectedLotteryV1DTO() {
        return new LotteryV1DTO(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
    }

}