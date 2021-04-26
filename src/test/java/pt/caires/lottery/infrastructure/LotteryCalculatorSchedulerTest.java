package pt.caires.lottery.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.shared.Clock;
import pt.caires.lottery.usecase.CalculateLotteriesWinner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LotteryCalculatorSchedulerTest {

    @Mock
    private CalculateLotteriesWinner calculateLotteriesWinner;
    @Mock
    private Clock clock;

    private LotteryCalculatorScheduler lotteryCalculatorScheduler;

    @BeforeEach
    void setUp() {
        this.lotteryCalculatorScheduler = new LotteryCalculatorScheduler(calculateLotteriesWinner, clock);
    }

    @Test
    void should_calculate_lotteries_winner() {
        given(clock.now()).willReturn(LocalDateTime.of(2021, 4, 26, 0, 0, 0));

        lotteryCalculatorScheduler.calculateLotteriesWinner();

        verify(calculateLotteriesWinner).execute(LocalDate.of(2021, 4, 25));
    }

}