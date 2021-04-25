package pt.caires.lottery.domain.shared;

import javax.inject.Named;
import java.time.LocalDateTime;

@Named
public class Clock {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
