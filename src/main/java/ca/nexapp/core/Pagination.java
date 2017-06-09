package ca.nexapp.core;

public class Pagination {

    private final int page;
    private final int itemPerPage;

    private Pagination(int page, int itemPerPage) {
        this.page = page;
        this.itemPerPage = itemPerPage;
    }

    public int page() {
        return page;
    }

    public int itemPerPage() {
        return itemPerPage;
    }

    public int startingIndex() {
        return (page - 1) * itemPerPage;
    }

    public static Pagination paged(int page, int itemPerPage) {
        return new Pagination(Math.max(page, 1), Math.max(itemPerPage, 1));
    }
}
