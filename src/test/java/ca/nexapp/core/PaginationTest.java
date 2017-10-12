package ca.nexapp.core;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class PaginationTest {

    @Test
    public void canCreateAPaginationFromAPage() {
        int page = 4;
        int itemPerPage = 5;
        Pagination pagination = Pagination.paged(page, itemPerPage);

        assertThat(pagination.page()).isEqualTo(4);
        assertThat(pagination.itemPerPage()).isEqualTo(5);
        assertThat(pagination.startingIndex()).isEqualTo(15);
    }

    @Test
    public void aPageStartsAtOne() {
        Pagination pagination = Pagination.paged(0, 10);

        assertThat(pagination.page()).isEqualTo(1);
        assertThat(pagination.itemPerPage()).isEqualTo(10);
        assertThat(pagination.startingIndex()).isEqualTo(0);
    }

    @Test
    public void cannotHaveANegativePage() {
        Pagination pagination = Pagination.paged(-4, 10);

        assertThat(pagination.page()).isEqualTo(1);
        assertThat(pagination.itemPerPage()).isEqualTo(10);
        assertThat(pagination.startingIndex()).isEqualTo(0);
    }

    @Test
    public void canCreateAPaginationFromAnOffset() {
        int offset = 5;
        int itemPerPage = 20;
        Pagination pagination = Pagination.offsetted(offset, itemPerPage);

        assertThat(pagination.page()).isEqualTo(1);
        assertThat(pagination.itemPerPage()).isEqualTo(20);
        assertThat(pagination.startingIndex()).isEqualTo(5);
    }

    @Test
    public void canStartAnOffsetAtZero() {
        Pagination pagination = Pagination.offsetted(0, 20);

        assertThat(pagination.page()).isEqualTo(1);
        assertThat(pagination.itemPerPage()).isEqualTo(20);
        assertThat(pagination.startingIndex()).isEqualTo(0);
    }

    @Test
    public void cannotHaveANegativeOffset() {
        Pagination pagination = Pagination.offsetted(-5, 20);

        assertThat(pagination.page()).isEqualTo(1);
        assertThat(pagination.itemPerPage()).isEqualTo(20);
        assertThat(pagination.startingIndex()).isEqualTo(0);
    }

    @Test
    public void givenAPagedPagination_CanRetrieveASubsetOfACollection() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Pagination pagination = Pagination.paged(1, 4);

        List<Integer> results = numbers.stream()
                .skip(pagination.startingIndex())
                .limit(pagination.itemPerPage())
                .collect(Collectors.toList());

        assertThat(results).containsExactly(1, 2, 3, 4).inOrder();
    }

    @Test
    public void givenAnOffsettedPagination_CanRetrieveASubsetOfACollection() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Pagination pagination = Pagination.offsetted(0, 3);

        List<Integer> results = numbers.stream()
                .skip(pagination.startingIndex())
                .limit(pagination.itemPerPage())
                .collect(Collectors.toList());

        assertThat(results).containsExactly(1, 2, 3).inOrder();
    }
}
