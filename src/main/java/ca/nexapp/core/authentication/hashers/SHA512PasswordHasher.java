package ca.nexapp.core.authentication.hashers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SHA512PasswordHasher implements PasswordHasher {

    @Override
    public byte[] hash(String raw, byte[] salt) {
        MessageDigest digest = digest();
        digest.update(salt);
        byte[] bytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return builder.toString().getBytes(StandardCharsets.UTF_8);
    }

    private MessageDigest digest() {
        try {
            return MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MessageDigest SHA-512 does not exist");
        }
    }

    @Override
    public boolean matches(String plain, byte[] hashed, byte[] salt) {
        byte[] toCompare = hash(plain, salt);
        return Arrays.equals(hashed, toCompare);
    }

}
