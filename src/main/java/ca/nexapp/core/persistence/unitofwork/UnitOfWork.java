package ca.nexapp.core.persistence.unitofwork;

import java.util.function.Consumer;
import java.util.function.Function;

import ca.nexapp.core.persistence.unitofwork.exceptions.AlreadyInTransactionException;
import ca.nexapp.core.persistence.unitofwork.exceptions.NotInTransactionException;

public interface UnitOfWork<T extends AutoCloseable> {

    void begin() throws AlreadyInTransactionException;

    void commit() throws NotInTransactionException;

    void rollback() throws NotInTransactionException;

    default void withTransaction(Consumer<T> toExecute) {
        Function<T, Object> actual = (T transaction) -> {
            toExecute.accept(transaction);
            return null;
        };
        withTransaction(actual);
    }

    <E> E withTransaction(Function<T, E> toExecute);

    default void singleQuery(Consumer<T> toExecute) {
        Function<T, Object> actual = (T transaction) -> {
            toExecute.accept(transaction);
            return null;
        };
        singleQuery(actual);
    }

    <E> E singleQuery(Function<T, E> toExecute);

    void dispose();
}
