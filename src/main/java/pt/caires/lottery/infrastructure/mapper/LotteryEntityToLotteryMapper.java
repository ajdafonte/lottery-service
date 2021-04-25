package pt.caires.lottery.infrastructure.mapper;

import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import javax.inject.Named;

@Named
public class LotteryEntityToLotteryMapper {

    public Lottery map(LotteryEntity lotteryEntity) {
        return new Lottery(
                lotteryEntity.getId(),
                lotteryEntity.getName(),
                lotteryEntity.getDate(),
                lotteryEntity.isFinished(),
                lotteryEntity.getTickets());
    }

}
