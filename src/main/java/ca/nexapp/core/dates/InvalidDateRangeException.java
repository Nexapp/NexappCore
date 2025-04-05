package ca.nexapp.core.dates;

import java.time.ZonedDateTime;

public class InvalidDateRangeException extends RuntimeException {

    private static final long serialVersionUID = 728483949226608247L;

    public final ZonedDateTime from;
    public final ZonedDateTime to;

    public InvalidDateRangeException(ZonedDateTime from, ZonedDateTime to) {
        this.from = from;
        this.to = to;
    }
}
