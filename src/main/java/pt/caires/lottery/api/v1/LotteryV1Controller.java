package pt.caires.lottery.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.api.v1.mapper.CreateLotteryV1DTOToLotteryMapper;
import pt.caires.lottery.api.v1.mapper.LotteryToLotteryV1DTOMapper;
import pt.caires.lottery.usecase.CreateLottery;

@RestController
@RequestMapping(value = "v1/lotteries", produces = MediaType.APPLICATION_JSON_VALUE)
public class LotteryV1Controller {

    private final CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper;
    private final CreateLottery createLottery;
    private final LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;

    public LotteryV1Controller(CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper,
                               CreateLottery createLottery,
                               LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper) {
        this.createLotteryV1DTOToLotteryMapper = createLotteryV1DTOToLotteryMapper;
        this.createLottery = createLottery;
        this.lotteryToLotteryV1DTOMapper = lotteryToLotteryV1DTOMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryV1DTO createLottery(@RequestBody CreateLotteryV1DTO createLotteryV1DTO) {
        return lotteryToLotteryV1DTOMapper.map(
                createLottery.execute(createLotteryV1DTOToLotteryMapper.map(createLotteryV1DTO)));
    }

}
