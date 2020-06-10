package life;

import javax.swing.*;

public class Controler {
    private Model model;
    private View view;
    private int timeOut=200;
    private boolean isPause=false;
    private int sourceGeneration=0;

    private int cashGeneration;
    private boolean[][] cashArray;

    public Controler(Model model,View view){
        this.model=model;
        this.view=view;
    }

    public void replaceModel(){
        this.model=Model.create(model.getWidth());
        goToSourceGeneration();
    }

    public int getTimeOut(){
        return timeOut;
    }
    public void setTimeOut(int timeOut){
        this.timeOut=timeOut;
    }

    public void setSourceGeneration(int n){
        this.sourceGeneration=n;
        goToSourceGeneration();
    }
    public int getSourceGeneration(){return this.sourceGeneration;}

    public void pause(){
        isPause=!isPause;
        goToSourceGeneration();
    }

    public View getView(){return view;}

    public void updateView(){
        if (model.getGeneration()!=cashGeneration){
            cashGeneration=model.getGeneration();
            cashArray = getModelData();
        }
        view.updateView(cashArray,cashGeneration,model.getAliveCount());
    }
    public void goToSourceGeneration(){
        class MyThread extends Thread{
            @Override
            public void run() {
                super.run();
                updateView();
                while(getSourceGeneration()>model.getGeneration() && !isPause){
                    try {
                        Thread.sleep(timeOut);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    model.nextGeneration();
                    updateView();
                }
            }
        }
        MyThread thread=new MyThread();
        thread.start();
    }

    private boolean[][] getModelData(){
        boolean[][] result = new boolean[model.getHeight()][model.getWidth()];
        for (int y=0;y<model.getHeight();y++) {
            for (int x = 0; x < model.getWidth(); x++) {
                result[y][x] = model.getCell(x, y).isAlive();
            }
        }
        return result;
    }
}
