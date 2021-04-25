package pt.caires.lottery.usecase;

import pt.caires.lottery.domain.Lottery;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named
public class GetLotteries {

    public List<Lottery> execute(LocalDate date) {
        return List.of(
                new Lottery(
                        "e3211be6-d0cc-4718-905d-ab933cc91ecb",
                        "Lottery 1",
                        LocalDate.of(2021, 4, 25),
                        false,
                        List.of(1234567, 9876543)));
    }

}
