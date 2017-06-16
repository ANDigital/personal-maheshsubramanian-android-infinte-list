package app.booklister.criteria;

/**
 * @author msubramanian
 */

public class SearchCriteria {

    private Integer startIndex;

    private String printType;

    public SearchCriteria(Integer startIndex, String printType) {
        this.startIndex = startIndex;
        this.printType = printType;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public String getPrintType() {
        return printType;
    }

}
