package app.booklister.service;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.booklister.BookListerApplication;
import app.booklister.criteria.SearchCriteria;
import app.booklister.model.Book;
import app.booklister.model.BookHolder;

/**
 * Real REST-ful implementation to fetch books from google service
 * 
 * @author mahsubramanian
 */
public class OnlineFetchBookServiceImpl implements FetchBookService {

    private static final Logger LOGGER = Logger.getLogger(OnlineFetchBookServiceImpl.class.getSimpleName());

    private Gson gson = new Gson();

    private String restServiceBaseUrl = "https://www.googleapis.com/books/v1/volumes?q=quilting";

    private final static String PARAMETER_START_INDEX = "&startIndex=%s";
    private final static String PARAMETER_PRINT_TYPE = "&printType=%s";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> retrieveBooks(SearchCriteria searchCriteria) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        String finalUrl = constructUrlUsingCriteria(searchCriteria);
        JsonObjectRequest request = new JsonObjectRequest(finalUrl, null, future, future);

        BookListerApplication.getInstance().getRequestQueue().add(request);

        try {
            LOGGER.log(Level.INFO, String.format("Making a request to '%s' to fetch books", finalUrl));

            // This is a blocking call and will time out in 10 seconds if no response is returned
            JSONObject response = future.get(10, TimeUnit.SECONDS);

            final BookHolder holder = gson.fromJson(response.toString(), BookHolder.class);

            if (null != holder && holder.getBooks().size() > 0) {
                LOGGER.log(Level.INFO, String.format("Received %d books", holder.getBooks().size()));
                return holder.getBooks();
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve book information", e);
        }

        return new ArrayList<>();
    }

    private String constructUrlUsingCriteria(SearchCriteria searchCriteria) {
        String url = restServiceBaseUrl;

        if(null != searchCriteria.getStartIndex()) {
            url += String.format(PARAMETER_START_INDEX, searchCriteria.getStartIndex());
        }

        if(null != searchCriteria.getPrintType()) {
            url += String.format(PARAMETER_PRINT_TYPE, searchCriteria.getPrintType());
        }

        return url;
    }
}
