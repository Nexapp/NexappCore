package ca.nexapp.core.random;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Rule;
import org.junit.Test;

public class WeightedSetTest {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Test
    public void givenNoItem_ShouldNotPickAnyItem() {
        WeightedSet<Integer> set = new WeightedSet<>(Collections.emptyList(), weightFunction());

        Optional<Integer> result = set.pickOne();

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenOneItem_CanPickIt() {
        WeightedSet<Integer> set = new WeightedSet<>(Arrays.asList(2), weightFunction());

        Optional<Integer> result = set.pickOne();

        assertThat(result.get()).isEqualTo(2);
    }

    @Test
    @Repeat(50)
    public void givenMultipleItems_CanPickOneOfthem() {
        WeightedSet<Integer> set = new WeightedSet<>(Arrays.asList(2, 6, 10), weightFunction());

        Optional<Integer> result = set.pickOne();

        assertThat(result.get()).isAnyOf(2, 6, 10);
    }

    @Test
    @Repeat(100)
    public void givenANegativeWeight_ShouldIgnoreThatItem() {
        Function<String, Number> weight = (string) -> {
            if (string.equals("John")) {
                return -1;
            }
            return 1;
        };
        WeightedSet<String> set = new WeightedSet<>(Arrays.asList("John", "Sam"), weight);

        Optional<String> result = set.pickOne();

        assertThat(result.get()).doesNotContain("John");
    }

    private Function<Integer, Number> weightFunction() {
        return (a) -> Long.valueOf(a);
    }
}
