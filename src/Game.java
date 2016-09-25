import java.awt.*;
import java.io.File;
import java.util.TreeMap;

/**
 * Created by Lily on 2016-09-24.
 */
public class Game {
        String _title;
        double _price;
        Image _cover;
        String _platform;

        public Game(String title, double price, String platform) {
            _title = title;
            _price = price;
            _platform = platform;
        }

        public void setCover(Image cover) {
            _cover = cover;
        }

        public String getTitle() {
            return _title;
        }

        public double getPrice() {
            return _price;
        }

        public Image getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
