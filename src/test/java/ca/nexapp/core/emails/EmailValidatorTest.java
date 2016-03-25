package ca.nexapp.core.emails;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @Before
    public void setUp() {
        emailValidator = mock(EmailValidator.class, CALLS_REAL_METHODS);
    }

    @Test(expected = InvalidEmailException.class)
    public void givenAnInvalidEmail_ShouldThrowAnException() throws InvalidEmailException {
        willReturn(false).given(emailValidator).isValid("example@email.com");

        emailValidator.validate("example@email.com");
    }

    @Test
    public void givenAValidEmail_ShouldNotThrowAnException() throws InvalidEmailException {
        willReturn(true).given(emailValidator).isValid("example@email.com");

        emailValidator.validate("example@email.com");
    }
}
