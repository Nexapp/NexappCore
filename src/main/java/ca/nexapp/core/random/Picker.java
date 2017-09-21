package ca.nexapp.core.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Picker<E> {

    private final Collection<E> items;
    private final Random random = new Random();

    public Picker(Collection<E> items) {
        this.items = items;
    }

    public Optional<E> pickOne() {
        return pick(1, new HashSet<>()).stream().findAny();
    }

    public List<E> pick(int limit) {
        return pick(limit, new HashSet<>());
    }

    public List<E> pick(int limit, Collection<E> toIgnore) {
        List<E> toPickFrom = new ArrayList<>(this.items);
        toPickFrom.removeAll(toIgnore);

        List<E> itemsFound = new ArrayList<>();
        while (itemsFound.size() < limit && !toPickFrom.isEmpty()) {
            int indexPicked = random.nextInt(toPickFrom.size());
            E itemPicked = toPickFrom.get(indexPicked);

            toPickFrom.remove(indexPicked);
            itemsFound.add(itemPicked);
        }

        return itemsFound;
    }

}
