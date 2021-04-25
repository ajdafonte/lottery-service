package pt.caires.lottery.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@ToString
public class LotteryEntity {

    private final String id;
    private final String name;
    private final LocalDate date;
    private final boolean finished;
    private final List<Integer> tickets;
    private Integer winningTicket;

    public LotteryEntity(String id, String name, LocalDate date, boolean finished, List<Integer> tickets) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.finished = finished;
        this.tickets = tickets;
    }

    public LotteryEntity(String id,
                         String name,
                         LocalDate date,
                         boolean finished,
                         List<Integer> tickets,
                         Integer winningTicket) {
        this(id, name, date, finished, tickets);
        this.winningTicket = winningTicket;
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

    public Integer getWinningTicket() {
        return winningTicket;
    }

    public boolean hasSameDate(LocalDate localDate) {
        return getDate().equals(localDate);
    }

}
