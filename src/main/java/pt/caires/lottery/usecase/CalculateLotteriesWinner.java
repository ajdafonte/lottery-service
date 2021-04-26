package pt.caires.lottery.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;
import pt.caires.lottery.domain.LotteryRepository;
import pt.caires.lottery.domain.shared.Randomizer;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.function.Consumer;

@Named
public class CalculateLotteriesWinner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateLotteriesWinner.class);

    private final LotteryFinder lotteryFinder;
    private final LotteryRepository lotteryRepository;
    private final Randomizer randomizer;

    public CalculateLotteriesWinner(LotteryFinder lotteryFinder,
                                    LotteryRepository lotteryRepository,
                                    Randomizer randomizer) {
        this.lotteryFinder = lotteryFinder;
        this.lotteryRepository = lotteryRepository;
        this.randomizer = randomizer;
    }

    public void execute(LocalDate date) {
        lotteryFinder.findAllBy(date)
                .forEach(checkAndCalculateLotteryWinner());
    }

    private Consumer<Lottery> checkAndCalculateLotteryWinner() {
        return lottery -> {
            if (lottery.isFinished()) {
                LOGGER.info("Lottery <%s> is already finished, hence no wiiner to calculate");
            } else {
                int winningTicket = randomizer.getElementFrom(lottery.getTickets());
                Lottery finishedLottery = new Lottery(
                        lottery.getId(),
                        lottery.getName(),
                        lottery.getDate(),
                        true,
                        lottery.getTickets(),
                        winningTicket);
                lotteryRepository.save(finishedLottery);
            }
        };
    }

}
