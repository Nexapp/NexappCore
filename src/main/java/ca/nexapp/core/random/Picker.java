package ca.nexapp.core.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Picker {

    @SafeVarargs
    public static <E> Optional<E> pickOne(E... items) {
        return pickOne(Arrays.asList(items));
    }

    public static <E> Optional<E> pickOne(Collection<E> items) {
        return pickOne(items, Collections.emptySet());
    }

    public static <E> Optional<E> pickOne(Collection<E> items, Collection<E> toIgnore) {
        return pick(items, 1, toIgnore).stream().findAny();
    }

    public static <E> List<E> pick(Collection<E> items, int limit) {
        return pick(items, limit, Collections.emptySet());
    }

    public static <E> List<E> pick(Collection<E> items, int limit, Collection<E> toIgnore) {
        Random random = new Random();
        List<E> toPickFrom = new ArrayList<>(items);
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
