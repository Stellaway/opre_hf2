import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SecondChance sc = new SecondChance(getInput());
        sc.run();

    }

    public static ArrayList<Integer> getInput(){
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] parts = line.split(",");
        ArrayList<Integer> nParts = new ArrayList<>();
        for (String n : parts) {
            nParts.add(Math.abs(Integer.parseInt(n)));
        }

        return nParts;
    }
}