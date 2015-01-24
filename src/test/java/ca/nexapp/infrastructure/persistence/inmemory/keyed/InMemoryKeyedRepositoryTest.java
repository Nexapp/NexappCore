package ca.nexapp.infrastructure.persistence.inmemory.keyed;

import static com.google.common.truth.Truth.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class InMemoryKeyedRepositoryTest {

    private static final Collection<Integer> MANY_ELEMENTS = Arrays.asList(1, 2, 3, 4, 5);

    private static final Integer AN_ELEMENT = 12;

    private static final Object[] ELEMENT_KEYS = { -AN_ELEMENT };

    private static final Object[] KEYS_OF_UNEXISTING_ELEMENT = { -9999999 };

    private InMemoryKeyedRepository<Integer> repository;

    @Before
    public void setUp() {
        repository = buildRepository();
    }

    @Test
    public void whenStoringAnElementShouldContainsTheElement() {
        repository.storeElement(AN_ELEMENT);
        boolean hasElement = repository.contains(AN_ELEMENT);
        assertThat(hasElement).isTrue();
    }

    @Test
    public void givenAnEmptyRepositoryWhenStoringAnElementShouldHaveOneElement() {
        repository.storeElement(AN_ELEMENT);
        int numberOfElements = repository.countElements();
        assertThat(numberOfElements).is(1);
    }

    @Test
    public void givenAnElementWhenRetrievingThisElementShouldReturnTheSameElement() {
        repository.storeElement(AN_ELEMENT);
        Integer elementRetrieved = repository.getFromKeys(ELEMENT_KEYS);
        assertThat(elementRetrieved).isEqualTo(AN_ELEMENT);
    }

    @Test
    public void givenManyElementsWhenStoringThemShouldContainsThemAll() {
        repository.storeElements(MANY_ELEMENTS);
        Collection<Integer> allValues = repository.listAll();
        assertThat(allValues).containsAllIn(MANY_ELEMENTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnEmptyRepositoryWhenRetrievingAnElementShouldThrowAnException() {
        repository.getFromKeys(ELEMENT_KEYS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAnElementWhenRetrievingAnUnexistingElementShouldThrowAnException() {
        repository.storeElement(AN_ELEMENT);

        repository.getFromKeys(KEYS_OF_UNEXISTING_ELEMENT);
    }

    @Test
    public void givenAnEmptyRepositoryWhenListingAllValuesShouldReturnAnEmptyCollection() {
        Collection<Integer> allValues = repository.listAll();

        assertThat(allValues).hasSize(0);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheCollectionShouldContainsTheElement() {
        repository.storeElement(AN_ELEMENT);
        Collection<Integer> allValues = repository.listAll();
        assertThat(allValues).contains(AN_ELEMENT);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheCollectionShouldHaveASizeOfOne() {
        repository.storeElement(AN_ELEMENT);
        Collection<Integer> allValues = repository.listAll();
        assertThat(allValues).hasSize(1);
    }

    private InMemoryKeyedRepository<Integer> buildRepository() {
        return new InMemoryKeyedRepository<Integer>() {

            @Override
            protected Object[] getUniqueKeysFrom(Integer element) {
                return new Object[] { -element };
            }
        };
    }
}
