package pt.caires.lottery.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.lottery.api.v1.dto.PurchaseLotteryV1DTO;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.domain.shared.Clock;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class PurchaseLotteryV1DTOToLotteryPurchaseEventMapperTest {

    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final List<Integer> TICKETS = List.of(123, 456);
    private static final LocalDateTime OCCURRED_AT = LocalDateTime.of(2021, 4, 25, 17, 0, 0);

    @Mock
    private Clock clock;

    private PurchaseLotteryV1DTOToLotteryPurchaseEventMapper purchaseLotteryV1DTOToLotteryPurchaseEventMapper;

    @BeforeEach
    void setUp() {
        this.purchaseLotteryV1DTOToLotteryPurchaseEventMapper = new PurchaseLotteryV1DTOToLotteryPurchaseEventMapper(clock);
    }

    @Test
    void should_map() {
        PurchaseLotteryV1DTO purchaseLotteryV1DTO = new PurchaseLotteryV1DTO(USER_ID, TICKETS);
        given(clock.now()).willReturn(OCCURRED_AT);

        LotteryPurchaseEvent result = purchaseLotteryV1DTOToLotteryPurchaseEventMapper.map(ID, purchaseLotteryV1DTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(anExpectedLotteryPurchaseEvent());
    }

    private LotteryPurchaseEvent anExpectedLotteryPurchaseEvent() {
        return new LotteryPurchaseEvent(
                "id",
                "userId",
                List.of(123, 456),
                LocalDateTime.of(2021, 4, 25, 17, 0, 0));
    }

}