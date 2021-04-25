package pt.caires.lottery.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@ToString
public class LotteryPurchaseEventEntity {

    private final String lotteryId;
    private final String userId;
    private final List<Integer> tickets;
    private final LocalDateTime occurredAt;

    public LotteryPurchaseEventEntity(String lotteryId,
                                      String userId,
                                      List<Integer> tickets,
                                      LocalDateTime occurredAt) {
        this.lotteryId = lotteryId;
        this.userId = userId;
        this.tickets = tickets;
        this.occurredAt = occurredAt;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public String getUserId() {
        return userId;
    }

    public List<Integer> getTickets() {
        return tickets;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

}
