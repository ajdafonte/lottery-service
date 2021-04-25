package pt.caires.lottery.infrastructure.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryToLotteryEntityMapperTest {

    private LotteryToLotteryEntityMapper lotteryToLotteryEntityMapper;

    @BeforeEach
    void setUp() {
        this.lotteryToLotteryEntityMapper = new LotteryToLotteryEntityMapper();
    }

    @Test
    void should_map() {
        Lottery lottery = new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));

        LotteryEntity result = lotteryToLotteryEntityMapper.map(lottery);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryEntity());
    }

    private LotteryEntity anExpectedLotteryEntity() {
        return new LotteryEntity(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
    }

}