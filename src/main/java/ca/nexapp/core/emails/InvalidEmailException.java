package ca.nexapp.core.emails;

public class InvalidEmailException extends Exception {

    private static final long serialVersionUID = 7371486740174302686L;

    private final String email;

    public InvalidEmailException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
