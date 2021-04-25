package pt.caires.lottery.domain.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomizerTest {

    private Randomizer randomizer;

    @BeforeEach
    void setUp() {
        this.randomizer = new Randomizer();
    }

    @Test
    void should_retrieve_collection_positive_values() {
        List<Integer> result = randomizer.getPositiveValues(3);

        assertThat(result)
                .isNotEmpty()
                .hasSize(3)
                .allMatch(value -> value > 0);
    }

}