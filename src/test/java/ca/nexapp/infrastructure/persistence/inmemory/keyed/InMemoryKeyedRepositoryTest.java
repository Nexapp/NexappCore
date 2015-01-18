package ca.nexapp.infrastructure.persistence.inmemory.keyed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertTrue(hasElement);
    }

    @Test
    public void givenAnEmptyRepositoryWhenStoringAnElementShouldHaveOneElement() {
        repository.storeElement(AN_ELEMENT);
        int numberOfElements = repository.countElements();
        assertEquals(1, numberOfElements);
    }

    @Test
    public void givenAnElementWhenRetrievingThisElementShouldReturnTheSameElement() {
        repository.storeElement(AN_ELEMENT);
        Integer elementRetrieved = repository.getFromKeys(ELEMENT_KEYS);
        assertEquals(AN_ELEMENT, elementRetrieved);
    }

    @Test
    public void givenManyElementsWhenStoringThemShouldContainsThemAll() {
        repository.storeElements(MANY_ELEMENTS);
        Collection<Integer> allValues = repository.listAll();
        assertTrue(allValues.containsAll(MANY_ELEMENTS));
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
        boolean hasNoElements = allValues.isEmpty();
        assertTrue(hasNoElements);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheCollectionShouldContainsTheElement() {
        repository.storeElement(AN_ELEMENT);

        Collection<Integer> allValues = repository.listAll();
        boolean hasElement = allValues.contains(AN_ELEMENT);

        assertTrue(hasElement);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheCollectionShouldHaveASizeOfOne() {
        repository.storeElement(AN_ELEMENT);

        Collection<Integer> allValues = repository.listAll();
        int sizeOfCollection = allValues.size();

        assertEquals(1, sizeOfCollection);
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
