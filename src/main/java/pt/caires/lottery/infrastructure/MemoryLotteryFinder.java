package pt.caires.lottery.infrastructure;

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

    private final WrapperStorageLottery wrapperStorageLottery;
    private final LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper;

    public MemoryLotteryFinder(WrapperStorageLottery wrapperStorageLottery,
                               LotteryEntityToLotteryMapper lotteryEntityToLotteryMapper) {
        this.wrapperStorageLottery = wrapperStorageLottery;
        this.lotteryEntityToLotteryMapper = lotteryEntityToLotteryMapper;
    }

    @Override
    public List<Lottery> findAll() {
        return wrapperStorageLottery.selectAll().stream()
                .map(toLottery())
                .collect(toList());
    }

    @Override
    public List<Lottery> findAllBy(LocalDate date) {
        return wrapperStorageLottery.selectAllBy(date).stream()
                .map(toLottery())
                .collect(toList());
    }

    @Override
    public Optional<Lottery> findBy(String id) {
        return wrapperStorageLottery.selectBy(id).map(toLottery());
    }

    private Function<LotteryEntity, Lottery> toLottery() {
        return lotteryEntityToLotteryMapper::map;
    }

}
