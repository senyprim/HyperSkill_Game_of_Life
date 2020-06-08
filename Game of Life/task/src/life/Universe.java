package life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Universe {
    private int width;
    private int height;
    private Cell[][] universe;

    public Universe(Cell[][] universe){
        this.height=universe.length;
        this.width=universe[0].length;
        this.universe=universe;
    }
    public Universe getNGeneration(int n){
        if (n<0){
            throw new IllegalArgumentException("Wrong number generation");
        }
        if (n==0) return this;

        for(int counter=0;counter<n;counter++){
            nextGeneration();
        }
        return this;
    }
    public void show(){
        for(int y=0;y<this.height;y++){
            drawLine(y);
            if (y<this.height-1) System.out.println();
        }
    }

    private Cell getCell(int y,int x){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        return universe[y][x];
    }
    private void setCell(int y,int x,Cell cell){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        universe[y][x]=cell;
    }
    private void drawLine(int y){
        for(int x=0;x<this.width;x++){
            getCell(y,x).drawCell();
        }
    }

    private Cell[] getNeighbors(int y,int x){
        Cell[] result= new Cell[8];
        int index=0;
        for(int xTemp=x-1;xTemp<=x+1;xTemp++){
            for(int yTemp=y-1;yTemp<=y+1;yTemp++){
                if (xTemp==x && yTemp==y){
                    continue;
                }
                int xNeighbors=xTemp<0
                        ?width+xTemp:xTemp>=width
                        ?xTemp-width:xTemp;
                int yNeighbors=yTemp<0
                        ?height+yTemp:yTemp>=height
                        ?yTemp-height:yTemp;
                result[index]=getCell(yNeighbors,xNeighbors);
                index++;
            }
        }
        return result;
    }
    private int aliveCount(Cell[] cells){
        int count=0;
        for (Cell cell:cells) {
            if (cell.isAlive()) count++;
        }
        return  count;
    }
    public int aliveAll(){
        int result=0;
        for(Cell[] line:this.universe){
            result+=aliveCount(line);
        }
        return result;
    }
    private Universe nextGeneration(){
        Cell[][] result=new Cell[height][width];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                result[y][x]=new Cell(getCell(y,x).isAlive());
                int aliveCount=aliveCount(getNeighbors(y,x));
                if (aliveCount==3){
                    result[y][x]=new Cell(true);
                }
                else if (aliveCount<2 || aliveCount>3){
                    result[y][x]=new Cell(false);
                }
            }
        }
        this.universe=result;
        return this;
    }
    public static Universe create(int width,long init){
        Cell[][] universe = new Cell[width][width];
        Random rnd = new Random(init);
        for(int y=0;y<width;y++){
            for(int x=0;x<width;x++){
                universe[y][x]=new Cell(rnd.nextBoolean());
            }
        }
        return new Universe(universe);
    }
}