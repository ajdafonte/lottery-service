package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.Lottery;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CreateLotteryTest {

    private CreateLottery createLottery;

    @BeforeEach
    void setUp() {
        this.createLottery = new CreateLottery();
    }

    @Test
    void should_create_a_lottery() {
        Lottery lottery = new Lottery("e3211be6-d0cc-4718-905d-ab933cc91ecb",
                "Lottery 1",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(1234567, 9876543));

        Lottery result = createLottery.execute(lottery);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLottery());
    }

    private Lottery anExpectedLottery() {
        return new Lottery("e3211be6-d0cc-4718-905d-ab933cc91ecb",
                "Lottery 1",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(1234567, 9876543));
    }

}