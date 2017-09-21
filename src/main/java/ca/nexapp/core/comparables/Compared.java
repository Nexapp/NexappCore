package ca.nexapp.core.comparables;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Compared<T> {

    public final T current;
    public final T compared;

    protected Compared(T current, T compared) {
        this.current = current;
        this.compared = compared;
    }

    public void apply(Consumer<T> toApply) {
        toApply.accept(current);
        toApply.accept(compared);
    }

    public <U> Compared<U> map(Function<T, U> mapper) {
        return Compared.of(mapper.apply(current), mapper.apply(compared));
    }

    public <U> U reduce(BiFunction<T, T, U> reducer) {
        return reducer.apply(current, compared);
    }

    public static <T> Compared<T> of(T current, T compared) {
        return new Compared<>(current, compared);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current, compared);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Compared)) {
            return false;
        }

        Compared<?> other = (Compared<?>) obj;
        return Objects.equals(current, other.current) &&
                Objects.equals(compared, other.compared);
    }

}
