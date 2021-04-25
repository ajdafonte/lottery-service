package pt.caires.lottery.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LotteryTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final LocalDate DATE = LocalDate.of(2021, 4, 25);
    private static final boolean FINISHED = false;
    private static final List<Integer> TICKETS = List.of(123, 456);

    @Test
    void should_require_id() {
        Throwable throwable = catchThrowable(() -> new Lottery(null, NAME, DATE, FINISHED, TICKETS));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id is required");
    }

    @Test
    void should_require_name() {
        Throwable throwable = catchThrowable(() -> new Lottery(ID, null, DATE, FINISHED, TICKETS));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name is required");
    }

    @Test
    void should_require_date() {
        Throwable throwable = catchThrowable(() -> new Lottery(ID, NAME, null, FINISHED, TICKETS));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Date is required");
    }

    @Test
    void should_requires_tickets() {
        Throwable throwable = catchThrowable(() -> new Lottery(ID, NAME, DATE, FINISHED, List.of()));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("At least one ticket is required");
    }

    @Test
    void should_contain_all_tickets() {
        Lottery lottery = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);

        boolean result = lottery.containsAllTickets(List.of(456));

        assertThat(result).isTrue();
    }

    @Test
    void should_not_contain_all_tickets() {
        Lottery lottery = new Lottery(ID, NAME, DATE, FINISHED, TICKETS);

        boolean result = lottery.containsAllTickets(List.of(111));

        assertThat(result).isFalse();
    }

}