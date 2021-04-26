package pt.caires.lottery.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import pt.caires.lottery.domain.shared.Clock;
import pt.caires.lottery.usecase.CalculateLotteriesWinner;

import javax.inject.Named;
import java.time.LocalDate;

@Named
public class LotteryCalculatorScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LotteryCalculatorScheduler.class);

    private final CalculateLotteriesWinner calculateLotteriesWinner;
    private final Clock clock;

    public LotteryCalculatorScheduler(CalculateLotteriesWinner calculateLotteriesWinner, Clock clock) {
        this.calculateLotteriesWinner = calculateLotteriesWinner;
        this.clock = clock;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void calculateLotteriesWinner() {
        LOGGER.info("Start to calculate Lotteries winner");
        LocalDate date = clock.now().minusDays(1).toLocalDate();
        calculateLotteriesWinner.execute(date);
        LOGGER.info("Finish to calculate Lotteries winner");
    }

}
