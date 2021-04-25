package pt.caires.lottery.infrastructure;

import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import javax.inject.Named;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class WrapperStorageLottery {

    private static ConcurrentHashMap<String, LotteryEntity> LOTTERY_STORAGE = new ConcurrentHashMap<>();

    public void insert(LotteryEntity lotteryEntity) {
        LOTTERY_STORAGE.put(lotteryEntity.getId(), lotteryEntity);
    }

    public LotteryEntity selectBy(String id) {
        return LOTTERY_STORAGE.get(id);
    }

}
