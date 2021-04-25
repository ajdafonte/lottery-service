package pt.caires.lottery.domain.shared;

import java.util.Collection;
import java.util.Objects;

public class FieldsValidator {

    private FieldsValidator() {
        //do nothing
    }

    public static void checkNotNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkNotBlank(String value, String message) {
        if (ValuesValidator.isBlank(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> void checkNotNullAndEmpty(Collection<T> collection, String message) {
        checkNotNull(collection, message);
        checkNotEmpty(collection, message);
    }

    private static <T> void checkNotEmpty(Collection<T> collection, String message) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

}
