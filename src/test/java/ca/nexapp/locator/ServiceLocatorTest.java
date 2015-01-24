package ca.nexapp.locator;

import static com.google.common.truth.Truth.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class ServiceLocatorTest {

    private static final Class<?> UNEXISTING_SERVICE = Integer.class;
    private static final Object A_SERVICE_IMPLEMENTATION = mock(Object.class);

    @Before
    public void setUp() {
        ServiceLocator locator = new ServiceLocator();

        locator.register(Object.class, A_SERVICE_IMPLEMENTATION);

        ServiceLocator.load(locator);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenRetrievingAnUnexistingServiceShouldThrowAnException() {
        ServiceLocator.find(UNEXISTING_SERVICE);
    }

    @Test
    public void whenRetrievingAnExistingRepositoryShouldReturnTheCorrespondingInstance() {
        Object repositoryFound = ServiceLocator.find(Object.class);

        assertThat(repositoryFound).isEqualTo(A_SERVICE_IMPLEMENTATION);
    }
}
