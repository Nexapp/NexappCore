package ca.nexapp.core.authentication;

public interface Authenticable {

    default boolean matches(String plainPassword) {
        return getPassword().matches(plainPassword);
    }

    Password getPassword();

    default void changePassword(String oldPassword, Password newPassword) throws InvalidPasswordException {
        if (!matches(oldPassword)) {
            throw new InvalidPasswordException();
        }
        setPassword(newPassword);
    }

    void setPassword(Password password);
}
