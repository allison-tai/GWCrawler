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
        Elements divs = doc.select("div");
        List<Element> prods = new LinkedList<Element>();
        for (Element div : divs) {
            if (div.hasClass("prod-info"))
                prods.add(div);
        }
        for (Element prod : prods) {
            System.out.println(prod.html());
        }
    }
}