package life;

import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int len=scanner.nextInt();
        int ngeneration=20;
        Universe universe=Universe.create(len,(new Date()).getTime());
        for(int i=1;i<ngeneration+1;i++){
            System.out.println(String.format("Generation #%d",i));
            System.out.println(String.format("Alive: #%d",universe.aliveAll()));
            universe.show();
            universe.getNGeneration(1);
            System.out.println();
        }
    }
}
