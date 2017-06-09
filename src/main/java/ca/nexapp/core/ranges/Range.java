package ca.nexapp.core.ranges;

import java.util.Objects;

public class Range<E extends Comparable<E>> {

    private final E from;
    private final E to;

    public Range(E from, E to) {
        this.from = from;
        this.to = to;
    }

    public E from() {
        return from;
    }

    public E to() {
        return to;
    }

    public boolean isOverlapping(Range<E> other) {
        return from.compareTo(other.to) <= 0 && other.from.compareTo(to) <= 0;
    }

    public static <E extends Comparable<E>> Range<E> of(E from, E to) {
        return new Range<>(from, to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }

        Range<?> other = (Range<?>) obj;
        return Objects.equals(from, other.from) && Objects.equals(to, other.to);
    }

    @Override
    public String toString() {
        return from + "-" + to;
    }
}
