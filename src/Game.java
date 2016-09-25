import java.io.File;
import java.util.TreeMap;

/**
 * Created by Lily on 2016-09-24.
 */
public class Game {
        String _title;
        double _price;
        String _cover;
        String _platform;

        public Game() {

        }

        public Game(String title, double price, String platform) {
            _title = title;
            _price = price;
            _platform = platform;
        }

        public void setCover(String cover) {
            _cover = cover;
        }

        public void setTitle(String title) {
            _title = title;
        }

        public void setPrice(double price) {
            _price = price;
        }

        public void setPlatform(String platform) {
            _platform = platform;
        }

        public String getTitle() {
            return _title;
        }

        public double getPrice() {
            return _price;
        }

        public String getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
