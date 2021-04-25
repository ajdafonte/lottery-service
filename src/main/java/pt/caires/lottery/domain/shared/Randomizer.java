package pt.caires.lottery.domain.shared;

import javax.inject.Named;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.toList;

@Named
public class Randomizer {

    public List<Integer> getPositiveValues(int size) {
        return ThreadLocalRandom.current()
                .ints(size, 1, Integer.MAX_VALUE)
                .boxed()
                .collect(toList());
    }

}
