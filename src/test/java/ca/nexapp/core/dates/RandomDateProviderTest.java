package ca.nexapp.core.dates;

import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

import ca.nexapp.core.random.Repeat;

public class RandomDateProviderTest {

    private static final ZoneId A_ZONE = ZoneId.of("America/Montreal");
    private static final ZonedDateTime FROM = ZonedDateTime.of(LocalDate.of(2016, Month.JANUARY, 1), LocalTime.MIN, A_ZONE);
    private static final ZonedDateTime TO = FROM.plusYears(1);

    @Test
    @Repeat(10_000) // It's my best attempt to test randomness
    public void aDateShouldBeBetweenTheProvidedDates() {
        DateProvider dateProvider = new RandomDateProvider(FROM, TO);

        ZonedDateTime toCompare = dateProvider.currentDateTime(A_ZONE);

        boolean after = toCompare.isAfter(FROM) || toCompare.equals(FROM);
        boolean before = toCompare.isBefore(TO) || toCompare.equals(TO);
        assertThat(after).isTrue();
        assertThat(before).isTrue();
    }

}
