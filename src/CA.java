/**
 * Created by Ирек on 24.11.2016.
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;


//Don't remember what CA means

public class CA {
    int size;
    int tiles;
    int m = 8;
    int map[][];
    int tmap[][];
    public CA(int[][] map, int t){
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

    int[][] get(){
        return map;
    }

}
