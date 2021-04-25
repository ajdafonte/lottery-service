package pt.caires.lottery.infrastructure;

import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryRepository;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryEntityToLotteryMapper;
import pt.caires.lottery.infrastructure.mapper.LotteryToLotteryEntityMapper;

import javax.inject.Named;

@Named
public class MemoryLotteryRepository implements LotteryRepository {

    private final WrapperStorageLottery wrapperStorageLottery;
    private final LotteryToLotteryEntityMapper chargingSessionToLotteryEntityMapper;
    private final LotteryEntityToLotteryMapper chargingSessionEntityToLotteryMapper;

    public MemoryLotteryRepository(WrapperStorageLottery wrapperStorageLottery,
                                   LotteryToLotteryEntityMapper chargingSessionToLotteryEntityMapper,
                                   LotteryEntityToLotteryMapper chargingSessionEntityToLotteryMapper) {
        this.wrapperStorageLottery = wrapperStorageLottery;
        this.chargingSessionToLotteryEntityMapper = chargingSessionToLotteryEntityMapper;
        this.chargingSessionEntityToLotteryMapper = chargingSessionEntityToLotteryMapper;
    }

    @Override
    public Lottery save(Lottery lottery) {
        LotteryEntity lotteryEntity = chargingSessionToLotteryEntityMapper.map(lottery);

        wrapperStorageLottery.insert(lotteryEntity);

        return chargingSessionEntityToLotteryMapper.map(wrapperStorageLottery.selectBy(lottery.getId()));
    }

}
