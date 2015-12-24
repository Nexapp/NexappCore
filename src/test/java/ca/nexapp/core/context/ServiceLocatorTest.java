package ca.nexapp.core.context;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ServiceLocatorTest {

    @Before
    public void setUp() {
        ServiceLocator.reset();
    }

    @Test(expected = IllegalStateException.class)
    public void cannotResolveAServiceThatIsNotRegistered() {
        ServiceLocator.locate().resolve(SampleService.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotRegisterTheSameServiceTwice() {
        ServiceLocator.locate().register(SampleService.class, new SampleImplementation());
        ServiceLocator.locate().register(SampleService.class, new SampleImplementation());
    }

    @Test
    public void canResolveAServiceThatIsRegistered() {
        ServiceLocator.locate().register(SampleService.class, new SampleImplementation());

        SampleService resolvedService = ServiceLocator.locate().resolve(SampleService.class);

        assertThat(resolvedService).isInstanceOf(SampleImplementation.class);
    }

    private interface SampleService {
    }

    private class SampleImplementation implements SampleService {
    }
}
