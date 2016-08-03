package ca.nexapp.core.dates;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JavaDateProvider implements DateProvider {

    @Override
    public Instant currentTimestamp() {
        return Instant.now(Clock.systemUTC());
    }

    @Override
    public LocalDateTime currentDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    @Override
    public LocalDate currentDate() {
        return LocalDate.now(Clock.systemUTC());
    }

}
