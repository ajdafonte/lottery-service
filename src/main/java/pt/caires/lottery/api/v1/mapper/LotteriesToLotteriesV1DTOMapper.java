package pt.caires.lottery.api.v1.mapper;

import pt.caires.lottery.api.v1.dto.LotteriesV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.domain.Lottery;

import javax.inject.Named;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Named
public class LotteriesToLotteriesV1DTOMapper {

    private final LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;

    public LotteriesToLotteriesV1DTOMapper(LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper) {
        this.lotteryToLotteryV1DTOMapper = lotteryToLotteryV1DTOMapper;
    }

    public LotteriesV1DTO map(List<Lottery> lotteries) {
        return new LotteriesV1DTO(
                lotteries.stream()
                        .map(toLottery())
                        .collect(toList()));
    }

    private Function<Lottery, LotteryV1DTO> toLottery() {
        return lotteryToLotteryV1DTOMapper::map;
    }

}
