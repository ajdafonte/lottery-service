package pt.caires.lottery.usecase;

import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Named
public class GetLotteries {

    private final LotteryFinder lotteryFinder;

    public GetLotteries(LotteryFinder lotteryFinder) {
        this.lotteryFinder = lotteryFinder;
    }

    public List<Lottery> execute(LocalDate date) {
        return Objects.isNull(date) ?
               lotteryFinder.findAll() :
               lotteryFinder.findAllBy(date);
    }

}
