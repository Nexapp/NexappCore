package ca.nexapp.core.emails;

import java.util.regex.Pattern;

public class WithRegexEmailValidator extends EmailValidator {

    private static final String VALIDATION_REGEX = "^[\\w!#$%'&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,99}$";

    @Override
    public boolean isValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches(VALIDATION_REGEX, email);
    }

}
