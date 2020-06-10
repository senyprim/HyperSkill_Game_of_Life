package life;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args)  {
        Model model=Model.create(30);
        View view=new View();
        Controler controler = new Controler(model,view);
        Form form=new Form(controler);
        controler.setSourceGeneration(100);
    }
}
