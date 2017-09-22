package ca.nexapp.core.random;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Rule;
import org.junit.Test;

public class WeightedPickerTest {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Test
    public void givenNoItem_ShouldNotPickAnyItem() {
        List<Integer> items = Collections.emptyList();

        Optional<Integer> result = WeightedPicker.pickOne(items, weightFunction());

        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void givenOneItem_CanPickIt() {
        List<Integer> items = Arrays.asList(2);

        Optional<Integer> result = WeightedPicker.pickOne(items, weightFunction());

        assertThat(result.get()).isEqualTo(2);
    }

    @Test
    @Repeat(50)
    public void givenMultipleItems_CanPickOneOfThem() {
        List<Integer> items = Arrays.asList(2, 6, 10);

        Optional<Integer> result = WeightedPicker.pickOne(items, weightFunction());

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
        List<String> names = Arrays.asList("John", "Sam");

        Optional<String> result = WeightedPicker.pickOne(names, weight);

        assertThat(result.get()).doesNotContain("John");
    }

    private Function<Integer, Number> weightFunction() {
        return (a) -> a;
    }
}
