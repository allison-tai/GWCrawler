/**
 * Created by Allison on 2016-09-24.
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.xml.bind.DatatypeConverter;

public class Crawler {

    public static void bbCrawl(List<Game> games) throws IOException {
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/playstation-4-games/33934.aspx?path=f7745ff8832df8fd7aa9509ae18c8934en01", games, "PS4");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/playstation-3/24334.aspx?path=97b55ed0c4ce4600a8ae66201e9d8a7fen01", games, "PS3");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-wii-u-games/32276.aspx?path=c2e0396bf4fabe11f83eee8998743759en01", games, "WiiU");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-wii-games/24336.aspx?path=fdd5fd017035e25b90dbe5c1b3ecf623en01", games, "Wii");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-handheld/621901.aspx?path=5aca53d7b7bff9cd44b0c3588418d6b8en01", games, "3DS");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-ds-games/22026.aspx?path=b3bec2624c13240ee715674a36bcce48en01", games, "DS");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/xbox-one-games/35511.aspx?path=44a462b3cfb1dace048bd57666031870en01", games, "XBoxOne");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/xbox-360/23393.aspx?path=002f08194a4d0cb1d05cb0c739e6262ben01", games, "XBox360");
            //Crawler.processPage("http://www.bestbuy.ca/en-CA/category/pc-games/21136.aspx?path=986beba798dde00c1554f6bae338c9d3en01", games, "PC");
    }

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
            String productName = product.select("h4.prod-title").text();
            Matcher m = pattern.matcher(productName);
            if (m.find()) {
                // get image, price, platform
                String imageUrl = product.select("div.prod-image").select("img").first().absUrl("src");
                double price = Double.parseDouble(product.select("div.prodprice").text().replace('$', Character.MIN_VALUE));
                String title = productName.replaceAll("\\(.*\\)", "(" + platform + ")");
                Game game = new Game(title, title, platform, price);
                BufferedImage img = getImage(imageUrl);
                if (img != null) {
                    game.setCover(imageToString(img));
                }
                games.add(game);
            }
        }
        //if (!nextUrl.equals(url))
            //processPage(nextUrl, games, platform);
        return games;
    }

    public static String imageToString(BufferedImage image) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(image, "png", output);
        byte[] imageBytes = output.toByteArray();
        return DatatypeConverter.printBase64Binary(imageBytes);
    }


    public static BufferedImage getImage(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            return image;
        }

        catch (IOException e){
//            System.out.println("ERROR in getImage: " + url);
//            System.out.println(e);
//            try {
//                InputStream stream = new ByteArrayInputStream(url.getBytes(StandardCharsets.UTF_8));
//                //BufferedImage img =
//                //        JPEGCodec.createJPEGDecoder(stream).decodeAsBufferedImage();
//                BufferedImage img = convertImage(stream);
//                System.out.println("Successfully decoded into an image");
//                return img;
//            } catch (Exception e2) {
//                System.out.println("Failed again");
//            }
            return null;

        }

    }

    public static BufferedImage convertImage(InputStream s) throws IOException {
        //Find a suitable ImageReader
        Iterator readers = ImageIO.getImageReadersByFormatName("PNG");
        ImageReader reader = null;
        while(readers.hasNext()) {
            reader = (ImageReader)readers.next();
            if(reader.canReadRaster()) {
                break;
            }
        }

        //Stream the image file (the original CMYK image)
        ImageInputStream input =   ImageIO.createImageInputStream(s);
        reader.setInput(input);
        ImageReadParam param = reader.getDefaultReadParam();
        BufferedImage bi = reader.read(0, param);

        //Read the image raster
        //Raster raster = reader.readRaster(0, null);

        //Create a new RGB image
        //BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(),
        //        BufferedImage.TYPE_4BYTE_ABGR);

        //Fill the new image with the old raster
        //bi.getRaster().setRect(raster);
        return bi;
    }

    public static void processPageSteam(String url, List<Game> games, String platform) throws IOException {
        //connect to video games category
        List<Game> tempGames = new LinkedList<Game>();

        Document doc = Jsoup.connect(url).timeout(0).get();
        Elements products = doc.select("div.responsive_search_name_combined");
        for (Element product : products) {
            Elements innerProduct = product.select("div.col.search_name.ellipsis");
            String title = innerProduct.select("span.title").text();
            //System.out.println(innerProduct.select("span.title").text());
            String priceString = product.select("div.col.search_price.responsive_secondrow").text();
            double regularPrice = 0;
            double salesPrice = 0;
            if (priceString.charAt(0) != 'F') {
                String prefix = priceString.substring(0, 5);
                String rawPrices = priceString.replace(prefix, "");
                String[] prices = rawPrices.split(" ");
                String regularPriceString = prices[0];
                String salesPriceString = regularPriceString;
                if (prices.length > 1) {
                    salesPriceString = prices[1];
                }
                regularPrice = Double.parseDouble(regularPriceString);
                salesPrice = Double.parseDouble(salesPriceString);
            }
            Game game = new Game(title, title, platform, regularPrice);
            game.addSalesPrice("Steam", salesPrice);
            tempGames.add(game);
        }
        Elements covers = doc.select("div.col.search_capsule");
        int index = 0;
        for (Element cover : covers) {
            String imgUrl = cover.select("img").attr("abs:src");
            BufferedImage img = getImage(imgUrl);
            if (img != null) {
                tempGames.get(index).setCover(imageToString(img));
            }
            index++;
        }
        // append the list of games to the end of the games list
        games.addAll(tempGames);

        String nextUrl = "";
        Elements pages = doc.select("div.search_pagination_right").select("a");
        boolean isButton = false;
        for (Element page : pages) {
            nextUrl = page.attr("abs:href");
            if (!page.select("a.pagebtn").isEmpty()) {
                isButton = true;
            }
        }

        if (!nextUrl.equals(url) && isButton)
            processPageSteam(nextUrl, games, platform);
    }
}