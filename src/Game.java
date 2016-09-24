import java.io.File;
import java.util.TreeMap;

/**
 * Created by Lily on 2016-09-24.
 */
public class Game {
        String _title;
        long _price;
        String _cover;
        String _platform;

        public Game(String title, long price, String platform) {
            _title = title;
            _price = price;
            _platform = platform;
        }

        public void setCover(String cover) {
            _cover = cover;
        }

        public String getTitle() {
            return _title;
        }

        public long getPrice() {
            return _price;
        }

        public String getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
