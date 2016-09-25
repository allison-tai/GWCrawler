import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main( String[] args )
    {

        System.out.println("YAY");

        // TODO: Populate the Bestbuy list
        try {
            List<Game> games = new LinkedList<Game>();
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/playstation-4-games/33934.aspx?path=f7745ff8832df8fd7aa9509ae18c8934en01", games, "PS4");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/playstation-3/24334.aspx?path=97b55ed0c4ce4600a8ae66201e9d8a7fen01", games, "PS3");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-wii-u-games/32276.aspx?path=c2e0396bf4fabe11f83eee8998743759en01", games, "WiiU");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-wii-games/24336.aspx?path=fdd5fd017035e25b90dbe5c1b3ecf623en01", games, "Wii");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-handheld/621901.aspx?path=5aca53d7b7bff9cd44b0c3588418d6b8en01", games, "3DS");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/nintendo-ds-games/22026.aspx?path=b3bec2624c13240ee715674a36bcce48en01", games, "DS");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/xbox-one-games/35511.aspx?path=44a462b3cfb1dace048bd57666031870en01", games, "XBoxOne");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/xbox-360/23393.aspx?path=002f08194a4d0cb1d05cb0c739e6262ben01", games, "XBox360");
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/pc-games/21136.aspx?path=986beba798dde00c1554f6bae338c9d3en01", games, "PC");

            Crawler.processPageSteam("http://store.steampowered.com/search/?sort_by=Reviews_DESC&category1=998&os=win");
        }
        // TODO: Populate the Steam list
        // TODO: Populate the game list using combination of Bestbuy and Steam?

        catch (IOException e) {
        }

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream("src/GamerWatch-ac8c55766009.json"))
                    .setDatabaseUrl("https://gamerwatch-71e5b.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);

            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference("amazon");
            ref.child("Deus Ex: Mankind Divided" + " (" + "Playstation 4" + ")").setValue(new Game("Deus Ex: Mankind Divided", 59.99, "Playstation 4"));
            ref.child("Until Dawn" + " (" + "Playstation 4" + ")").setValue(new Game("Until Dawn", 29.00, "Playstation 4"));

        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file :(");
        }
    }
}
