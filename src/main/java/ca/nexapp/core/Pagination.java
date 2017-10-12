package ca.nexapp.core;

public class Pagination {

    private final int page;
    private final int itemPerPage;
    private final int offset;

    private Pagination(int page, int itemPerPage, int offset) {
        this.page = page;
        this.itemPerPage = itemPerPage;
        this.offset = offset;
    }

    public int page() {
        return page;
    }

    public int itemPerPage() {
        return itemPerPage;
    }

    public int startingIndex() {
        return offset;
    }

    public static Pagination paged(int page, int itemPerPage) {
        page = Math.max(page, 1);
        itemPerPage = Math.max(itemPerPage, 1);
        int offset = (page - 1) * itemPerPage;
        return new Pagination(page, itemPerPage, offset);
    }

    public static Pagination offsetted(int offset, int itemPerPage) {
        itemPerPage = Math.max(itemPerPage, 1);
        offset = Math.max(offset, 0);
        int page = offset / itemPerPage + 1;
        return new Pagination(page, itemPerPage, offset);
    }
}
