package pt.caires.lottery.infrastructure;

import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.domain.LotteryPurchaseEventRepository;
import pt.caires.lottery.infrastructure.mapper.LotteryPurchaseEventToLotteryPurchaseEventEntityMapper;

import javax.inject.Named;

@Named
public class MemoryLotteryPurchaseEventRepository implements LotteryPurchaseEventRepository {

    private final LotteryPurchaseEventToLotteryPurchaseEventEntityMapper lotteryPurchaseEventToLotteryPurchaseEventEntityMapper;
    private final WrapperStorageLotteryPurchaseEvent wrapperStorageLotteryPurchaseEvent;

    public MemoryLotteryPurchaseEventRepository(LotteryPurchaseEventToLotteryPurchaseEventEntityMapper lotteryPurchaseEventToLotteryPurchaseEventEntityMapper,
                                                WrapperStorageLotteryPurchaseEvent wrapperStorageLotteryPurchaseEvent) {
        this.lotteryPurchaseEventToLotteryPurchaseEventEntityMapper = lotteryPurchaseEventToLotteryPurchaseEventEntityMapper;
        this.wrapperStorageLotteryPurchaseEvent = wrapperStorageLotteryPurchaseEvent;
    }

    @Override
    public void save(LotteryPurchaseEvent lotteryPurchaseEvent) {
        wrapperStorageLotteryPurchaseEvent.insert(
                lotteryPurchaseEventToLotteryPurchaseEventEntityMapper.map(lotteryPurchaseEvent));
    }

}
