package pt.caires.lottery.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotBlank;
import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotNull;
import static pt.caires.lottery.domain.shared.FieldsValidator.checkNotNullAndEmpty;

@EqualsAndHashCode
@ToString
public class Lottery {

    private final String id;
    private final String name;
    private final LocalDate date;
    private final boolean finished;
    private final List<Integer> tickets;
    private Integer winningTicket;

    public Lottery(String id, String name, LocalDate date, boolean finished, List<Integer> tickets) {
        checkNotBlank(id, "Id is required");
        checkNotBlank(name, "Name is required");
        checkNotNull(date, "Date is required");
        checkNotNullAndEmpty(tickets, "At least one ticket is required");
        this.id = id;
        this.name = name;
        this.date = date;
        this.finished = finished;
        this.tickets = tickets;
    }

    public Lottery(String id,
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

    public boolean containsAllTickets(List<Integer> tickets) {
        return getTickets().containsAll(tickets);
    }


}
