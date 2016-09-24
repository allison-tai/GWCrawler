/**
 * Created by Lily on 2016-09-24.
 */

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main( String[] args )
    {

        System.out.println("YAY");

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream("path/to/serviceAccountCredentials.json"))
                    .setDatabaseUrl("https://databaseName.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        }
        catch (FileNotFoundException e) {

        }
    }
}
