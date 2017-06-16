package app.booklister.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mahsubramanian
 */
public class BookHolder {

    @SerializedName("items")
    private List<Book> books;

    @SerializedName("totalItems")
    private String total;

    public List<Book> getBooks() {
        return books;
    }

    public String getTotal() {
        return total;
    }
}
