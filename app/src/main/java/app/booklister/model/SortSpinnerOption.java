package app.booklister.model;

/**
 * @author mahsubramanian
 */
public enum SortSpinnerOption {


    OPTION_ALL("all"), //
    OPTION_BOOKS_ONLY("books"), //
    OPTION_MAGAZINE_ONLY ("magazines");

    private final String option;

    SortSpinnerOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    @Override
    public String toString() {
        return option;
    }
}
