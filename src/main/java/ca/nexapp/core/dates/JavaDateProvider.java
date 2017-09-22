package ca.nexapp.core.dates;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JavaDateProvider implements DateProvider {

    @Override
    public ZonedDateTime currentDateTime(ZoneId zone) {
        return ZonedDateTime.now(zone);
    }

    @Override
    public LocalDate currentDate(ZoneId zone) {
        return LocalDate.now(zone);
    }

}
