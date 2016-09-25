import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Game {
        String _id;
        String _title;
        String _cover;
        Map<String, Double> _prices;
        double _regularPrice;
        String _platform;

        public Game() {
        }

        public Game(String id, String title, String platform, double regularPrice) {
            _id = id;
            _title = title;
            _platform = platform;
            _regularPrice = regularPrice;
            _prices = new HashMap<String, Double>();
        }

        public void setId(String id) {
            _id = id;
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

        public void setPrices(Map<String, Double> prices) {
            _prices = prices;
        }

        public void addSalesPrice(String vendor, double salesPrice) {
            _prices.put(vendor, salesPrice);
        }

        public void setPlatform(String platform) {
            _platform = platform;
        }

        public String getId() {
            return _id;
        }

        public String getTitle() {
            return _title;
        }

        public double getRegularPrice() {
            return _regularPrice;
        }

        public Map<String, Double> getPrices() {
            return _prices;
        }

        public double getSalesPrice(String vendor) {
            if (_prices == null) {
                return -1;
            }
            return _prices.get(vendor);
        }

        public String getCover() {
            return _cover;
        }

        public String getPlatform() {
            return _platform;
        }
    }
