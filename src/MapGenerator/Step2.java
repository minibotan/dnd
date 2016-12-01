package MapGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;


//Don't remember what MapGenerator.Step2 means

public class Step2 {
    int size;
    int tiles;
    int m = 8;
    int map[][];
    int tmap[][];
    public Step2(int[][] map, int t) throws IOException {
        tiles = t;
        size = map.length;
        this.map = map;
        int counter = 0;
        while(counter < 3 && (!map.equals(tmap))){
            counter++;
            tmap = map.clone();
            for(int i = 0; i < size; i ++){
                for (int j = 0; j < size; j++) {
                    map[i][j] = count(i,j);
                }
            }
        }
//        writeInfo();
    }

    int count(int k, int l){
        int[] ans = new int[tiles];
        for(int i = -m; i <= m; i++){
            if(i+k < 0 || i+k >=size) continue;
            for(int j = -m; j<=m; j++) {
                if(j+l < 0 || j+l >=size) continue;
                ans[tmap[i+k][j+l]]++;
            }
        }
        int max = 0;
        for(int i = 1; i < tiles; i ++) {
            max = (ans[max]>ans[i])?(max):(i);
        }
        if(max == 2 && ans[0] > 1){
            max = tiles-1;
        }
        return max;
    }

//    TODO: Locations Counter. BFS or DFS
//    void writeInfo() throws IOException {
//        File file = new File("locations.dnd");
//        file.createNewFile();
//        FileWriter writer = new FileWriter(file);
//            writer.append(countLocations() + "\n");
//        writer.close();
//    }
//
//    private int countLocations() {
//        String[] locations = new String[]{"Land", "Forest", "Water", "Rock"};
//        int[] count = new int[4];
//        for(int i = 0; i < size; i ++){
//            for(int j = 0; j < size; j++){
//                if(){
//
//                }
//            }
//        }
//
//        return 0;
//    }

    int[][] get(){
        return map;
    }

}
