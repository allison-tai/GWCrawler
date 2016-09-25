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

    public static void processPageSteam(String url) throws IOException {
        //connect to video games category
        Document doc = Jsoup.connect(url).timeout(0).get();
        Elements products = doc.select("div.responsive_search_name_combined");
        for (Element product : products) {
            Elements innerProduct = product.select("div.col.search_name.ellipsis");
            System.out.println(innerProduct.select("span.title").text());
            System.out.println(product.select("div.col.search_price.responsive_secondrow").text());

        }
        //Elements products = doc.select("div.col.search_name.ellipsis");
        //String nextUrl = doc.select("div.search_pagination_right").select("a").get(3).attr("abs:href");
        String nextUrl = "";
        Elements pages = doc.select("div.search_pagination_right").select("a");
        boolean isButton = false;
        for (Element page : pages) {
            nextUrl = page.attr("abs:href");
            if (!page.select("a.pagebtn").isEmpty()) {
                isButton = true;
            }
        }
//        for (Element product : products) {
//            System.out.println(product.select("span.title").text());
//            System.out.println(product.select("div.col.search_price.responsive_secondrow").text());
//        }


        if (!nextUrl.equals(url) && isButton)
            processPageSteam(nextUrl);
    }
}