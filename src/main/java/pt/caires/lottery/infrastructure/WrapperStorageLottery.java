package pt.caires.lottery.infrastructure;

import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Named
public class WrapperStorageLottery {

    private static ConcurrentHashMap<String, LotteryEntity> LOTTERY_STORAGE = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, LotteryEntity> getLotteryStorage() {
        return LOTTERY_STORAGE;
    }

    public void insert(LotteryEntity lotteryEntity) {
        LOTTERY_STORAGE.put(lotteryEntity.getId(), lotteryEntity);
    }

    public Optional<LotteryEntity> selectBy(String id) {
        return Optional.ofNullable(LOTTERY_STORAGE.get(id));
    }

    public Collection<LotteryEntity> selectAllBy(LocalDate date) {
        return selectAll().stream()
                .filter(byDate(date))
                .collect(toList());
    }

    public Collection<LotteryEntity> selectAll() {
        return LOTTERY_STORAGE.values();
    }

    private Predicate<LotteryEntity> byDate(LocalDate date) {
        return lotteryEntity -> lotteryEntity.hasSameDate(date);
    }


}
