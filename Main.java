import java.util.Arrays;

/**
 * demonstrate the functionality of wearables
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class Main {
    /**
     * use wearables class
     * 
     * @param   args        the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "WearablesArray.txt";
        Wearables w = new Wearables();
        w.fill(fileName);
        
        // -- Display all the data -- //
        
        System.out.println("First 3 wearables in array text");
        System.out.println(w.get(0).toString());
        System.out.println(w.get(1).toString());
        System.out.println(w.get(2).toString());
        System.out.println("\n");
        
        int[] r = w.getRankingData();
        System.out.println("Top 3 wearables by Ranking");
        System.out.println(w.get(r[0]).toString());
        System.out.println(w.get(r[1]).toString());
        System.out.println(w.get(r[2]).toString());
        System.out.println("\n");
        w.toCsv("rankings", r);
        
        int[] p = w.getPriceData();
        System.out.println("Top 3 most expensive wearables");
        System.out.println(w.get(p[p.length - 1]).toString());
        System.out.println(w.get(p[p.length - 2]).toString());
        System.out.println(w.get(p[p.length - 3]).toString());
        System.out.println("\n");
        w.toCsv("prices", p);
        
        int[] c = w.getCoNameData();
        System.out.println("Top 3 wearables by company name");
        System.out.println(w.get(c[0]).toString());
        System.out.println(w.get(c[1]).toString());
        System.out.println(w.get(c[2]).toString());
        System.out.println("\n");
        w.toCsv("company names", c);
    }
}
