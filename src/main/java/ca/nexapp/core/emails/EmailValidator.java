package ca.nexapp.core.emails;

public abstract class EmailValidator {

    public void validate(String email) throws InvalidEmailException {
        if (!isValid(email)) {
            throw new InvalidEmailException(email);
        }
    }

    public abstract boolean isValid(String email);
}
