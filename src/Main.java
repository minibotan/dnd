import MapGenerator.MapMaker;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ирек on 29.11.2016.
 */
public class Main {

    public static void main(String args[]) throws IOException {
        long time = System.currentTimeMillis();
        try{
            File d = new File("Results\\"+time);
            d.mkdir();
        }  catch (Exception e) {}
        new MapMaker(time);
    }


}
