package pt.caires.lottery.infrastructure.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryEntityToLotteryMapperTest {

    private LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;

    @BeforeEach
    void setUp() {
        this.lotteryEntityToLotteryMapper = new LotteryEntityToLotteryMapper();
    }

    @Test
    void should_map() {
        LotteryEntity lottery = new LotteryEntity(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456),
                123);

        Lottery result = lotteryEntityToLotteryMapper.map(lottery);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLottery());
    }

    private Lottery anExpectedLottery() {
        return new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456),
                123);
    }

}