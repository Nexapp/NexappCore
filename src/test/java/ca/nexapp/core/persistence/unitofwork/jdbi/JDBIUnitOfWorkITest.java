package ca.nexapp.core.persistence.unitofwork.jdbi;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.tweak.TransactionHandler;

import ca.nexapp.core.persistence.unitofwork.UnitOfWork;
import ca.nexapp.core.persistence.unitofwork.UnitOfWorkSuiteITest;

public class JDBIUnitOfWorkITest extends UnitOfWorkSuiteITest<Handle> {

    private final static DBI DBI = new DBI("jdbc:h2:mem:jdbiUoW_tests;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");

    @Before
    @Override
    public void setUp() {
        super.setUp();
        String query = "DROP TABLE IF EXISTS test; CREATE TABLE test (id INT NOT NULL PRIMARY KEY, value TEXT);  ";
        DBI.withHandle(handle -> handle.createStatement(query).execute());
    }

    @Test(expected = RuntimeException.class)
    public void shouldRollbackInCaseOfCommitError() {
        Handle handle = mock(Handle.class);
        TransactionHandler transactionHandler = mock(TransactionHandler.class);
        willThrow(RuntimeException.class).given(transactionHandler).commit(handle);
        JDBIUnitOfWork jdbiUnitOfWork = new JDBIUnitOfWork(aMockedDBI(handle, transactionHandler));

        try {
            jdbiUnitOfWork.begin();
            jdbiUnitOfWork.commit();
        } finally {
            verify(transactionHandler).rollback(handle);
        }
    }

    @Override
    protected UnitOfWork<Handle> get() {
        return new JDBIUnitOfWork(DBI);
    }

    @Override
    protected Consumer<Handle> insert(int key, String value) {
        return (handle) -> {
            handle.createStatement("INSERT INTO test (id, value) VAlUES (:key, :value)")
                    .bind("key", key)
                    .bind("value", value)
                    .execute();
        };
    }

    @Override
    protected Function<Handle, Optional<String>> find(int key) {
        return (handle) -> {
            String value = handle.createQuery("SELECT value FROM test WHERE id = :key")
                    .bind("key", key)
                    .mapTo(String.class)
                    .first();
            return Optional.ofNullable(value);
        };
    }

    private static DBI aMockedDBI(Handle handle, TransactionHandler transactionHandler) {
        DBI dbi = mock(DBI.class);

        willReturn(transactionHandler).given(dbi).getTransactionHandler();
        willReturn(handle).given(dbi).open();

        willReturn(true).given(transactionHandler).isInTransaction(handle);

        return dbi;
    }

}
