package app.booklister;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author mahsubramanian
 */
public class BookListerApplication extends Application {

    private static BookListerApplication instance;

    private RequestQueue requestQueue;

    public BookListerApplication() {
        instance = this;
    }

    public static BookListerApplication getInstance() {
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = Volley.newRequestQueue(this);
    }

}
