package ca.nexapp.core.authentication.salts;

public interface SaltGenerator {

    byte[] generate();
}
