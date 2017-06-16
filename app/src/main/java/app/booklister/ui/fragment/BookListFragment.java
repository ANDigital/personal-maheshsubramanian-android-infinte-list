package app.booklister.ui.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.booklister.R;
import app.booklister.adapter.BookListAdapter;
import app.booklister.criteria.SearchCriteria;
import app.booklister.model.Book;
import app.booklister.model.SortSpinnerOption;
import app.booklister.service.FetchBookService;
import app.booklister.service.OnlineFetchBookServiceImpl;

/**
 * Fragment displaying the list of books
 */
public class BookListFragment extends Fragment implements AbsListView.OnScrollListener {

    private static final Logger LOGGER = Logger.getLogger(BookListFragment.class.getSimpleName());

    private static final int PAGE_COUNT = 10;
    private static final int INITIAL_INDEX = 0;
    private static final int FETCH_DATA_THRESHOLD = 3;

    private int spinnerListenerSetCount = 1;
    private int scrollListenerSetCount = 1;

    private ListView lstBooks;

    private FetchBookService fetchBookService = new OnlineFetchBookServiceImpl();

    private ProgressBar progressBar;

    private Spinner spnSort;

    private BookListAdapter adapter;

    private volatile boolean dataLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new BookListAdapter(getActivity(), R.layout.book_list_item, new ArrayList<Book>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ArrayAdapter<SortSpinnerOption> spinnerArrayAdapter =
            new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getSortOptions());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.book_spnSort);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SortSpinnerOption option = (SortSpinnerOption) parent.getAdapter().getItem(position);
                if (spinnerListenerSetCount != 1) {
                    resetBookAdapter();
                    fetchBooks(INITIAL_INDEX, option.getOption());
                }
                spinnerListenerSetCount++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lstBooks = (ListView) getActivity().findViewById(R.id.book_lstBooks);
        lstBooks.setAdapter(adapter);
        lstBooks.setOnScrollListener(this);

    }

    private void fetchBooks(final int pageCount, final String sortKey) {
        new AsyncTask<Void, Void, List<Book>>() {

            @Override
            protected void onPreExecute() {
                getActivity().findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
            }

            @Override
            protected List<Book> doInBackground(Void... params) {
                return fetchBookService.retrieveBooks(new SearchCriteria(pageCount*PAGE_COUNT, sortKey));
            }

            @Override
            protected void onPostExecute(List<Book> books) {
                if (!isAdded() && null != books && books.size() > 0) {
                    return;
                }

                adapter.addAll(books);
                adapter.notifyDataSetChanged();
                getActivity().findViewById(R.id.progressbar).setVisibility(View.GONE);
                dataLoading = false;
            }
        }.execute();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (dataLoading) {
            return;
        }

        if (scrollListenerSetCount == 1) {
            scrollListenerSetCount++;
            return;
        }

        if (firstVisibleItem + visibleItemCount > totalItemCount - FETCH_DATA_THRESHOLD) {
            int pageToRetrieve = totalItemCount / PAGE_COUNT + 1;
            LOGGER.log(Level.INFO, "First visible item is " + firstVisibleItem + ", visible item count is "
                + visibleItemCount + " and total count is " + totalItemCount);
            LOGGER.log(Level.INFO, "Fetching booksls -al for page count " + pageToRetrieve);
            dataLoading = true;
            fetchBooks(pageToRetrieve, getSortKey());
        }
    }

    private void resetBookAdapter() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    private List<SortSpinnerOption> getSortOptions() {
        List<SortSpinnerOption> options = new ArrayList<>();
        for(SortSpinnerOption value: SortSpinnerOption.values()) {
            options.add(value);
        }
        return options;
    }

    private String getSortKey() {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.book_spnSort);
        return ((SortSpinnerOption) spinner.getSelectedItem()).getOption();
    }

}
