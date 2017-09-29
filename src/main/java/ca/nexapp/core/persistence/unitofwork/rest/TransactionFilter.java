package ca.nexapp.core.persistence.unitofwork.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ca.nexapp.core.persistence.unitofwork.UnitOfWork;

public class TransactionFilter<T extends AutoCloseable> implements Filter {

    private final UnitOfWork<T> unitOfWork;

    public TransactionFilter(UnitOfWork<T> unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // nothing to do
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            unitOfWork.begin();
            chain.doFilter(request, response);
            unitOfWork.commit();
        } catch (Throwable e) {
            unitOfWork.rollback();
        } finally {
            unitOfWork.dispose();
        }
    }

    @Override
    public void destroy() {
        // nothing to do
    }
}
