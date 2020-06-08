package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len=scanner.nextInt();
        int init=scanner.nextInt();
        int ngeneration=scanner.nextInt();
        Universe.create(len,init).getNGeneration(ngeneration).show();

    }
}
