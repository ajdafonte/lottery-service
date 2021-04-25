package pt.caires.lottery.domain;

import java.time.LocalDate;
import java.util.List;

public interface LotteryFinder {

    List<Lottery> findAll();

    List<Lottery> findAllBy(LocalDate date);

}
