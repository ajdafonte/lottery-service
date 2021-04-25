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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "v1/lotteries", produces = MediaType.APPLICATION_JSON_VALUE)
public class LotteryV1Controller {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryV1DTO createLottery(@RequestBody CreateLotteryV1DTO createLotteryV1DTO) {
        return new LotteryV1DTO(
                "e3211be6-d0cc-4718-905d-ab933cc91ecb",
                "Lottery 1",
                LocalDate.of(2021, 4, 25),
                false,
                List.of(1234567, 9876543));
    }

}
