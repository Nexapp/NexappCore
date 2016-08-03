package ca.nexapp.core.dates;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateProvider {

    Instant currentTimestamp();

    LocalDateTime currentDateTime();

    LocalDate currentDate();
}
