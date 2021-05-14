package ca.nexapp.core.authentication;

import ca.nexapp.core.authentication.hashers.PasswordHasher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PasswordTest {

    @Mock
    PasswordHasher passwordHasher;

    private static final byte[] A_SALT = new byte[]{1, 2, 3, 4};
    private static final byte[] ANOTHER_SALT = new byte[]{5, 6, 7, 8};
    private static final byte[] A_HASH = new byte[]{11, 22, 33, 44, 55, 66, 77, 88};
    private static final byte[] ANOTHER_HASH = new byte[]{12, 23, 34, 45, 56, 67, 78, 89};

    @Test
    public void givenPasswordsWithEquivalentHashAndSalt_ShouldBeEquals() {
        Password password1 = Password.fromHash(A_HASH, A_SALT, passwordHasher);
        Password password2 = Password.fromHash(A_HASH.clone(), A_SALT.clone(), passwordHasher);

        assertThat(password1).isEqualTo(password2);
    }

    @Test
    public void givenPasswordsWithDifferentHash_ShouldNotBeEquals() {
        Password password1 = Password.fromHash(A_HASH, A_SALT, passwordHasher);
        Password password2 = Password.fromHash(ANOTHER_HASH, A_SALT.clone(), passwordHasher);

        assertThat(password1).isNotEqualTo(password2);
    }

    @Test
    public void givenPasswordsWithDifferentSalt_ShouldNotBeEquals() {
        Password password1 = Password.fromHash(A_HASH, A_SALT, passwordHasher);
        Password password2 = Password.fromHash(A_HASH.clone(), ANOTHER_SALT, passwordHasher);

        assertThat(password1).isNotEqualTo(password2);
    }

    @Test
    public void givenPasswordsWithEquivalentHashAndSalt_ShouldHaveEqualsHashCode() {
        int password1HashCode = Password.fromHash(A_HASH, A_SALT, passwordHasher).hashCode();
        int password2HashCode = Password.fromHash(A_HASH.clone(), A_SALT.clone(), passwordHasher).hashCode();

        assertThat(password1HashCode).isEqualTo(password2HashCode);
    }

    @Test
    public void givenPasswordsWithDifferentHash_ShouldNotHaveEqualsHashCode() {
        int password1HashCode = Password.fromHash(A_HASH, A_SALT, passwordHasher).hashCode();
        int password2HashCode = Password.fromHash(ANOTHER_HASH, A_SALT.clone(), passwordHasher).hashCode();

        assertThat(password1HashCode).isNotEqualTo(password2HashCode);
    }

    @Test
    public void givenPasswordsWithDifferentSalt_ShouldNotHaveEqualsHashCode() {
        int password1HashCode = Password.fromHash(A_HASH, A_SALT, passwordHasher).hashCode();
        int password2HashCode = Password.fromHash(A_HASH.clone(), ANOTHER_SALT, passwordHasher).hashCode();

        assertThat(password1HashCode).isNotEqualTo(password2HashCode);
    }
}
