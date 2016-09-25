import java.awt.*;
import java.io.File;
import java.util.TreeMap;

/**
 * Created by Lily on 2016-09-24.
 */
public class Game {
        String _title;
        double _regularPrice;
        double _salesPrice;
        Image _cover;
        String _platform;

        public Game() {

        }

        public Game(String title, String platform, double regularPrice, double salesPrice) {
            _title = title;
            _platform = platform;
            _regularPrice = regularPrice;
            _salesPrice = salesPrice;
        }

        public void setCover(Image cover) {
            _cover = cover;
        }

        public void setTitle(String title) {
            _title = title;
        }

        public void setRegularPrice(double regularPrice) {
            _regularPrice = regularPrice;
        }

        public void setSalesPrice(double salesPrice) {
            _salesPrice = salesPrice;
        }

        public void setPlatform(String platform) {
            _platform = platform;
        }

        public String getTitle() {
            return _title;
        }

        public double getRegularPrice() {
            return _regularPrice;
        }

        public double getSalesPrice() {
            return _salesPrice;
        }

        public Image getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
