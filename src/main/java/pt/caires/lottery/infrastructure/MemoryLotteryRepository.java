package pt.caires.lottery.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryRepository;
import pt.caires.lottery.domain.exception.ConflictServiceException;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryEntityToLotteryMapper;
import pt.caires.lottery.infrastructure.mapper.LotteryToLotteryEntityMapper;

import javax.inject.Named;
import java.util.function.Function;

@Named
public class MemoryLotteryRepository implements LotteryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryLotteryRepository.class);

    private final WrapperStorageLottery wrapperStorageLottery;
    private final LotteryToLotteryEntityMapper lotteryToLotteryEntityMapper;
    private final LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;

    public MemoryLotteryRepository(WrapperStorageLottery wrapperStorageLottery,
                                   LotteryToLotteryEntityMapper lotteryToLotteryEntityMapper,
                                   LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper) {
        this.wrapperStorageLottery = wrapperStorageLottery;
        this.lotteryToLotteryEntityMapper = lotteryToLotteryEntityMapper;
        this.lotteryEntityToLotteryMapper = lotteryEntityToLotteryMapper;
    }

    @Override
    public Lottery save(Lottery lottery) {
        LotteryEntity lotteryEntity = lotteryToLotteryEntityMapper.map(lottery);

        wrapperStorageLottery.insert(lotteryEntity);

        Lottery savedLottery = wrapperStorageLottery.selectBy(lottery.getId())
                .map(toLottery())
                .orElseThrow(() -> new ConflictServiceException("A conflict has occurred when saving the Lottery"));

        LOGGER.info("Saved Lottery <{}>", savedLottery);
        return savedLottery;
    }

    private Function<LotteryEntity, Lottery> toLottery() {
        return lotteryEntityToLotteryMapper::map;
    }

}
