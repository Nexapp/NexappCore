package ca.nexapp.core.authentication;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class AuthenticableTest {

    @Test
    public void givenAMatchingPassword_ShouldMatches() {
        Authenticable authenticable = matchable(true);

        assertThat(authenticable.matches("a.password")).isTrue();
    }

    @Test
    public void givenANonMatchingPassword_ShouldNotMatches() {
        Authenticable authenticable = matchable(false);

        assertThat(authenticable.matches("a.password")).isFalse();
    }

    @Test
    public void givenAMatchablePassword_WhenChangingThePassword_ShouldSetTheNewPassword() {
        Authenticable authenticable = matchable(true);
        Password newPassword = mock(Password.class);

        authenticable.changePassword("old.password", newPassword);

        verify(authenticable).setPassword(newPassword);
    }

    @Test(expected = InvalidPasswordException.class)
    public void givenANonMatchablePassword_WhenChangingThePassword_ShouldThrowAnException() {
        Authenticable authenticable = matchable(false);

        authenticable.changePassword("old.password", mock(Password.class));
    }

    private Authenticable matchable(boolean matches) {
        Password password = mock(Password.class);
        willReturn(matches).given(password).matches(anyString());
        return spy(new Authenticable() {

            @Override
            public Password getPassword() {
                return password;
            }

            @Override
            public void setPassword(Password password) {

            }
        });
    }

}
