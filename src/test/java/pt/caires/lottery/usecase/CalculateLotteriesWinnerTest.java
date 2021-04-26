package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;
import pt.caires.lottery.domain.LotteryRepository;
import pt.caires.lottery.domain.shared.Randomizer;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CalculateLotteriesWinnerTest {

    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final int WINNING_TICKET = 123;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final Lottery LOTTERY = new Lottery(ID, NAME, DATE, false, TICKETS);
    private static final Lottery FINISHED_LOTTERY = new Lottery(ID, NAME, DATE, true, TICKETS, WINNING_TICKET);

    @Mock
    private LotteryFinder lotteryFinder;
    @Mock
    private LotteryRepository lotteryRepository;
    @Mock
    private Randomizer randomizer;

    private CalculateLotteriesWinner calculateLotteriesWinner;

    @BeforeEach
    void setUp() {
        this.calculateLotteriesWinner = new CalculateLotteriesWinner(lotteryFinder, lotteryRepository, randomizer);
    }

    @Test
    void should_calculate_lottery_winner() {
        given(lotteryFinder.findAllBy(DATE)).willReturn(List.of(LOTTERY));
        given(randomizer.getElementFrom(TICKETS)).willReturn(WINNING_TICKET);

        calculateLotteriesWinner.execute(DATE);

        verify(lotteryRepository).save(FINISHED_LOTTERY);
    }

    @Test
    void should_not_calculate_lottery_winner_when_lottery_is_finished() {
        given(lotteryFinder.findAllBy(DATE)).willReturn(List.of(FINISHED_LOTTERY));

        calculateLotteriesWinner.execute(DATE);

        verifyNoInteractions(randomizer);
        verifyNoInteractions(lotteryRepository);
    }

}