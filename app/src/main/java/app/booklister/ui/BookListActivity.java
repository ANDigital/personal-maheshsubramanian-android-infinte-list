package app.booklister.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import app.booklister.R;
import app.booklister.ui.fragment.BookListFragment;

/**
 * Activity displaying the fragment for listing books
 */
public class BookListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Fragment bookListFragment = new BookListFragment();
        getFragmentManager().beginTransaction()
            .replace(R.id.books_listContainer, bookListFragment, BookListFragment.class.getCanonicalName())
            .commit();
    }

}
