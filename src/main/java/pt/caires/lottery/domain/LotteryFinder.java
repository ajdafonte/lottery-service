package pt.caires.lottery.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LotteryFinder {

    List<Lottery> findAll();

    List<Lottery> findAllBy(LocalDate date);

    Optional<Lottery> findBy(String id);

}
