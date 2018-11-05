import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * client code interface for accessing wearables and their indices
 *
 * @author Eric Boris
 * @version 11/4/2018
 */
public class Wearables {
    /** header          the header of the text file */
    private String header;
    /** wearables       an array of wearable objects */
    private Wearable[] wearables;
    /** rankData        an ordered array of wearables by rank */
    private BinaryTree<Integer> rankings;
    
    public Wearables() {
        rankings = new BinaryTree<Integer>();
    }
    
    /**
     * fill the wearables array from the provided file
     * 
     * @param   fileName        the name of the file to access
     */
    public void fill(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null");
        }
        File file = new File(fileName);
        BufferedReader br = null;
        ArrayList<Wearable> wearablesAL = new ArrayList<Wearable>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            // skip the first line of the file
            br.readLine();
            header = br.readLine();
            
            // read each line out of the file
            int index = 0;
            while ((line = br.readLine()) != null) {
                wearablesAL.add(new Wearable(line, header));
                String[] splitLine = line.split("@");
                rankings.add(Integer.parseInt(splitLine[0]), index++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.wearables = wearablesAL.toArray(new Wearable[wearablesAL.size()]);
    }
    
    /**
     * get the wearable at the provided index
     * 
     * @param   index       the index of the provided wearable to return
     * @return              a wearable at the provided index
     */
    public Wearable get(int index) {
        if (index < 0 || index > wearables.length) {
            throw new IndexOutOfBoundsException("index : " + index);
        }
        return wearables[index];
    }
    
    public int[] getRankingData() {
        return rankings.getAll();
    }
}
