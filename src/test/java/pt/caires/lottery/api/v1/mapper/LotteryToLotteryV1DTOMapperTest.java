package pt.caires.lottery.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.domain.Lottery;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryToLotteryV1DTOMapperTest {

    private LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;

    @BeforeEach
    void setUp() {
        this.lotteryToLotteryV1DTOMapper = new LotteryToLotteryV1DTOMapper();
    }

    @Test
    void should_map() {
        Lottery lottery = new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));

        LotteryV1DTO result = lotteryToLotteryV1DTOMapper.map(lottery);

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