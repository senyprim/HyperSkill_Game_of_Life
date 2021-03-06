package life;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
    JLabel generationLabel;
    JLabel aliveLabel;
    JPanel centerPanel;
    boolean[][] cashData;

    public View()  {
        generationLabel=new JLabel("Generation #");
        generationLabel.setName("GenerationLabel");
        aliveLabel=new JLabel("Alive: ");
        aliveLabel.setName("AliveLabel");

        centerPanel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (cashData!=null) {
                    paintUniverse(g, getWidth(), getHeight());
                }
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300,300);
            }
        };

    }

    public JLabel getGenerationLabel(){
        return generationLabel;
    }
    public JLabel getAliveLabel(){
        return aliveLabel;
    }
    public JPanel getDataView(){return centerPanel;}

    public void paintUniverse(Graphics g,int width,int height){
        int dx=width/cashData.length;
        int dy=height/cashData[0].length;
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        for (int y=0;y<cashData.length;y++){
            for(int x=0;x<cashData[0].length;x++){
                if (cashData[y][x]) {
                    g.setColor(Color.BLACK);
                }
                else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * dx, y * dy, dx,  dy);
                g.setColor(Color.BLACK);
                g.drawRect(x * dx, y * dy, dx,  dy);
            }
        }
    }

    public void updateView(boolean[][] data,int nGeneration,int countAlive){
        generationLabel.setText(String.format("Generation #%d",nGeneration));
        aliveLabel.setText(String.format("Alive: %d",countAlive));
        this.cashData=data;
        centerPanel.repaint();
    }
}
