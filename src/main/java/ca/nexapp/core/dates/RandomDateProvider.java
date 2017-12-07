package ca.nexapp.core.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;

public class RandomDateProvider implements DateProvider {

    private final long fromEpoch;
    private final long toEpoch;
    private final Random random;

    public RandomDateProvider(ZonedDateTime fromInclusive, ZonedDateTime toInclusive) {
        fromEpoch = fromInclusive.toEpochSecond();
        toEpoch = toInclusive.toEpochSecond();
        random = new Random();
    }

    @Override
    public ZonedDateTime currentDateTime(ZoneId zone) {
        return currentDateTime().atZone(zone);
    }

    @Override
    public LocalDate currentDate(ZoneId zone) {
        long randomEpoch = randomizeBetween(fromEpoch, toEpoch);
        return LocalDate.ofEpochDay(randomEpoch);
    }

    private LocalDateTime currentDateTime() {
        long randomEpoch = randomizeBetween(fromEpoch, toEpoch);
        return LocalDateTime.ofEpochSecond(randomEpoch, 0, ZoneOffset.UTC);
    }

    private long randomizeBetween(long from, long to) {
        return from + random.nextInt((int) (to - from));
    }

}
