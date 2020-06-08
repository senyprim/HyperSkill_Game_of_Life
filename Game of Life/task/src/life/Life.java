package life;

import java.awt.*;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

public class Life {
    private final int width;
    private final int height;
    private final boolean[][]field;


    public Life(int width,int height,int initRnd){
        if (width<=0 || height<=0){
            throw new IllegalArgumentException("Error:width and height can not be negative");
        }
        this.width=width;
        this.height=height;
        field=new boolean[height][width];
        Random rnd = new Random(initRnd);

        for (int y=0;y<this.height;y++)
            for(int x=0;x<this.width;x++)
                field[y][x] = rnd.nextBoolean();

    }
    public void show(){
        for(int y=0;y<this.height;y++){
            drawLine(y);
            System.out.println();
        }
    }

    private void setPoint(int y,int x,boolean value){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        field[y][x]=value;
    }
    private boolean getPoint(int y,int x){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        return field[y][x];
    }
    private void drawLine(int y){
        for(int x=0;x<this.width;x++){
            drawPoint(y,x);
        }
    }



    private void drawPoint(int y, int x){
        if (getPoint(y,x)){
            drawLifePoint(y,x);
        } else {
            drawEmptyPoint(y,x);
        }
    }

    private void drawEmptyPoint(int y, int x) {
        System.out.print(" ");
    }
    private void drawLifePoint(int y, int x) {
        System.out.print("O");
    }
}
