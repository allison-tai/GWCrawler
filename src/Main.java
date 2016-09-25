/**
 * Created by Lily on 2016-09-24.
 */

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main( String[] args )
    {

        System.out.println("YAY");

        // TODO: Populate the Bestbuy list

        // TODO: Populate the Steam list

        // TODO: Populate the game list using combination of Bestbuy and Steam?

        try {
            Crawler.processPage("http://www.bestbuy.ca/en-CA/category/playstation/621715.aspx");
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

        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file :(");
        }
    }
}
