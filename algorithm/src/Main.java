import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        System.out.println("insert file location");
        Input input =new Input();
        Scanner sc;
        sc = new Scanner(System.in);
        String address = sc.next();
        System.out.println("choose the number of an algorithm 1.basic greedy algorithm 2.Welsh–Powell");
        sc = new Scanner(System.in);
        int algorithm = sc.nextInt();
        input.csvReader(address);
        input.toAdjacencyList(algorithm);


    }
}
