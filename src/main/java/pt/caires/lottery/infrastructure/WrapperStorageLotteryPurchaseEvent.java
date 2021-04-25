package pt.caires.lottery.infrastructure;

import pt.caires.lottery.infrastructure.entity.LotteryPurchaseEventEntity;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentSkipListMap;

@Named
public class WrapperStorageLotteryPurchaseEvent {

    private static ConcurrentSkipListMap<LocalDateTime, LotteryPurchaseEventEntity> LOTTERY_PURCHASE_EVENTS_STORAGE = new ConcurrentSkipListMap<>();

    public static ConcurrentSkipListMap<LocalDateTime, LotteryPurchaseEventEntity> getLotteryPurchaseEventsStorage() {
        return LOTTERY_PURCHASE_EVENTS_STORAGE;
    }

    public void insert(LotteryPurchaseEventEntity lotteryPurchaseEventEntity) {
        LOTTERY_PURCHASE_EVENTS_STORAGE.put(lotteryPurchaseEventEntity.getOccurredAt(), lotteryPurchaseEventEntity);
    }

}
