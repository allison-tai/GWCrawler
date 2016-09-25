import java.awt.*;
import java.io.File;
import java.util.TreeMap;

public class Game {
        String _title;
        String _cover;
        double _regularPrice;
        double _salesPrice;
        String _platform;

        public Game() {

        }

        public Game(String title, String platform, double regularPrice, double salesPrice) {
            _title = title;
            _platform = platform;
            _regularPrice = regularPrice;
            _salesPrice = salesPrice;
        }

        public void setCover(String cover) {
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

        public String getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
