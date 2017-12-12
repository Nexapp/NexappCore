package ca.nexapp.core.authentication;

import java.util.Objects;

import ca.nexapp.core.authentication.hashers.PasswordHasher;
import ca.nexapp.core.authentication.salts.SaltGenerator;

public class Password {

    private final byte[] hashed;
    private final byte[] salt;
    private final transient PasswordHasher passwordHasher;

    private Password(byte[] hashed, byte[] salt, PasswordHasher passwordHasher) {
        this.hashed = hashed;
        this.salt = salt;
        this.passwordHasher = passwordHasher;
    }

    public boolean matches(String plaintext) {
        return passwordHasher.matches(plaintext, hashed, salt);
    }

    public byte[] getHashed() {
        return hashed;
    }

    public byte[] getSalt() {
        return salt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashed, salt);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Password)) {
            return false;
        }

        Password other = (Password) obj;
        return Objects.equals(hashed, other.hashed)
                && Objects.equals(salt, other.salt);
    }

    public static Password fromPlaintext(String plaintext, SaltGenerator saltGenerator, PasswordHasher passwordHasher) {
        byte[] salt = saltGenerator.generate();
        byte[] hash = passwordHasher.hash(plaintext, salt);
        return Password.fromHash(hash, salt, passwordHasher);
    }

    public static Password fromHash(byte[] password, byte[] salt, PasswordHasher passwordHasher) {
        return new Password(password, salt, passwordHasher);
    }
}
