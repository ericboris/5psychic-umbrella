import java.util.Arrays;

/**
 * demonstrate the functionality of wearables
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "WearablesArray.txt";
        Wearables w = new Wearables();
        w.fill(fileName);
        
        System.out.println("First three in array text");
        System.out.println(w.get(0).toString());
        System.out.println(w.get(1).toString());
        System.out.println(w.get(2).toString());
        System.out.println("\n");
        
        int[] r = w.getRankingData();
        System.out.println("Top 3 by Ranking");
        System.out.println(w.get(r[0]).toString());
        System.out.println(w.get(r[1]).toString());
        System.out.println(w.get(r[2]).toString());
    }
}
