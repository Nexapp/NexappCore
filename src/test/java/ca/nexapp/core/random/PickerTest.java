package ca.nexapp.core.random;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;

public class PickerTest {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Test
    public void givenNoItem_WhenPickingOneItem_ShouldReturnNoResult() {
        List<Integer> items = Collections.emptyList();

        Optional<Integer> picked = Picker.pickOne(items);

        assertThat(picked).isEqualTo(Optional.empty());
    }

    @Test
    public void canPickOneItem() {
        Optional<Integer> picked = Picker.pickOne(1, 2, 3, 4, 5);

        assertThat(picked.get()).isAnyOf(1, 2, 3, 4, 5);
    }

    @Test
    public void whenPickingANegativeAmountOfTimes_ShouldReturnNoResult() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> picked = Picker.pick(items, -1);

        assertThat(picked).isEmpty();
    }

    @Test
    public void shouldPickTheRequestedAmountOfTimes() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> picked = Picker.pick(items, 3);

        assertThat(picked).hasSize(3);
    }

    @Test
    public void cannotPickMoreItemsThanTheProvidedItems() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> picked = Picker.pick(items, 1000);

        assertThat(picked).hasSize(5);
    }

    @Test
    public void givenNoItem_WhenPicking_ShouldReturnNoResult() {
        List<Integer> items = Collections.emptyList();

        List<Integer> picked = Picker.pick(items, 1000);

        assertThat(picked).isEmpty();
    }

    @Test
    @Repeat(100)
    public void shouldNeverPickAnIgnoredItem() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> toIgnore = Arrays.asList(3, 4, 5);

        List<Integer> picked = Picker.pick(items, 10, toIgnore);

        assertThat(picked).doesNotContain(3);
        assertThat(picked).doesNotContain(4);
        assertThat(picked).doesNotContain(5);
    }
}
