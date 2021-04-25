package pt.caires.lottery.usecase;

import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryRepository;

import javax.inject.Named;

@Named
public class CreateLottery {

    private final LotteryRepository lotteryRepository;

    public CreateLottery(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public Lottery execute(Lottery lottery) {
        return lotteryRepository.save(lottery);
    }

}
