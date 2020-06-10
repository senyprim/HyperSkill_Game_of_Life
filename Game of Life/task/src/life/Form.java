package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {
    Controler controler;

    JToggleButton pauseButton;
    JButton repeatButton;
    JButton nextButton;
    JSlider speedSlide;

    public Form(Controler controler){
        super("Game of Life");
        setSize(600, 400);
        setLocationRelativeTo(null);
        this.controler=controler;

        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setLayout(new BorderLayout());
        JPanel controlPanel=new JPanel();
        controlPanel.setSize(200,400);

        controlPanel.setLayout(new FlowLayout());
        controlPanel.setPreferredSize(new Dimension(200,400));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(200,50);
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        controlPanel.add(buttonPanel,0);

        nextButton=new JButton("");

        pauseButton=new JToggleButton("");
        pauseButton.addActionListener(event->controler.pause());
        pauseButton.setName("PlayToggleButton");

        repeatButton=new JButton("");
        repeatButton.addActionListener(event->controler.replaceModel());

        repeatButton.setName("ResetButton");

        speedSlide=new JSlider(100,1000,controler.getTimeOut());
        speedSlide.addChangeListener(changeEvent->{
                int val=((JSlider)changeEvent.getSource()).getValue();
                controler.setTimeOut(val);
            });

        controlPanel.add(nextButton,0);
        controlPanel.add(pauseButton,1);
        controlPanel.add(repeatButton,2);
        controlPanel.add(speedSlide);


        controlPanel.add(controler.getView().generationLabel,3);
        controlPanel.add(controler.getView().aliveLabel,4);
        add(controlPanel,BorderLayout.WEST);

        add(controler.getView().getDataView(),BorderLayout.EAST);

        setVisible(true);
    }

}
