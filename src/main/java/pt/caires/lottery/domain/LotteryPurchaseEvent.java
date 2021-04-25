package pt.caires.lottery.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotBlank;
import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotNull;
import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotNullAndEmpty;

@EqualsAndHashCode
@ToString
public class LotteryPurchaseEvent {

    private final String lotteryId;
    private final String userId;
    private final List<Integer> tickets;
    private final LocalDateTime occurredAt;

    public LotteryPurchaseEvent(String lotteryId, String userId, List<Integer> tickets, LocalDateTime occurredAt) {
        checkNotBlank(lotteryId, "LotteryId is required");
        checkNotBlank(userId, "UserId is required");
        checkNotNullAndEmpty(tickets, "At least one ticket is required");
        checkNotNull(occurredAt, "OccurredAt is required");
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
