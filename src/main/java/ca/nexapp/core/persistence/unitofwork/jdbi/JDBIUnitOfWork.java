package ca.nexapp.core.persistence.unitofwork.jdbi;

import java.util.function.Function;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.tweak.TransactionHandler;

import ca.nexapp.core.persistence.unitofwork.UnitOfWork;
import ca.nexapp.core.persistence.unitofwork.exceptions.AlreadyInTransactionException;
import ca.nexapp.core.persistence.unitofwork.exceptions.NotInTransactionException;

public class JDBIUnitOfWork implements UnitOfWork<Handle> {

    private final ThreadLocal<Handle> unit = new ThreadLocal<>();

    private final DBI dbi;
    private final TransactionHandler transactionHandler;

    public JDBIUnitOfWork(DBI dbi) {
        this.dbi = dbi;
        this.transactionHandler = dbi.getTransactionHandler();
    }

    @Override
    public void begin() throws AlreadyInTransactionException {
        if (isInTransaction()) {
            throw new AlreadyInTransactionException();
        }
        dispose();

        Handle handle = dbi.open();
        transactionHandler.begin(handle);
        unit.set(handle);
    }

    @Override
    public void commit() throws NotInTransactionException {
        try {
            transactionHandler.commit(getHandle());
        } catch (Exception e) {
            transactionHandler.rollback(getHandle());
            throw e;
        } finally {
            dispose();
        }
    }

    @Override
    public void rollback() throws NotInTransactionException {
        try {
            transactionHandler.rollback(getHandle());
        } finally {
            dispose();
        }
    }

    @Override
    public <E> E withTransaction(Function<Handle, E> toExecute) {
        if (!isInTransaction()) {
            return singleQuery(toExecute);
        }

        return toExecute.apply(getHandle());
    }

    @Override
    public <E> E singleQuery(Function<Handle, E> toExecute) {
        return dbi.withHandle(toExecute::apply);
    }

    @Override
    public void dispose() {
        Handle handle = unit.get();
        unit.remove();

        if (handle != null) {
            handle.close();
        }
    }

    private boolean isInTransaction() {
        Handle handle = unit.get();
        return handle != null && transactionHandler.isInTransaction(handle);
    }

    private Handle getHandle() throws NotInTransactionException {
        if (!isInTransaction()) {
            throw new NotInTransactionException();
        }

        return unit.get();
    }
}
