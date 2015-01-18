package ca.nexapp.infrastructure.persistence.inmemory.keyed;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class InMemoryKeyedRepository<E> {

    private Map<Integer, E> elements = new HashMap<>();

    protected final void storeElements(Collection<E> elements) {
        for (E element : elements) {
            storeElement(element);
        }
    }

    protected final void storeElement(E element) {
        Object[] keys = getUniqueKeysFrom(element);
        int key = hashKeys(keys);

        elements.put(key, element);
    }

    protected final E getFromKeys(Object[] uniqueKeys) {
        int id = hashKeys(uniqueKeys);

        if (!elements.containsKey(id)) {
            throw new IllegalArgumentException("The element cannot be found.");
        }

        return elements.get(id);
    }

    protected final Collection<E> listAll() {
        return elements.values();
    }

    protected final int countElements() {
        return elements.size();
    }

    protected final boolean contains(E element) {
        Object[] uniqueKeys = getUniqueKeysFrom(element);
        int id = hashKeys(uniqueKeys);
        return elements.containsKey(id);
    }

    protected abstract Object[] getUniqueKeysFrom(E element);

    private int hashKeys(Object... uniqueKeys) {
        return Objects.hash(uniqueKeys);
    }

}
