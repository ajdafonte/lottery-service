package pt.caires.lottery.infrastructure.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.infrastructure.entity.LotteryPurchaseEventEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryPurchaseEventToLotteryPurchaseEventEntityMapperTest {

    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    private LotteryPurchaseEventToLotteryPurchaseEventEntityMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new LotteryPurchaseEventToLotteryPurchaseEventEntityMapper();
    }

    @Test
    void should_map() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT);

        LotteryPurchaseEventEntity result = mapper.map(lotteryPurchaseEvent);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryPurchaseEventEntity());
    }

    private LotteryPurchaseEventEntity anExpectedLotteryPurchaseEventEntity() {
        return new LotteryPurchaseEventEntity(
                "id",
                "userId",
                List.of(123, 456),
                LocalDateTime.of(2021, 4, 25, 17, 0, 0));
    }

}