package pt.caires.lottery.api.v1.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@ToString
public class CreateLotteryV1DTO {

    private final String name;
    private final LocalDate date;
    private final int numberTickets;

    public CreateLotteryV1DTO(String name, LocalDate date, int numberTickets) {
        this.name = name;
        this.date = date;
        this.numberTickets = numberTickets;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

}
