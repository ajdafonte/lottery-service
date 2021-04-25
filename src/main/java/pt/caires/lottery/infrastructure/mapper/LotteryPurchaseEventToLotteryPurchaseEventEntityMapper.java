package pt.caires.lottery.infrastructure.mapper;

import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.infrastructure.entity.LotteryPurchaseEventEntity;

import javax.inject.Named;

@Named
public class LotteryPurchaseEventToLotteryPurchaseEventEntityMapper {

    public LotteryPurchaseEventEntity map(LotteryPurchaseEvent lotteryPurchaseEvent) {
        return new LotteryPurchaseEventEntity(
                lotteryPurchaseEvent.getLotteryId(),
                lotteryPurchaseEvent.getUserId(),
                lotteryPurchaseEvent.getTickets(),
                lotteryPurchaseEvent.getOccurredAt());
    }

}
