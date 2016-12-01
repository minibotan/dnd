package MapGenerator;
// Fractal Map Maker

public class FMM {

    // размер карты: должен быть степенью 2
    public int size;

    // распределить память
    public int[][] map;
    public int tiles;

    public FMM(int size, int t) {
        this.size = size;
        map = new int[size][size];
        tiles = t;
        int n = 2;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < n; j ++ ) {
                setCell(i * size / n, i * size / n, (int) (Math.random() * (tiles-1)));
            }
        }
        makeFractal(size/(n*2));
    }

    public void setCell(int x, int y, int c) {
        map[x][y]=c;
    }

    public int getCell(int x, int y) {
        return map[x][y];
    }

    public void makeFractal(int step) {
        for (int y=0; y<size; y+=step) {
            for (int x=0; x<size; x+=step) {

                int cx=x+ ((Math.random()<0.5) ? 0 : step);
                int cy=y+ ((Math.random()<0.5) ? 0 : step);

                cx=(cx/(step*2))*step*2;
                cy=(cy/(step*2))*step*2;

                cx=cx%size;
                cy=cy%size;
                setCell(x,y,getCell(cx,cy));
            }
        }
        if (step>1) makeFractal(step/2);
    }

    int[][] get(){
        return map;
    }
}