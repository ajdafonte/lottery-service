package pt.caires.lottery.api.v1.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class LotteriesV1DTO {

    private final List<LotteryV1DTO> lotteries;

    public LotteriesV1DTO(List<LotteryV1DTO> lotteries) {
        this.lotteries = lotteries;
    }

    public List<LotteryV1DTO> getLotteries() {
        return lotteries;
    }

}
