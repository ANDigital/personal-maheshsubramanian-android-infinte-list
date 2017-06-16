package app.booklister.model;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a book instance
 *
 * @author mahsubramanian
 */
public class Book {

    private String id;

    private VolumeInfo volumeInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public class VolumeInfo {

        private String title;

        @SerializedName("subtitle")
        private String subTitle;

        private List<String> authors;

        private ImageLink imageLinks;

        public String getTitle() {
            return title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public String getAuthors() {
            if(CollectionUtils.isEmpty(authors)) {
                return "";
            }

            return Arrays.toString(authors.toArray());
        }

        public ImageLink getImageLinks() {
            return imageLinks;
        }

    }

    public class ImageLink {

        private String smallThumbnail;

        public String getSmallThumbnail() {
            return smallThumbnail;
        }


    }
}
