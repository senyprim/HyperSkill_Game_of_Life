package life;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

public class GameOfLife extends JFrame{
    Universe universe;
    JLabel generationLabel;
    JLabel aliveLabel;
    JPanel centerPanel;
    public GameOfLife() throws InterruptedException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        JPanel topPanel=new JPanel();

        generationLabel=new JLabel("Generation #");
        generationLabel.setName("GenerationLabel");
        aliveLabel=new JLabel("Alive: ");
        aliveLabel.setName("AliveLabel");
        centerPanel=new JPanel(){
            public void paintComponent(Graphics g){paintUniverse(g);}
        };
        centerPanel.setSize(300,300);

        topPanel.add(generationLabel);
        topPanel.add(aliveLabel);
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(centerPanel,BorderLayout.CENTER);

        setVisible(true);

        this.universe = Universe.create(30,30);

        onCreate();
    }
    public void paintUniverse(Graphics g){
        int width=Math.min(getWidth(),getHeight());
        int length=width/universe.getWidth();

        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        for (int y=0;y<universe.getWidth();y++){
            for(int x=0;x<universe.getWidth();x++){

                if (universe.getCell(y,x).isAlive()) {
                    g.setColor(Color.BLACK);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * length, y * length, length,  length);
                g.setColor(Color.BLACK);
                g.drawRect(x*length,y*length,length,length);
            }
        }
    }
    public void onCreate() throws InterruptedException {
        for(int i=1 ;i<20;i++){
            universe.nextGeneration();
            generationLabel.setText(String.format("Generation #%d",this.universe.getGeneration()));
            aliveLabel.setText(String.format("Alive: %d",this.universe.aliveAll()));
            centerPanel.repaint();
            Thread.sleep(1000);
        }
    }




}
