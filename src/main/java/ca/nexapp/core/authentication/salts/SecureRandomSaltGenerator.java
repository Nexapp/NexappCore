package ca.nexapp.core.authentication.salts;

import java.security.SecureRandom;
import java.util.Random;

public class SecureRandomSaltGenerator implements SaltGenerator {

    @Override
    public byte[] generate() {
        Random random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }

}
