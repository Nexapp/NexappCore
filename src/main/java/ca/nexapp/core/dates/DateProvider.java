package ca.nexapp.core.dates;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface DateProvider {

    Instant currentTimestamp();

    LocalDateTime currentDateTime();

    ZonedDateTime currentDateTime(ZoneId zone);

    LocalDate currentDate();

    default DayOfWeek currentDayOfWeek() {
        return currentDate().getDayOfWeek();
    }

    default DayOfWeek currentDayOfWeek(ZoneId zone) {
        return currentDateTime(zone).getDayOfWeek();
    }
}
