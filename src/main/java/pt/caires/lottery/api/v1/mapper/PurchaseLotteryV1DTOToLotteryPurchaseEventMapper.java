package pt.caires.lottery.api.v1.mapper;

import pt.caires.lottery.api.v1.dto.PurchaseLotteryV1DTO;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.domain.shared.Clock;

import javax.inject.Named;

@Named
public class PurchaseLotteryV1DTOToLotteryPurchaseEventMapper {

    private final Clock clock;

    public PurchaseLotteryV1DTOToLotteryPurchaseEventMapper(Clock clock) {
        this.clock = clock;
    }

    public LotteryPurchaseEvent map(String id, PurchaseLotteryV1DTO purchaseLotteryV1DTO) {
        return new LotteryPurchaseEvent(
                id,
                purchaseLotteryV1DTO.getUserId(),
                purchaseLotteryV1DTO.getTickets(),
                clock.now());
    }

}
