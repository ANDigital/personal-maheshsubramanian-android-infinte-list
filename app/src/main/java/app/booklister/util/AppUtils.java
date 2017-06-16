package app.booklister.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application wide utilities
 *
 * @author mahsubramanian
 */
public class AppUtils {

    private static final Logger LOGGER = Logger.getLogger(AppUtils.class.getSimpleName());

    /**
     * Return JSON in assets folder as string
     *
     * @return
     */
    public static String loadJSONFromAsset(Context context, String jsonFile) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(jsonFile);
            StringBuilder sb = new StringBuilder(2048); // Define a size if you have an idea of it.
            char[] read = new char[128]; // Your buffer size.
            try (InputStreamReader ir = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                for (int i; -1 != (i = ir.read(read)); sb.append(read, 0, i));
            } catch (Throwable t) {}
            return sb.toString();
            
        } catch (IOException e) {
            // throw
            LOGGER.log(Level.SEVERE,
                String.format("Unable to open input stream to load JSON file. Exception: %s", e.getMessage()));
            return null;

        } finally {
            if(null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
