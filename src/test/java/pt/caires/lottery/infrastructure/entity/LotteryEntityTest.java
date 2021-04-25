package pt.caires.lottery.infrastructure.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryEntityTest {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final boolean FINISHED = false;
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LotteryEntity LOTTERY_ENTITY = new LotteryEntity(
            ID,
            NAME,
            LocalDate.of(2021, 4, 25),
            FINISHED,
            TICKETS);

    @Test
    void should_have_same_date() {
        boolean result = LOTTERY_ENTITY.hasSameDate(LocalDate.of(2021, 4, 25));

        assertThat(result).isTrue();
    }

    @Test
    void should_not_have_same_date() {
        boolean result = LOTTERY_ENTITY.hasSameDate(LocalDate.of(2021, 4, 26));

        assertThat(result).isFalse();
    }

}