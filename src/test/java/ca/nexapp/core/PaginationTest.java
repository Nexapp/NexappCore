package ca.nexapp.core;

import static com.google.common.truth.Truth.assertThat;

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
    public void canCreateAPaginationFromAnOffset() {
        int offset = 15;
        int itemPerPage = 5;
        Pagination pagination = Pagination.offsetted(offset, itemPerPage);

        assertThat(pagination.page()).isEqualTo(4);
        assertThat(pagination.itemPerPage()).isEqualTo(5);
        assertThat(pagination.startingIndex()).isEqualTo(15);
    }
}
