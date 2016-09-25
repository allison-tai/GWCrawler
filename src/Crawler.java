/**
 * Created by Allison on 2016-09-24.
 */
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    // need a field representing the database

    public static void processPage(String url) throws IOException {
        //connect to video games category
        Document doc = Jsoup.connect(url).timeout(0).get();
        Elements products = doc.select("div.prod-info");
        String nextUrl = doc.select("li.pagi-next").select("a").attr("abs:href");
        for (Element product : products) {
                System.out.println(product.select("h4.prod-title").text());
                System.out.println(product.select("div.prodprice").text());
        }
        if (!nextUrl.equals(url))
            processPage(nextUrl);
    }
}