package app.booklister.ui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.booklister.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class BookListActivityTest {

    private static final int EXPECTED_COUNT_OF_LIST_ITEMS = 10;

    @Rule
    public ActivityTestRule<BookListActivity> mActivityRule =
        new ActivityTestRule<>(BookListActivity.class);

    @Test
    public void ensureActivityLoaded() {
        // list view is displayed
        onView(withId(R.id.book_lstBooks)).check(matches(isDisplayed()));

        // sort option selection spinner displayed
        onView(withId(R.id.book_spnSort)).check(matches(isDisplayed()));
    }

    /**
     * Test to check the book list is loaded and it contains the right number of entries (10 in this case)
     */
    @Test
    public void test_ListViewDisplayedWithCorrectEntries() {


        // list view is displayed
        onView(withId(R.id.book_lstBooks)).check(matches(isDisplayed()));

        ListView listBooks = (ListView) mActivityRule.getActivity().findViewById(R.id.book_lstBooks);
        assertEquals("List view should have 10 items in it", EXPECTED_COUNT_OF_LIST_ITEMS, listBooks.getAdapter()
                .getCount());

    }
}