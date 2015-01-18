package ca.nexapp.infrastructure.persistence.inmemory.listed;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InMemoryListedRepositoryTest {

    private static final Collection<Integer> MANY_ELEMENTS = Arrays.asList(1, 2, 3, 4, 5);

    private static final Integer AN_ELEMENT = 12;

    private InMemoryListedRepository<Integer> repository;

    @Before
    public void setUp() {
        repository = new InMemoryListedRepository<>();
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
    public void givenManyElementsWhenStoringThemShouldContainsThemAll() {
        repository.storeElements(MANY_ELEMENTS);
        Collection<Integer> allValues = repository.listAll();
        assertTrue(allValues.containsAll(MANY_ELEMENTS));
    }

    @Test
    public void givenAnEmptyRepositoryWhenListingAllValuesShouldReturnAnEmptyList() {
        List<Integer> allValues = repository.listAll();
        boolean hasNoElements = allValues.isEmpty();
        assertTrue(hasNoElements);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheListShouldContainsTheElement() {
        repository.storeElement(AN_ELEMENT);

        List<Integer> allValues = repository.listAll();
        boolean hasElement = allValues.contains(AN_ELEMENT);

        assertTrue(hasElement);
    }

    @Test
    public void givenAnElementWhenListingAllValuesThenTheListShouldHaveASizeOfOne() {
        repository.storeElement(AN_ELEMENT);

        List<Integer> allValues = repository.listAll();
        int sizeOfCollection = allValues.size();

        assertEquals(1, sizeOfCollection);
    }

    @Test
    public void givenAnElementWhenClearingTheRepositoryShouldNotHaveAnyElements() {
        repository.storeElement(AN_ELEMENT);
        repository.clearElements();

        int numberOfElements = repository.countElements();

        assertEquals(0, numberOfElements);
    }

    @Test
    public void givenAnElementWhenClearingTheRepositoryShouldNotContainsTheElement() {
        repository.storeElement(AN_ELEMENT);
        repository.clearElements();

        boolean hasElement = repository.contains(AN_ELEMENT);

        assertFalse(hasElement);
    }
}
