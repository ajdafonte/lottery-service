package pt.caires.lottery.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.caires.lottery.domain.Lottery;
import pt.caires.lottery.domain.LotteryFinder;
import pt.caires.lottery.domain.LotteryPurchaseEvent;
import pt.caires.lottery.domain.LotteryPurchaseEventRepository;
import pt.caires.lottery.domain.exception.ConflictServiceException;
import pt.caires.lottery.domain.exception.NotFoundServiceException;

import javax.inject.Named;
import java.util.List;
import java.util.function.Consumer;

@Named
public class PurchaseLotteryTickets {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseLotteryTickets.class);

    private final LotteryFinder lotteryFinder;
    private final LotteryPurchaseEventRepository lotteryPurchaseEventRepository;

    public PurchaseLotteryTickets(LotteryFinder lotteryFinder,
                                  LotteryPurchaseEventRepository lotteryPurchaseEventRepository) {
        this.lotteryFinder = lotteryFinder;
        this.lotteryPurchaseEventRepository = lotteryPurchaseEventRepository;
    }

    public void execute(LotteryPurchaseEvent lotteryPurchaseEvent) {
        lotteryFinder.findBy(lotteryPurchaseEvent.getLotteryId())
                .ifPresentOrElse(validateAndSaveLotteryPurchaseEvent(lotteryPurchaseEvent), throwNotFoundException(lotteryPurchaseEvent));
    }

    private Consumer<Lottery> validateAndSaveLotteryPurchaseEvent(LotteryPurchaseEvent lotteryPurchaseEvent) {
        return lottery -> {
            LOGGER.info("Validate if user <{}> can purchase tickets <{}> for lottery <{}>",
                    lotteryPurchaseEvent.getUserId(), lotteryPurchaseEvent.getTickets(), lotteryPurchaseEvent.getLotteryId());

            checkIfLotteryIsFinished(lottery);
            checkIfLotteryContainsAllTickets(lottery, lotteryPurchaseEvent);

            lotteryPurchaseEventRepository.save(lotteryPurchaseEvent);
        };
    }

    private void checkIfLotteryContainsAllTickets(Lottery lottery, LotteryPurchaseEvent lotteryPurchaseEvent) {
        List<Integer> tickets = lotteryPurchaseEvent.getTickets();
        if (!lottery.containsAllTickets(tickets)) {
            throw new ConflictServiceException(String.format("Invalid tickets <%s> for Lottery <%s>", tickets, lottery.getId()));
        }
    }

    private void checkIfLotteryIsFinished(Lottery lottery) {
        if (lottery.isFinished()) {
            throw new ConflictServiceException(
                    String.format("Unable to purchase tickets for finished Lottery <%s>", lottery.getId()));
        }
    }

    private Runnable throwNotFoundException(LotteryPurchaseEvent lotteryPurchaseEvent) {
        return () -> {
            throw new NotFoundServiceException(String.format("Lottery <%s> was not found", lotteryPurchaseEvent.getLotteryId()));
        };
    }

}
