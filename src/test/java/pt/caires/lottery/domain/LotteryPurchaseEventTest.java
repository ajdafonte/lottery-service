package pt.caires.lottery.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LotteryPurchaseEventTest {

    private static final String LOTTERY_ID = "lotteryId";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    @Test
    void require_lottery_id() {
        Throwable throwable = catchThrowable(() -> new LotteryPurchaseEvent(null, USER_ID, TICKETS, OCCURRED_AT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("LotteryId is required");
    }

    @Test
    void require_user_id() {
        Throwable throwable = catchThrowable(() -> new LotteryPurchaseEvent(LOTTERY_ID, null, TICKETS, OCCURRED_AT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("UserId is required");
    }

    @Test
    void require_tickets() {
        Throwable throwable = catchThrowable(() -> new LotteryPurchaseEvent(LOTTERY_ID, USER_ID, null, OCCURRED_AT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one ticket is required");
    }

    @Test
    void require_occurred_at() {
        Throwable throwable = catchThrowable(() -> new LotteryPurchaseEvent(LOTTERY_ID, USER_ID, TICKETS, null));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("OccurredAt is required");
    }

}