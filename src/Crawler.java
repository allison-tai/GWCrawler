/**
 * Created by Allison on 2016-09-24.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;

public class Crawler {

    public static List<Game> processPage(String url, List<Game> games, String platform) throws IOException {
        String titlePattern = ".*\\s\\(.*\\).*";
        Pattern pattern = Pattern.compile(titlePattern);
        Document doc = Jsoup.connect(url).timeout(0).get();
        // get products
        Elements products = doc.select("div.item-inner.clearfix");
        // get next page
        String nextUrl = doc.select("li.pagi-next").select("a").attr("abs:href");
        // Make game objects
        for (Element product : products) {
            String title = product.select("h4.prod-title").text();
            Matcher m = pattern.matcher(title);
            if (m.find()) {
                // get image, price, platform
                String imageUrl = product.select("div.prod-image").select("img").first().absUrl("src");
                double price = Double.parseDouble(product.select("div.prodprice").text().replace('$', Character.MIN_VALUE));
                Game game = new Game(title, price, platform);
                game.setCover(getImage(imageUrl));
                games.add(game);
            }
        }
        if (!nextUrl.equals(url))
            processPage(nextUrl, games, platform);
        return games;
    }

    public static Image getImage(String url) {
        try {
            Image image = ImageIO.read(new URL(url)).getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
            return image;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}