/**
 * Created by Allison on 2016-09-24.
 */
import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    // need a field representing the database

    public static void processPage(String url) throws IOException{
        //connect to video games category
        Document doc = Jsoup.connect(url).timeout(0).get();
        Elements divs = doc.select("div");
        for (Element elem : divs)
            System.out.println(elem.html());
        }
    }