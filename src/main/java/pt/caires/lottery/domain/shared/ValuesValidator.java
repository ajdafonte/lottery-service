package pt.caires.lottery.domain.shared;

import java.util.Objects;

public class ValuesValidator {

    private ValuesValidator() {
        //do nothing
    }

    public static boolean isBlank(String value) {
        return Objects.isNull(value) || value.trim().isEmpty();
    }

    public static boolean isNotBlank(String value) {
        return Objects.nonNull(value) && !value.trim().isEmpty();
    }

}
