package senla.util;

public class Paginator {
    public static Long getLastPageNumber(Long totalElements, Integer elementsPerPage) {
        Long remainder = totalElements % elementsPerPage;
        if (remainder == 0) {
            return totalElements - elementsPerPage;
        } else {
            return totalElements - remainder;
        }
    }
}
