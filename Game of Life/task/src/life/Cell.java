package life;

import java.awt.*;

public class Cell {
    private boolean isAlive;
    public Cell(boolean isAlive){
        this.isAlive=isAlive;
    }
    public boolean isAlive(){
        return this.isAlive;
    }

    public String toString(){
        return isAlive?"O":" ";
    }
}
