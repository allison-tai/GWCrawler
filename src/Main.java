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
        List<Game> games = new LinkedList<Game>();
        try {
            // TODO: Populate the Steam list
           // Crawler.processPageSteam("http://store.steampowered.com/search/?sort_by=Reviews_DESC&category1=998&os=win");

            // TODO: Populate BestBuy list
            Crawler.bbCrawl(games);
        }
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

            DatabaseReference bbRef = FirebaseDatabase
                    .getInstance()
                    .getReference("game");
            for (Game game: games) {
                bbRef.child(game.getTitle()).setValue(game);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file :(");
        }
    }
}
