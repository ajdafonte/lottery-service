package pt.caires.lottery.domain.shared;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class FieldsValidatorTest {

    @Test
    void should_check_not_null() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotNull("", "Not null"));

        assertThat(throwable)
                .isNull();
    }

    @Test
    void should_fail_when_check_not_null() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotNull(null, "Null"));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("Null")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_check_not_blank() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotBlank("Value", "Blank"));

        assertThat(throwable)
                .isNull();
    }

    @Test
    void should_fail_when_check_not_blank() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotBlank(" ", "Blank"));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("Blank")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_check_not_null_and_empty() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotNullAndEmpty(List.of("value"), "Null"));

        assertThat(throwable)
                .isNull();
    }

    @Test
    void should_fail_when_check_not_null_and_empty_with_null() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotNullAndEmpty(null, "Null"));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("Null")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_fail_when_check_not_null_and_empty_with_empty() {
        Throwable throwable = catchThrowable(() -> FieldsValidator.checkNotNullAndEmpty(Collections.emptyList(), "Blank"));

        assertThat(throwable)
                .isNotNull()
                .hasMessage("Blank")
                .isInstanceOf(IllegalArgumentException.class);
    }

}
