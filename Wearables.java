import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;

/**
 * client interface for accessing wearables and their indices
 *
 * @author Eric Boris
 * @version 11/4/2018
 */
public class Wearables {
    /** header          the header of the text file */
    private String[] header;
    /** wearables       an array of wearable objects */
    private Wearable[] wearables;
    /** rankTree        an ordered array of wearables by rank */
    private BinaryTree<Integer> rankTree;
    /** priceTree       an ordered array of wearables by price */
    private TernaryTree<Double> priceTree;
    /** coNameTree      and ordered array of wearables by company name */
    private TernaryTree<String> coNameTree;

    /** SPLIT_CHAR      the split delimiter used in the file */
    private static final String SPLIT_CHAR = "@";

    /**
     * create a new wearables object
     */
    public Wearables() {
        rankTree = new BinaryTree<Integer>();
        priceTree = new TernaryTree<Double>();
        coNameTree = new TernaryTree<String>();
    }

    /**
     * fill the wearables array from the provided file
     * 
     * @param   fileName        the name of the file to access
     * @return                  false if file read fails, true otherwise
     */
    public boolean fill(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName must not be null");
        }
        File file = new File(fileName);
        BufferedReader br = null;
        ArrayList<Wearable> wearablesAL = new ArrayList<Wearable>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            // skip the first line of the file but parse and store the second
            br.readLine();
            header = br.readLine().split(SPLIT_CHAR);

            // read each line out of the file
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(SPLIT_CHAR);
                wearablesAL.add(new Wearable(splitLine, header));
                rankTree.add(Integer.parseInt(splitLine[0]), index);
                priceTree.add(Double.parseDouble(splitLine[2]), index);
                coNameTree.add(splitLine[5], index);
                index++;
            }
        } catch (IOException e) {
            return false;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                return false;
            }
        }
        this.wearables = wearablesAL.toArray(new Wearable[wearablesAL.size()]);
        return true;
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

    /**
     * get an ordered array of the ranking index data
     * 
     * @return              an ordered array of the ranking index data
     */
    public int[] getRankingData() {
        return rankTree.getIndices();
    }

    /**
     * get an ordered array of the price index data
     * 
     * @return              an ordered array of the price index data
     */
    public int[] getPriceData() {
        return priceTree.getIndices();
    }

    /**
     * get an ordered array of the company name index data
     * 
     * @return              an ordered array of the company name index data
     */
    public int[] getCoNameData() {
        return coNameTree.getIndices();
    }

    /**
     * save a named csv of wearables to drive based on provided index ranking
     * 
     * @param   fileName    the desired file name
     * @param   indices     the index ranking to sort wearables by
     * @return              false if file creation fails, true otherwise
     */
    public boolean toCsv(String fileName, int[] indices) {
        if (fileName == null || indices == null) {
            throw new IllegalArgumentException("argument must not be null");
        }

        StringBuilder sb = new StringBuilder();
        for (String h : header) {
            sb.append(h + ",");
        }
        sb.append("\n");

        for (int index : indices) {
            Wearable w = get(index);
            sb.append(w.getRank() + ",");
            sb.append("\"" + w.getName() + "\",");
            sb.append(w.getPrice() + ",");
            sb.append("\"" + w.getBodyLocation() + "\",");
            sb.append("\"" + w.getCategory() + "\",");
            sb.append("\"" + w.getCoName() + "\",");
            sb.append("\"" + w.getCoUrl() + "\",");
            sb.append("\"" + w.getCoLocation() + "\",");
            sb.append("\"" + w.getCoCity() + "\",");
            sb.append("\"" + w.getCoUsState() + "\",");
            sb.append("\"" + w.getCoCountry() + "\",");
            sb.append("\n");
        }

        String csvText = sb.toString();

        try {
            // delete file if it already exists to prevent appending
            File file = new File(fileName + ".csv");
            if (file.exists()) {
                file.delete();
            }

            // create and save new csv
            FileWriter fw = new FileWriter(fileName + ".csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(csvText);
            bw.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Balance the rank tree
     * 
     * @return              true if balancing succeeds, false otherwise
     */
    public boolean balanceRankTree() {
        if (rankTree != null) {
            rankTree.balance();
            return true;
        }
        return false;
    }
}
