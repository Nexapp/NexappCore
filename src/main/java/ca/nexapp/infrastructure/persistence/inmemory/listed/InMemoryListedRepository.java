package ca.nexapp.infrastructure.persistence.inmemory.listed;

import java.util.ArrayList;
import java.util.List;

public class InMemoryListedRepository<E> {

    private final List<E> elements = new ArrayList<>();

    protected final void storeElements(Iterable<E> elements) {
        for (E element : elements) {
            storeElement(element);
        }
    }

    protected final void storeElement(E element) {
        elements.add(element);
    }

    protected final List<E> listAll() {
        return new ArrayList<>(elements);
    }

    protected final int countElements() {
        return elements.size();
    }

    protected final boolean contains(E element) {
        return elements.contains(element);
    }

    protected final void clearElements() {
        elements.clear();
    }

}
