package ca.nexapp.core.dates;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

public class DateTimeRange {

    private final ZonedDateTime from;
    private final ZonedDateTime to;

    private DateTimeRange(ZonedDateTime from, ZonedDateTime to) throws InvalidDateRangeException {
        if (from.isAfter(to)) {
            throw new InvalidDateRangeException(from, to);
        }

        this.from = from;
        this.to = to;
    }

    public boolean isOverlapping(DateTimeRange other) {
        return !(from.isAfter(other.to) || to.isBefore(other.from));
    }

    public Optional<DateTimeRange> findOverlappingPeriod(DateTimeRange other) {
        if (!isOverlapping(other)) {
            return Optional.empty();
        }

        ZonedDateTime startDate = from.compareTo(other.from) > 0 ? from : other.from;
        ZonedDateTime endDate = to.compareTo(other.to) < 0 ? to : other.to;
        DateTimeRange dateRange = DateTimeRange.of(startDate, endDate);
        return Optional.of(dateRange);
    }

    public Duration asDuration() {
        return Duration.between(from, to);
    }

    public ZoneId getZone() {
        return from.getZone();
    }

    public ZonedDateTime getFrom() {
        return from;
    }

    public ZonedDateTime getTo() {
        return to;
    }

    public boolean includes(Optional<ZonedDateTime> date) {
        return date.map(this::includes).orElse(false);
    }

    public boolean includes(ZonedDateTime date) {
        boolean after = date.isAfter(from) || date.isEqual(from);
        boolean before = date.isBefore(to) || date.isEqual(to);
        return before && after;
    }

    public static DateTimeRange of(Instant from, Instant to, ZoneId zoneId) {
        ZonedDateTime fromDateTime = ZonedDateTime.ofInstant(from, zoneId);
        ZonedDateTime toDateTime = ZonedDateTime.ofInstant(to, zoneId);
        return new DateTimeRange(fromDateTime, toDateTime);
    }

    public static DateTimeRange of(LocalDateTime from, LocalDateTime to, ZoneId zoneId) {
        ZonedDateTime zonedFrom = ZonedDateTime.of(from, zoneId);
        ZonedDateTime zonedTo = ZonedDateTime.of(to, zoneId);
        return new DateTimeRange(zonedFrom, zonedTo);
    }

    public static DateTimeRange of(ZonedDateTime from, ZonedDateTime to) {
        if (!from.getZone().equals(to.getZone())) {
            throw new InvalidDateRangeException(from, to);
        }
        return new DateTimeRange(from, to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DateTimeRange)) {
            return false;
        }
        DateTimeRange dateTimeRange = (DateTimeRange) obj;
        return Objects.equals(from, dateTimeRange.from)
                && Objects.equals(to, dateTimeRange.to);
    }

    @Override
    public String toString() {
        return String.format("%s => %s [%s]", from, to, getZone());
    }
}
