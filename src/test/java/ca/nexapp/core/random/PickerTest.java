package ca.nexapp.core.random;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PickerTest {

    @Test
    public void shouldNeverPickAnIgnoredItem() {
        // It's my best attempt to test randomness
        for (int i = 0; i < 100; ++i) {
            Picker<Integer> items = new Picker<>(Arrays.asList(1, 2, 3, 4, 5));

            List<Integer> picked = items.pick(10, Arrays.asList(3, 4, 5));

            assertThat(picked).doesNotContain(3);
            assertThat(picked).doesNotContain(4);
            assertThat(picked).doesNotContain(5);
        }
    }
}
