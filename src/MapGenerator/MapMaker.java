package MapGenerator;
// Map Creator

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapMaker {
    int[] tilecol = new int[]{
        (((93)<<8   | 161)<<8   | 48), //grass      0
        (((47)<<8   | 82)<<8    | 24), //forest     1
        (((1)<<8    | 110)<<8   | 189),//water      2
        (((78)<<8   | 87)<<8    | 84), //rock       3
        (((252)<<8  | 221)<<8   | 118) // sand      4
    };
    int n = 1024;

    long time;
    int[][] map;
    BufferedImage img = new BufferedImage(n,n, BufferedImage.TYPE_INT_RGB);
    String foldName;  //folder



    public MapMaker(long time) throws IOException {
        this.time = time;
        map = new Step1(n, tilecol.length).get();  //Fractal map generation
        MakePicture();
//        Write("0");
        map = new Step2(map, tilecol.length).get(); //map smooth
        MakePicture();
//        Write("1");
        img = new Step3(map, img, time).getImg();         //add town
        Write("2");
    }




    void MakePicture(){
        for(int i = 0; i<n; i ++){
            for (int j = 0; j < n; j++) {
                img.setRGB(i,j,tilecol[map[i][j]]);
            }
        }
    }

    void Write(String step){
        foldName = "Results\\"+time;
        String fileName = foldName+"\\Map_"+step+".png";
        try {
            File f = new File(fileName);
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
        }
    }

}
