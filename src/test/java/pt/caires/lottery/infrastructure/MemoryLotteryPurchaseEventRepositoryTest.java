package pt.caires.lottery.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.infrastructure.entity.LotteryPurchaseEventEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryPurchaseEventToLotteryPurchaseEventEntityMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemoryLotteryPurchaseEventRepositoryTest {

    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    @Mock
    private LotteryPurchaseEventToLotteryPurchaseEventEntityMapper mapper;
    @Mock
    private WrapperStorageLotteryPurchaseEvent wrapperStorageLotteryPurchaseEvent;

    private MemoryLotteryPurchaseEventRepository memoryLotteryPurchaseEventRepository;

    @BeforeEach
    void setUp() {
        this.memoryLotteryPurchaseEventRepository = new MemoryLotteryPurchaseEventRepository(mapper, wrapperStorageLotteryPurchaseEvent);
    }

    @Test
    void should_save_a_lottery_purchase_event() {
        LotteryPurchaseEvent lotteryPurchaseEvent = new LotteryPurchaseEvent(ID, USER_ID, TICKETS, OCCURRED_AT);
        LotteryPurchaseEventEntity lotteryPurchaseEventEntity = new LotteryPurchaseEventEntity(ID, USER_ID, TICKETS, OCCURRED_AT);
        given(mapper.map(lotteryPurchaseEvent)).willReturn(lotteryPurchaseEventEntity);

        memoryLotteryPurchaseEventRepository.save(lotteryPurchaseEvent);

        verify(wrapperStorageLotteryPurchaseEvent).insert(lotteryPurchaseEventEntity);
    }

}