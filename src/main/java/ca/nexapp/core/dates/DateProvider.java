package ca.nexapp.core.dates;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public interface DateProvider {

    default Instant currentTimestamp() {
        return currentDateTime(ZoneOffset.UTC).toInstant();
    }

    default ZonedDateTime atStartOfDay(ZoneId zone) {
        return currentDateTime(zone).toLocalDate().atStartOfDay(zone);
    }

    default ZonedDateTime atEndOfDay(ZoneId zone) {
        return currentDateTime(zone).with(LocalTime.MAX);
    }

    ZonedDateTime currentDateTime(ZoneId zone);

    LocalDate currentDate(ZoneId zone);

    default DayOfWeek currentDayOfWeek(ZoneId zone) {
        return currentDateTime(zone).getDayOfWeek();
    }
}
