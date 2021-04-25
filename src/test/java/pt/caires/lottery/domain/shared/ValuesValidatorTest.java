package pt.caires.lottery.domain.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValuesValidatorTest {

    @Test
    void should_return_true_when_is_a_blank_value() {
        assertThat(ValuesValidator.isBlank(" "))
                .isTrue();
    }

    @Test
    void should_return_true_when_undefined_value() {
        assertThat(ValuesValidator.isBlank(null))
                .isTrue();
    }

    @Test
    void should_return_false_when_is_not_a_blank_value() {
        assertThat(ValuesValidator.isBlank("value"))
                .isFalse();
    }

    @Test
    void should_return_false_when_is_a_blank_value() {
        assertThat(ValuesValidator.isNotBlank(" "))
                .isFalse();
    }

    @Test
    void should_return_false_when_undefined_value() {
        assertThat(ValuesValidator.isNotBlank(null))
                .isFalse();
    }

    @Test
    void should_return_true_when_is_not_a_blank_value() {
        assertThat(ValuesValidator.isNotBlank("value"))
                .isTrue();
    }

}