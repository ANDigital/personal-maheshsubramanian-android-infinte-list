package app.booklister.service;

import java.util.List;

import app.booklister.criteria.SearchCriteria;
import app.booklister.model.Book;

/**
 * @author Mahesh Subramanian
 */
public interface FetchBookService {

    /**
     * Get the list of books corresponding to the given search criteria
     * 
     * @param searchCriteria
     * @return
     */
    List<Book> retrieveBooks(SearchCriteria searchCriteria);

}
