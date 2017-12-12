package ca.nexapp.core.authentication.hashers;

public interface PasswordHasher {

    byte[] hash(String raw, byte[] salt);

    boolean matches(String plain, byte[] hashed, byte[] salt);
}
