package app.booklister.ui.viewholders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author mahsubramanian
 */
public class BookRowViewHolder {

    private TextView title;

    private ImageView thumbnail;

    private TextView subTitle;

    private TextView authors;


    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }


    public TextView getAuthors() {
        return authors;
    }

    public void setAuthors(TextView authors) {
        this.authors = authors;
    }

    public TextView getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(TextView subTitle) {
        this.subTitle = subTitle;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
