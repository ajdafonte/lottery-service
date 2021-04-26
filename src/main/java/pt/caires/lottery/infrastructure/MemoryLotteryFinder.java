package pt.caires.lottery.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;
import pt.caires.lottery.infrastructure.mapper.LotteryEntityToLotteryMapper;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Named
public class MemoryLotteryFinder implements LotteryFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryLotteryFinder.class);

    private final WrapperStorageLottery wrapperStorageLottery;
    private final LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;

    public MemoryLotteryFinder(WrapperStorageLottery wrapperStorageLottery,
                               LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper) {
        this.wrapperStorageLottery = wrapperStorageLottery;
        this.lotteryEntityToLotteryMapper = lotteryEntityToLotteryMapper;
    }

    @Override
    public List<Lottery> findAll() {
        List<Lottery> lotteries = wrapperStorageLottery.selectAll().stream()
                .map(toLottery())
                .collect(toList());

        LOGGER.info("Found all Lotteries with a total of <{}>", lotteries.size());
        return lotteries;
    }

    @Override
    public List<Lottery> findAllBy(LocalDate date) {
        List<Lottery> lotteries = wrapperStorageLottery.selectAllBy(date).stream()
                .map(toLottery())
                .collect(toList());

        LOGGER.info("Found a total of <{}> Lotteries for date <{}>", lotteries.size(), date);
        return lotteries;
    }

    @Override
    public Optional<Lottery> findBy(String id) {
        LOGGER.info("Find Lottery with id <{}>", id);
        return wrapperStorageLottery.selectBy(id).map(toLottery());
    }

    private Function<LotteryEntity, Lottery> toLottery() {
        return lotteryEntityToLotteryMapper::map;
    }

}
