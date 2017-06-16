package app.booklister.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.booklister.R;
import app.booklister.model.Book;
import app.booklister.ui.viewholders.BookRowViewHolder;

/**
 * @author mahsubramanian
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    private int thumbnailSize;

    public BookListAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        thumbnailSize = (int) getContext().getResources().getDimension(R.dimen.thumbnail_size);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Book item = getItem(position);
        final BookRowViewHolder viewHolder;
        final View rowView;

        if (null != convertView) {
            viewHolder = (BookRowViewHolder) convertView.getTag();
            rowView = convertView;
        } else {
            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            rowView = layoutInflater.inflate(R.layout.book_list_item, parent, false);
            viewHolder = new BookRowViewHolder();

            viewHolder.setTitle((TextView) rowView.findViewById(R.id.book_list_txtTitle));
            viewHolder.setSubTitle((TextView) rowView.findViewById(R.id.book_list_txtSubTitle));
            viewHolder.setAuthors((TextView) rowView.findViewById(R.id.book_list_txtAuthors));
            viewHolder.setThumbnail((ImageView) rowView.findViewById(R.id.book_list_imgThumbnail));
            rowView.setTag(viewHolder);
        }

        viewHolder.getTitle().setText(item.getVolumeInfo().getTitle());
        viewHolder.getSubTitle().setText(item.getVolumeInfo().getSubTitle());
        viewHolder.getAuthors().setText(item.getVolumeInfo().getAuthors());

        Picasso.with(getContext()) //
                .load(item.getVolumeInfo().getImageLinks().getSmallThumbnail()) //
                .resize(thumbnailSize, thumbnailSize) //
                .into(viewHolder.getThumbnail());

        return rowView;

    }
}
