package ca.nexapp.core.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PasswordTest {

    byte[] aSalt = new byte[]{1, 2, 3, 4};
    byte[] aHash = new byte[]{11, 22, 33, 44, 55, 66, 77, 88};

    @Test
    public void givenASameHashAndSalt_ShouldBeEquals() {
        Password password1 = Password.fromHash(aHash, aSalt, null);
        Password password2 = Password.fromHash(aHash.clone(), aSalt.clone(), null);

        assertThat(password1).isEqualTo(password2);
    }

    @Test
    public void givenASameHashAndSalt_ShouldHaveEqualsHashCode() {
        int password1HashCode = Password.fromHash(aHash, aSalt, null).hashCode();
        int password2HashCode = Password.fromHash(aHash.clone(), aSalt.clone(), null).hashCode();

        assertThat(password1HashCode).isEqualTo(password2HashCode);
    }
}
