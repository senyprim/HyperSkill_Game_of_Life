package life;

public class Controler {
    private Model model;
    private View view;

    private int cashGeneration;
    private boolean[][] cashArray;

    public Controler(Model model,View view){
        this.model=model;
        this.view=view;
    }

    public void updateView(){
        if (model.getGeneration()!=cashGeneration){
            cashGeneration=model.getGeneration();
            cashArray = getModelData();
        }
        view.updateView(cashArray,cashGeneration,model.getAliveCount());
    }
    public void goToGeneration(int n){
        updateView();
        while(n>model.getGeneration()){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            model.nextGeneration();
            updateView();
        }
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
