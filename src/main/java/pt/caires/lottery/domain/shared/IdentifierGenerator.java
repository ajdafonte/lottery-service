package pt.caires.lottery.domain.shared;

import javax.inject.Named;
import java.util.UUID;

@Named
public class IdentifierGenerator {

    public String generate() {
        return UUID.randomUUID().toString();
    }

}
