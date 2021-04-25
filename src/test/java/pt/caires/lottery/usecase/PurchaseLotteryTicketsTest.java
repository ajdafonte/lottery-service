package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.domain.LotteryPurchaseEventRepository;
import pt.caires.lottery.domain.exception.ConflictServiceException;
import pt.caires.lottery.domain.exception.NotFoundServiceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PurchaseLotteryTicketsTest {

    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    @Mock
    private LotteryFinder lotteryFinder;
    @Mock
    private LotteryPurchaseEventRepository lotteryPurchaseEventRepository;

    private PurchaseLotteryTickets purchaseLotteryTickets;

    @BeforeEach
    void setUp() {
        this.purchaseLotteryTickets = new PurchaseLotteryTickets(lotteryFinder, lotteryPurchaseEventRepository);
    }

    @Test
    void should_purchase_lottery_tickets() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT);
        Lottery lottery = new Lottery(ID, "name", LocalDate.of(2021, 4, 25), false, List.of(123, 456));
        given(lotteryFinder.findBy(ID)).willReturn(Optional.of(lottery));

        purchaseLotteryTickets.execute(lotteryPurchaseEvent);

        verify(lotteryPurchaseEventRepository).save(lotteryPurchaseEvent);
    }

    @Test
    void should_fail_to_purchase_lottery_tickets_when_lottery_not_found() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent("unknownId", USER_ID, TICKETS, OCCURRED_AT);
        given(lotteryFinder.findBy("unknownId")).willReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> purchaseLotteryTickets.execute(lotteryPurchaseEvent));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(NotFoundServiceException.class)
                .hasMessage("Lottery <unknownId> was not found");
    }

    @Test
    void should_fail_to_purchase_lottery_tickets_when_lottery_finished() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT);
        Lottery lottery = new Lottery(ID, "name", LocalDate.of(2021, 4, 25), true, List.of(123, 456));
        given(lotteryFinder.findBy(ID)).willReturn(Optional.of(lottery));

        Throwable throwable = catchThrowable(() -> purchaseLotteryTickets.execute(lotteryPurchaseEvent));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(ConflictServiceException.class)
                .hasMessage("Unable to purchase tickets for finished Lottery <id>");
    }

    @Test
    void should_fail_to_purchase_lottery_tickets_when_invalid_tickets() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, List.of(111, 222), OCCURRED_AT);
        Lottery lottery = new Lottery(ID, "name", LocalDate.of(2021, 4, 25), false, List.of(123, 456));
        given(lotteryFinder.findBy(ID)).willReturn(Optional.of(lottery));

        Throwable throwable = catchThrowable(() -> purchaseLotteryTickets.execute(lotteryPurchaseEvent));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(ConflictServiceException.class)
                .hasMessage("Invalid tickets <[111, 222]> for Lottery <id>");
    }

}