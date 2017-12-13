package ca.nexapp.core.authentication.hashers;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.pholser.junit.quickcheck.Property;

public abstract class PasswordHasherTest {

    private static final String A_PASSWORD = "A_PASSWORD";
    private static final String ANOTHER_PASSWORD = "ANOTHER_PASSWORD";
    private static final byte[] A_SALT = "SO_MUCH_SALT".getBytes();
    private static final byte[] ANOTHER_SALT = "SALTY_SALT".getBytes();

    private PasswordHasher hasher;

    @Before
    public void setUp() {
        hasher = createHasher();
    }

    @Test
    public void givenTheSamePasswordAndSalt_WhenHashing_ShouldMatch() {
        byte[] hashed = hasher.hash(A_PASSWORD, A_SALT);

        boolean matched = hasher.matches(A_PASSWORD, hashed, A_SALT);

        assertThat(matched).isTrue();
    }

    @Test
    public void givenTheSamePasswordButDifferentSalt_WhenHashing_ShouldNotMatch() {
        byte[] hashed = hasher.hash(A_PASSWORD, A_SALT);

        boolean matched = hasher.matches(A_PASSWORD, hashed, ANOTHER_SALT);

        assertThat(matched).isFalse();
    }

    @Test
    public void givenDifferentPasswordsButSameSalt_WhenHashing_ShouldNotMatch() {
        byte[] hashed = hasher.hash(A_PASSWORD, A_SALT);

        boolean matched = hasher.matches(ANOTHER_PASSWORD, hashed, A_SALT);

        assertThat(matched).isFalse();
    }

    @Property(trials = 500)
    public void samePasswordAndSaltShouldMatchHash(String password, byte[] salt) {
        byte[] hashed = hasher.hash(password, salt);

        boolean matched = hasher.matches(password, hashed, salt);

        assertThat(matched).isTrue();
    }

    protected abstract PasswordHasher createHasher();
}
