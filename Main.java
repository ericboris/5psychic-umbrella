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
        
        System.out.println(w.get(0).toString());
        System.out.println(w.get(10).toString());
    }
}
