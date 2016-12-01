package MapGenerator;
// Map Creator

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MC {
    int[] tilecol = new int[]{
        (((93)<<8   | 161)<<8   | 48), //grass      0
        (((47)<<8   | 82)<<8    | 24), //forest     1
        (((1)<<8    | 110)<<8   | 189),//water      2
        (((78)<<8   | 87)<<8    | 84), //rock       3
        (((252)<<8  | 221)<<8   | 118) // sand      4
    };
    int n = 1024;

    long time = System.currentTimeMillis();
    int[][] map;
    BufferedImage img = new BufferedImage(n,n, BufferedImage.TYPE_INT_RGB);
    String foldName = "images\\images" + time;  //folder



    public MC() throws IOException {
        for (int k = 0; k < 20; k ++) {
            map = new FMM(n, tilecol.length).get();  //map generation
            map = new CA(map, tilecol.length).get(); //map smooth
            MakePicture(k);                          //create an image
            img = new CC(map, img).getImg();         //add town
            Write(k);                                //get result
        }
    }




    void MakePicture(int k){
        for(int i = 0; i<n; i ++){
            for (int j = 0; j < n; j++) {
                img.setRGB(i,j,tilecol[map[i][j]]);
            }
        }
    }

    void Write(int k){
        try{
            File d = new File(foldName);
            d.mkdir();
        }  catch (Exception e) {}
        String fileName = foldName+"\\image_"+k+".png";
        try {
            File f = new File(fileName);
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
        }
    }

}
