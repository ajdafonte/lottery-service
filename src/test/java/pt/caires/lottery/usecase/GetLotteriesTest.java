package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetLotteriesTest {

    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final Lottery LOTTERY_1 = new Lottery(
            "id",
            "name",
            LocalDate.of(2021, 4, 25),
            false,
            List.of(123, 456));

    @Mock
    private LotteryFinder lotteryFinder;

    private GetLotteries getLotteries;

    @BeforeEach
    void setUp() {
        this.getLotteries = new GetLotteries(lotteryFinder);
    }

    @Test
    void should_retrieve_all_lotteries() {
        given(lotteryFinder.findAll()).willReturn(List.of(LOTTERY_1));

        List<Lottery> result = getLotteries.execute(null);

        assertThat(result)
                .isNotEmpty()
                .containsExactly(anExpectedLottery());
    }

    @Test
    void should_retrieve_lotteries_by_date() {
        given(lotteryFinder.findAllBy(DATE)).willReturn(List.of(LOTTERY_1));

        List<Lottery> result = getLotteries.execute(DATE);

        assertThat(result)
                .isNotEmpty()
                .containsExactly(anExpectedLottery());
    }

    private Lottery anExpectedLottery() {
        return new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
    }

}