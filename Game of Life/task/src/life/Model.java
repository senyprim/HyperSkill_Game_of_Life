package life;

import java.util.Date;
import java.util.Random;

public class Model {
    private int width;
    private int height;
    private int nGeneration;
    private int aliveCount;
    private Cell[][] universe;

    public Model(Cell[][] universe){
        this.height=universe.length;
        this.width=universe[0].length;
        this.universe=universe;
        this.nGeneration=1;
        this.aliveCount=getAliveCount(universe);
    }

    public static Model create(int width, long init){
        Cell[][] universe = new Cell[width][width];
        Random rnd = new Random(init);
        for(int y=0;y<width;y++){
            for(int x=0;x<width;x++){
                universe[y][x]=new Cell(rnd.nextBoolean());
            }
        }
        return new Model(universe);
    }
    public static Model create(int width){
        return create(width,(new Date()).getTime());
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){return height;}

    public int getGeneration() {return nGeneration;}

    public Cell getCell(int x,int y){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        return universe[y][x];
    }

    public int getAliveCount(){return aliveCount;}

    public Model nextGeneration(){
        Cell[][] result=new Cell[height][width];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                result[y][x]=new Cell(getCell(x,y).isAlive());
                int aliveCount= getAliveCount(getNeighbors(y,x));
                if (aliveCount==3){
                    result[y][x]=new Cell(true);
                }
                else if (aliveCount<2 || aliveCount>3){
                    result[y][x]=new Cell(false);
                }
            }
        }
        this.universe=result;
        this.nGeneration++;
        this.aliveCount= getAliveCount(universe);
        return this;
    }

    private void setCell(int y,int x,Cell cell){
        if (x<0 || y<0 || x>=width || y>=height){
            throw  new IllegalArgumentException("Error:Coordinates can not be negative or more sides");
        }
        universe[y][x]=cell;
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
                result[index]=getCell(xNeighbors,yNeighbors);
                index++;
            }
        }
        return result;
    }

    static private int getAliveCount(Cell[] cells){
        int count=0;
        for (Cell cell:cells) {
            if (cell.isAlive()) count++;
        }
        return  count;
    }

    static private int getAliveCount(Cell[][] cells){
        int count=0;
        for(Cell[] line:cells )
            count+= getAliveCount(line);
        return count;
    }



}