package ca.nexapp.core.comparables;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

public class ComparedTest {

    @Test
    public void canApplyAConsumerToElements() {
        List<String> names = new ArrayList<>();
        Compared<String> compared = Compared.of("John", "Sam");

        Consumer<String> consumer = names::add;
        compared.apply(consumer);

        assertThat(names).containsExactly("John", "Sam");
    }

    @Test
    public void canMapTheElements() {
        Compared<String> compared = Compared.of("1", "346");

        Compared<Integer> mapped = compared.map(Integer::parseInt);

        assertThat(mapped).isEqualTo(Compared.of(1, 346));
    }

    @Test
    public void canReduceTheElements() {
        Compared<String> compared = Compared.of("Hello", "World!");

        String appended = compared.reduce((current, value) -> current + " " + value);

        assertThat(appended).isEqualTo("Hello World!");
    }

}
