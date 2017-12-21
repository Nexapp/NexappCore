package ca.nexapp.core.dates;

import static com.google.common.truth.Truth.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.junit.Test;

public class DateTimeRangeTest {

    private static final ZoneId A_ZONE = ZoneOffset.UTC;

    private static final ZonedDateTime NOW = ZonedDateTime.now(A_ZONE);
    private static final ZonedDateTime EARLIER = NOW.minusDays(1);
    private static final ZonedDateTime LATER = NOW.plusDays(1);

    @Test
    public void canCreateFromTwoZonedDateTime() {
        DateTimeRange range = DateTimeRange.of(NOW, LATER);

        assertThat(NOW).isEqualTo(range.getFrom());
        assertThat(LATER).isEqualTo(range.getTo());
    }

    @Test
    public void canCreateARangeFromTheSameDateTime() {
        DateTimeRange range = DateTimeRange.of(NOW, NOW);

        assertThat(NOW).isEqualTo(range.getFrom());
        assertThat(NOW).isEqualTo(range.getTo());
    }

    @Test
    public void canCreateARangeFromTwoInstant() {
        Instant fromInstant = NOW.toInstant();
        Instant toInstant = LATER.toInstant();

        DateTimeRange range = DateTimeRange.of(fromInstant, toInstant, A_ZONE);

        assertThat(NOW.withZoneSameInstant(A_ZONE)).isEqualTo(range.getFrom());
        assertThat(LATER.withZoneSameInstant(A_ZONE)).isEqualTo(range.getTo());
    }

    @Test
    public void canCreateARangeFromTwoLocalDateTime() {
        LocalDateTime from = NOW.toLocalDateTime();
        LocalDateTime to = LATER.toLocalDateTime();

        DateTimeRange range = DateTimeRange.of(from, to, A_ZONE);

        assertThat(NOW).isEqualTo(range.getFrom());
        assertThat(LATER).isEqualTo(range.getTo());
    }

    @Test(expected = InvalidDateRangeException.class)
    public void cannotCreateARangeThatGoesToThePast() throws InvalidDateRangeException {
        DateTimeRange.of(NOW, EARLIER);
    }

    @Test(expected = InvalidDateRangeException.class)
    public void cannotCreateARangeFromDifferentZones() {
        ZonedDateTime montreal = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        ZonedDateTime vancouver = ZonedDateTime.now(ZoneId.of("America/Vancouver"));

        DateTimeRange.of(montreal, vancouver);
    }

    @Test
    public void canRetrieveTheZone() {
        ZonedDateTime from = ZonedDateTime.now(A_ZONE);
        ZonedDateTime to = from.plusDays(555);
        DateTimeRange range = DateTimeRange.of(from, to);

        assertThat(range.getZone()).isEqualTo(A_ZONE);
    }

    @Test
    public void canRetrieveTheDuration() {
        ZonedDateTime from = ZonedDateTime.now(A_ZONE);
        ZonedDateTime to = from.plusMinutes(50);

        DateTimeRange dateTimeRange = DateTimeRange.of(from, to);

        Duration expected = Duration.ofMinutes(50);
        assertThat(dateTimeRange.asDuration()).isEqualTo(expected);
    }

    private static final LocalDateTime MARCH_1ST = LocalDateTime.of(2017, Month.MARCH, 1, 0, 0);
    private static final LocalDateTime MARCH_2ND = LocalDateTime.of(2017, Month.MARCH, 2, 0, 0);
    private static final LocalDateTime MARCH_3RD = LocalDateTime.of(2017, Month.MARCH, 3, 0, 0);
    private static final LocalDateTime MARCH_5TH = LocalDateTime.of(2017, Month.MARCH, 5, 0, 0);
    private static final LocalDateTime MARCH_6TH = LocalDateTime.of(2017, Month.MARCH, 6, 0, 0);
    private static final LocalDateTime APRIL_1ST = LocalDateTime.of(2017, Month.APRIL, 1, 0, 0);
    private static final LocalDateTime APRIL_2ND = LocalDateTime.of(2017, Month.APRIL, 2, 0, 0);
    private static final LocalDateTime MAY_1ST = LocalDateTime.of(2017, Month.MAY, 1, 0, 0);
    private static final LocalDateTime JANUARY_1ST = LocalDateTime.of(2017, Month.JANUARY, 1, 0, 0);
    private static final LocalDateTime FEBRUARY_2ND = LocalDateTime.of(2017, Month.FEBRUARY, 2, 0, 0);

    @Test
    public void givenNotOverlappingDateTime_ShouldNotOverlap() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_2ND, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(APRIL_1ST, APRIL_2ND, A_ZONE);

        assertThat(range1.isOverlapping(range2)).isFalse();
    }

    @Test
    public void givenATimeRangeOverflowingAtTheEnd_ShouldOverlap() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_2ND, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(MARCH_2ND, APRIL_2ND, A_ZONE);

        assertThat(range1.isOverlapping(range2)).isTrue();
    }

    @Test
    public void givenATimeRangeOverflowingAtTheBeginning_ShouldOverlap() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_2ND, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(FEBRUARY_2ND, MARCH_1ST, A_ZONE);

        assertThat(range1.isOverlapping(range2)).isTrue();
    }

    @Test
    public void givenAContainedRangeInsideAnotherRange_ShouldOverlap() {
        DateTimeRange range = DateTimeRange.of(MARCH_1ST, APRIL_2ND, A_ZONE);
        DateTimeRange contained = DateTimeRange.of(MARCH_5TH, MARCH_6TH, A_ZONE);

        assertThat(range.isOverlapping(contained)).isTrue();
    }

    @Test
    public void overlappingDateRange_ShouldBeCloseAtTheBeginning() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, APRIL_2ND, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(FEBRUARY_2ND, MARCH_1ST, A_ZONE);

        assertThat(range1.isOverlapping(range2)).isTrue();
    }

    @Test
    public void overlappingDateRange_ShouldBeCloseAtTheEnd() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, APRIL_1ST, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(APRIL_1ST, MAY_1ST, A_ZONE);

        assertThat(range1.isOverlapping(range2)).isTrue();
    }

    @Test
    public void cannotFindOverlappingPeriodOfNonOverlappingRanges() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_2ND, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(APRIL_1ST, APRIL_2ND, A_ZONE);

        Optional<DateTimeRange> overlap = range1.findOverlappingPeriod(range2);

        assertThat(overlap).isEqualTo(Optional.empty());
    }

    @Test
    public void givenOverlappingRangesAtTheEnd_CanRetrieveTheOverlappingPeriod() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_3RD, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(MARCH_2ND, APRIL_2ND, A_ZONE);

        Optional<DateTimeRange> overlap = range1.findOverlappingPeriod(range2);

        DateTimeRange expected = DateTimeRange.of(MARCH_2ND, MARCH_3RD, A_ZONE);
        assertThat(overlap).isEqualTo(Optional.of(expected));
    }

    @Test
    public void givenOverlappingRangesAtTheBeginning_CanRetrieveTheOverlappingPeriod() {
        DateTimeRange range1 = DateTimeRange.of(MARCH_1ST, MARCH_5TH, A_ZONE);
        DateTimeRange range2 = DateTimeRange.of(FEBRUARY_2ND, MARCH_2ND, A_ZONE);

        Optional<DateTimeRange> overlap = range1.findOverlappingPeriod(range2);

        DateTimeRange expected = DateTimeRange.of(MARCH_1ST, MARCH_2ND, A_ZONE);
        assertThat(overlap).isEqualTo(Optional.of(expected));
    }

    @Test
    public void givenOverlappingInside_TheOverlappingPeriodIsTheInnerDate() {
        DateTimeRange range = DateTimeRange.of(MARCH_1ST, APRIL_2ND, A_ZONE);
        DateTimeRange innerRange = DateTimeRange.of(MARCH_5TH, MARCH_6TH, A_ZONE);

        Optional<DateTimeRange> overlap = range.findOverlappingPeriod(innerRange);

        assertThat(overlap).isEqualTo(Optional.of(innerRange));
    }

    @Test
    public void givenADateAfterTheDateTimeRange_ShouldNotBeIncluded() {
        DateTimeRange range = DateTimeRange.of(JANUARY_1ST, FEBRUARY_2ND, A_ZONE);
        ZonedDateTime date = ZonedDateTime.of(MARCH_3RD, A_ZONE);

        assertThat(range.includes(date)).isFalse();
    }

    @Test
    public void givenADateAtTheEndOfTheRange_ShouldBeIncluded() {
        DateTimeRange range = DateTimeRange.of(JANUARY_1ST, FEBRUARY_2ND, A_ZONE);
        ZonedDateTime date = FEBRUARY_2ND.atZone(A_ZONE);

        assertThat(range.includes(date)).isTrue();
    }

    @Test
    public void givenADateBeforeTheRange_ShouldBeIncluded() {
        DateTimeRange range = DateTimeRange.of(JANUARY_1ST, FEBRUARY_2ND, A_ZONE);
        ZonedDateTime date = JANUARY_1ST.minusYears(1).atZone(A_ZONE);

        assertThat(range.includes(date)).isFalse();
    }

    @Test
    public void givenADateAtTheStartOfADateTimeRange_ShouldBeIncluded() {
        DateTimeRange range = DateTimeRange.of(JANUARY_1ST, FEBRUARY_2ND, A_ZONE);
        ZonedDateTime date = ZonedDateTime.of(JANUARY_1ST, A_ZONE);

        assertThat(range.includes(date)).isTrue();
    }

    @Test
    public void givenADateBetweenTheStartAndTheEndOfATimeRange_ShouldBeIncluded() {
        DateTimeRange range = DateTimeRange.of(MARCH_1ST, MARCH_3RD, A_ZONE);
        ZonedDateTime date = ZonedDateTime.of(MARCH_2ND, A_ZONE);

        assertThat(range.includes(date)).isTrue();
    }

    @Test
    public void givenNoDate_ShouldNotBeIncluded() {
        DateTimeRange range = DateTimeRange.of(JANUARY_1ST, FEBRUARY_2ND, A_ZONE);
        Optional<ZonedDateTime> noDate = Optional.empty();

        assertThat(range.includes(noDate)).isFalse();
    }
}
