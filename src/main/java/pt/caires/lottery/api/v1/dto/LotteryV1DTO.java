package pt.caires.lottery.api.v1.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@ToString
public class LotteryV1DTO {

    private final String id;
    private final String name;
    private final LocalDate date;
    private final boolean finished;
    private final List<Integer> tickets;

    public LotteryV1DTO(String id, String name, LocalDate date, boolean finished, List<Integer> tickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.finished = finished;
        this.tickets = tickets;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isFinished() {
        return finished;
    }

    public List<Integer> getTickets() {
        return tickets;
    }

}
