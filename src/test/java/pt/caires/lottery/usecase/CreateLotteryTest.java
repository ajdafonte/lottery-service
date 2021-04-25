package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CreateLotteryTest {

    @Mock
    private LotteryRepository lotteryRepository;

    private CreateLottery createLottery;

    @BeforeEach
    void setUp() {
        this.createLottery = new CreateLottery(lotteryRepository);
    }

    @Test
    void should_create_a_lottery() {
        Lottery lottery = new Lottery(
                "id",
                "name",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(123, 456));
        given(lotteryRepository.save(lottery)).willReturn(lottery);

        Lottery result = createLottery.execute(lottery);

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
                List.of(123, 456));
    }

}