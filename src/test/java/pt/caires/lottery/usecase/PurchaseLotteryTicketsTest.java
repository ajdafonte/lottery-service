package pt.caires.lottery.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.LotteryPurchaseEvent;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PurchaseLotteryTicketsTest {

    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    private PurchaseLotteryTickets purchaseLotteryTickets;

    @BeforeEach
    void setUp() {
        this.purchaseLotteryTickets = new PurchaseLotteryTickets();
    }

    @Test
    void should_purchase_lottery_tickets() {
        Throwable throwable = catchThrowable(() -> purchaseLotteryTickets.execute(new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT)));

        assertThat(throwable).isNull();
    }

}