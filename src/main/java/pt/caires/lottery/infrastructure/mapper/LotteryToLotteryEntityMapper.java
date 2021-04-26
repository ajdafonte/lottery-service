package pt.caires.lottery.infrastructure.mapper;

import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.infrastructure.entity.LotteryEntity;

import javax.inject.Named;

@Named
public class LotteryToLotteryEntityMapper {

    public LotteryEntity map(Lottery lottery) {
        return new LotteryEntity(
                lottery.getId(),
                lottery.getName(),
                lottery.getDate(),
                lottery.isFinished(),
                lottery.getTickets(),
                lottery.getWinningTicket());
    }

}
