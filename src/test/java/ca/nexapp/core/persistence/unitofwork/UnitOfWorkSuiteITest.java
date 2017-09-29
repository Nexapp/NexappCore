package ca.nexapp.core.persistence.unitofwork;

import static com.google.common.truth.Truth.assertThat;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.nexapp.core.persistence.unitofwork.exceptions.AlreadyInTransactionException;
import ca.nexapp.core.persistence.unitofwork.exceptions.NotInTransactionException;

public abstract class UnitOfWorkSuiteITest<T extends AutoCloseable> {

    private static final int A_KEY = 5;
    private static final String A_VALUE = "Hello World!";

    protected UnitOfWork<T> unitOfWork;

    @Before
    public void setUp() {
        unitOfWork = get();
        unitOfWork.begin();
    }

    @Test
    public void shouldBeAbleToQueryEvenWithDisposedTransaction() {
        unitOfWork.dispose();

        unitOfWork.withTransaction(insert(A_KEY, A_VALUE));
        Optional<String> value = unitOfWork.withTransaction(find(A_KEY));

        assertThat(value).isEqualTo(Optional.of(A_VALUE));
    }

    @Test
    public void canQueryOutsideOfTransaction() {
        unitOfWork.withTransaction(insert(A_KEY, A_VALUE));

        Optional<String> foundInTransaction = unitOfWork.withTransaction(find(A_KEY));
        Optional<String> foundOutsideTransaction = unitOfWork.singleQuery(find(A_KEY));

        assertThat(foundInTransaction).isEqualTo(Optional.of(A_VALUE));
        assertThat(foundOutsideTransaction).isEqualTo(Optional.empty());
    }

    @Test
    public void shouldBeAbleToReopenUnitOfWork() {
        unitOfWork.commit();

        unitOfWork.begin();
    }

    @Test
    public void shouldBeAbleToQuery() {
        unitOfWork.withTransaction(insert(A_KEY, A_VALUE));

        Optional<String> value = unitOfWork.withTransaction(find(A_KEY));

        assertThat(value).isEqualTo(Optional.of(A_VALUE));
    }

    @Test
    public void commitShouldKeepChanges() {
        unitOfWork.withTransaction(insert(A_KEY, A_VALUE));

        unitOfWork.commit();
        unitOfWork.begin();

        Optional<String> value = unitOfWork.withTransaction(find(A_KEY));
        assertThat(value).isEqualTo(Optional.of(A_VALUE));
    }

    @Test
    public void rollbackShouldEraseChanges() {
        unitOfWork.withTransaction(insert(A_KEY, A_VALUE));

        unitOfWork.rollback();
        unitOfWork.begin();

        Optional<String> value = unitOfWork.withTransaction(find(A_KEY));
        assertThat(value).isEqualTo(Optional.empty());
    }

    @Test(expected = AlreadyInTransactionException.class)
    public void cannotBeginMultipleTimes() {
        unitOfWork.begin();

        unitOfWork.begin();
    }

    @Test(expected = NotInTransactionException.class)
    public void cannotCommitMultipleTimes() {
        unitOfWork.commit();

        unitOfWork.commit();
    }

    @Test(expected = NotInTransactionException.class)
    public void cannotRollbackMultipleTimes() {
        unitOfWork.rollback();

        unitOfWork.rollback();
    }

    @Test(expected = NotInTransactionException.class)
    public void disposedTransactionShouldNotBeReusable() {
        unitOfWork.dispose();

        unitOfWork.commit();
    }

    @After
    public void tearDown() {
        unitOfWork.dispose();
    }

    protected abstract UnitOfWork<T> get();

    protected abstract Consumer<T> insert(int key, String value);

    protected abstract Function<T, Optional<String>> find(int key);

}
