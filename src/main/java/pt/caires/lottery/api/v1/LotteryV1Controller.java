package pt.caires.lottery.api.v1;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.caires.lottery.api.v1.dto.CreateLotteryV1DTO;
import pt.caires.lottery.api.v1.dto.LotteriesV1DTO;
import pt.caires.lottery.api.v1.dto.LotteryV1DTO;
import pt.caires.lottery.api.v1.dto.PurchaseLotteryV1DTO;
import pt.caires.lottery.api.v1.mapper.CreateLotteryV1DTOToLotteryMapper;
import pt.caires.lottery.api.v1.mapper.LotteriesToLotteriesV1DTOMapper;
import pt.caires.lottery.api.v1.mapper.LotteryToLotteryV1DTOMapper;
import pt.caires.lottery.api.v1.mapper.PurchaseLotteryV1DTOToLotteryPurchaseEventMapper;
import pt.caires.lottery.usecase.CreateLottery;
import pt.caires.lottery.usecase.GetLotteries;
import pt.caires.lottery.usecase.PurchaseLotteryTickets;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "v1/lotteries", produces = MediaType.APPLICATION_JSON_VALUE)
public class LotteryV1Controller {

    private final CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper;
    private final CreateLottery createLottery;
    private final LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper;
    private final GetLotteries getLotteries;
    private final LotteriesToLotteriesV1DTOMapper lotteriesToLotteriesV1DTOMapper;
    private final PurchaseLotteryTickets purchaseLotteryTickets;
    private final PurchaseLotteryV1DTOToLotteryPurchaseEventMapper purchaseLotteryV1DTOToLotteryPurchaseEventMapper;

    public LotteryV1Controller(CreateLotteryV1DTOToLotteryMapper createLotteryV1DTOToLotteryMapper,
                               CreateLottery createLottery,
                               LotteryToLotteryV1DTOMapper lotteryToLotteryV1DTOMapper,
                               GetLotteries getLotteries,
                               LotteriesToLotteriesV1DTOMapper lotteriesToLotteriesV1DTOMapper,
                               PurchaseLotteryTickets purchaseLotteryTickets,
                               PurchaseLotteryV1DTOToLotteryPurchaseEventMapper purchaseLotteryV1DTOToLotteryPurchaseEventMapper) {
        this.createLotteryV1DTOToLotteryMapper = createLotteryV1DTOToLotteryMapper;
        this.createLottery = createLottery;
        this.lotteryToLotteryV1DTOMapper = lotteryToLotteryV1DTOMapper;
        this.getLotteries = getLotteries;
        this.lotteriesToLotteriesV1DTOMapper = lotteriesToLotteriesV1DTOMapper;
        this.purchaseLotteryTickets = purchaseLotteryTickets;
        this.purchaseLotteryV1DTOToLotteryPurchaseEventMapper = purchaseLotteryV1DTOToLotteryPurchaseEventMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public LotteryV1DTO createLottery(@RequestBody CreateLotteryV1DTO createLotteryV1DTO) {
        return lotteryToLotteryV1DTOMapper.map(
                createLottery.execute(createLotteryV1DTOToLotteryMapper.map(createLotteryV1DTO)));
    }

    @GetMapping
    public LotteriesV1DTO getLotteriesBy(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return lotteriesToLotteriesV1DTOMapper.map(getLotteries.execute(date));
    }

    @PostMapping(value = "/{id}/purchase", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchaseLotteryTickets(@PathVariable String id,
                                       @RequestBody PurchaseLotteryV1DTO purchaseLotteryV1DTO) {
        purchaseLotteryTickets.execute(purchaseLotteryV1DTOToLotteryPurchaseEventMapper.map(id, purchaseLotteryV1DTO));
    }

}
