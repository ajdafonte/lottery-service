package pt.caires.lottery.api.v1.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class PurchaseLotteryTicketsV1DTO {

    private final String userId;
    private final List<Integer> tickets;

    public PurchaseLotteryTicketsV1DTO(String userId, List<Integer> tickets) {
        this.userId = userId;
        this.tickets = tickets;
    }

    public String getUserId() {
        return userId;
    }

    public List<Integer> getTickets() {
        return tickets;
    }

}
