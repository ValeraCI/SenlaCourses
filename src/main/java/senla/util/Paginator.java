package senla.util;

public class Paginator {

    public static Long getFirstElement(Long pageNumber, Long totalElements, Integer elementsPerPage){
        Long firstResult = (pageNumber - 1) * elementsPerPage;

        if (firstResult < 0) {
            firstResult = 0L;
        } else if (firstResult > totalElements) {
            firstResult = Paginator.getLastPageNumber(totalElements, elementsPerPage);
        }

        return firstResult;
    }
    public static Long getLastPageNumber(Long totalElements, Integer elementsPerPage) {
        Long remainder = totalElements % elementsPerPage;
        if (remainder == 0) {
            return totalElements - elementsPerPage;
        } else {
            return totalElements - remainder;
        }
    }

    public static Integer limitingMinimumValueToOne(Integer num){
        return num < 1 ? 1 : num;
    }
}
