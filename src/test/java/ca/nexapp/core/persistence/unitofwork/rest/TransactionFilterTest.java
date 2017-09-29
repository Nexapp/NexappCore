package ca.nexapp.core.persistence.unitofwork.rest;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.nexapp.core.persistence.unitofwork.UnitOfWork;

@RunWith(MockitoJUnitRunner.class)
public class TransactionFilterTest {

    @Mock
    private UnitOfWork<Connection> unitOfWork;

    private ServletRequest servletRequest;
    private ServletResponse servletResponse;
    private FilterChain filterChain;

    private TransactionFilter<Connection> transactionFilter;

    @Before
    public void setUp() {
        servletRequest = mock(ServletRequest.class);
        servletResponse = mock(ServletResponse.class);
        filterChain = mock(FilterChain.class);

        transactionFilter = new TransactionFilter<>(unitOfWork);
    }

    @Test
    public void shouldBeginATransaction() throws IOException, ServletException {
        transactionFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(unitOfWork).begin();
    }

    @Test
    public void shouldCommitATransaction() throws IOException, ServletException {
        transactionFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(unitOfWork).commit();
    }

    @Test
    public void shouldDisposeATransaction() throws IOException, ServletException {
        transactionFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(unitOfWork).dispose();
    }

    @Test
    public void givenAnErrorInTheFilterChain_ShouldRollbackTheTransaction() throws IOException, ServletException {
        errorInFilterChain();
        verify(unitOfWork).rollback();
    }

    @Test
    public void givenAnErrorInTheFilterChain_ShouldNotCommitTheTransaction() throws IOException, ServletException {
        errorInFilterChain();
        verify(unitOfWork, never()).commit();
    }

    @Test
    public void givenAnErrorInTheFilterChain_ShouldDisposeTheTransaction() throws IOException, ServletException {
        errorInFilterChain();
        verify(unitOfWork).dispose();
    }

    private void errorInFilterChain() throws IOException, ServletException {
        willThrow(Throwable.class).given(filterChain).doFilter(servletRequest, servletResponse);

        try {
            transactionFilter.doFilter(servletRequest, servletResponse, filterChain);
        } catch (Throwable e) {
            // do nothing
        }
    }

    private class Connection implements AutoCloseable {

        @Override
        public void close() throws Exception {
        }
    }
}
