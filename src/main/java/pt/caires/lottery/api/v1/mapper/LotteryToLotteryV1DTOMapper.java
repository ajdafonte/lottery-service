package pt.caires.lottery.api.v1.mapper;

import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.domain.Lottery;

import javax.inject.Named;

@Named
public class LotteryToLotteryV1DTOMapper {

    public LotteryV1DTO map(Lottery lottery) {
        return new LotteryV1DTO(
                lottery.getId(),
                lottery.getName(),
                lottery.getDate(),
                lottery.isFinished(),
                lottery.getTickets(),
                lottery.getWinningTicket());
    }

}
