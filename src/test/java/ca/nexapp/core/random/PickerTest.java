package ca.nexapp.core.random;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

public class PickerTest {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Test
    @Repeat(100)
    public void shouldNeverPickAnIgnoredItem() {
        Picker<Integer> items = new Picker<>(Arrays.asList(1, 2, 3, 4, 5));

        List<Integer> picked = items.pick(10, Arrays.asList(3, 4, 5));

        assertThat(picked).doesNotContain(3);
        assertThat(picked).doesNotContain(4);
        assertThat(picked).doesNotContain(5);
    }
}
