/**
 * Created by Lily on 2016-09-24.
 */

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main( String[] args )
    {

        System.out.println("YAY");

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream("src/GamerWatch-ac8c55766009.json"))
                    .setDatabaseUrl("https://gamerwatch-71e5b.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);

            // As an admin, the app has access to read and write all data, regardless of Security Rules
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference("game");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object document = dataSnapshot.getValue();
                    System.out.println(document);
                }

                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Error" + databaseError.getMessage());
                }
            });

        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find the file :(");
        }
    }
}
