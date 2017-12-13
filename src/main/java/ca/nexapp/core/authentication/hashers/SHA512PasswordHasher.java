package ca.nexapp.core.authentication.hashers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA512PasswordHasher implements PasswordHasher {

    @Override
    public byte[] hash(String raw, byte[] salt) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            output.write(salt);
            output.write(raw.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return DigestUtils.sha512(output.toByteArray());
    }

    @Override
    public boolean matches(String plain, byte[] hashed, byte[] salt) {
        byte[] toCompare = hash(plain, salt);
        return Arrays.equals(hashed, toCompare);
    }

}
