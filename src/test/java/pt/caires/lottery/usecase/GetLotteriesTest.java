package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.Lottery;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetLotteriesTest {

    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);

    private GetLotteries getLotteries;

    @BeforeEach
    void setUp() {
        this.getLotteries = new GetLotteries();
    }

    @Test
    void should_retrieve_lotteries() {
        List<Lottery> result = getLotteries.execute(DATE);

        assertThat(result)
                .isNotEmpty()
                .containsExactly(anExpectedLottery());
    }

    private Lottery anExpectedLottery() {
        return new Lottery(
                "e3211be6-d0cc-4718-905d-ab933cc91ecb",
                "Lottery 1",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(1234567, 9876543));
    }

}