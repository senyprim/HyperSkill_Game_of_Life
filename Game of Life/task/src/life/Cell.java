package life;

public class Cell {
    private boolean isAlive;
    public Cell(boolean isAlive){
        this.isAlive=isAlive;
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public void drawCell(){
        System.out.print(isAlive?"O":" ");
    }
}
