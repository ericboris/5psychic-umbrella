import java.util.Arrays;

/**
 * A generic-type ternary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class TernaryTree<E extends Comparable<E>> {
    /** root                the root of the ternary tree */
    private TernaryNode<E> root;
    /** size                the size of the tree; */
    private int size;
    
    /**
     * construct a new tree
     */
    public TernaryTree() {
        root = null;
    }
    
    /**
     * set the size of the tree
     * 
     * @param   size        the number of nodes in the tree
     */
    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be less than zero");
        }
        this.size = size;
    }
    
    /** 
     * add a new node 
     * 
     * @param   data        the data for the node
     * @param   index       the index data for the node
     */
    public void add(E data, int index) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index : " + index);
        }
        root = add(data, index, root);
    }
    
    /**
     * add a new node
     * 
     * @param   data        the data for the node
     * @param   index       the index data for the node
     * @param   node        the root node to compare against
     * @return              return a new node
     */
    private TernaryNode<E> add(E data, int index, TernaryNode<E> node) {
        if (node == null) {
            node = new TernaryNode<E>(data, index);
        } else if (node.data.compareTo(data) > 0) {
            node.left = add(data, index, node.left);
        } else if (node.data.compareTo(data) == 0) {
            node.same = addUnary(index, node.same);
        } else {
            node.right = add(data, index, node.right);
        }
        return node;
    }
    
    /**
     * add a unary node
     * 
     * @param   index       the index data for the node
     * @param   node        the root node to compare against
     * @return              a new node
     */
    private UnaryNode addUnary(int index, UnaryNode node) {
        if (node == null) {
            node = new UnaryNode(index);
        } else {
            node.next = addUnary(index, node.next);
        }
        return node;
    }
    
    /**
     * get all the nodes of the tree in order
     * 
     * @return               an ordered array of all of the nodes' indexs
     */
    public int[] getIndices() {
        int[] indicesArray = new int[0];
        return getIndices(indicesArray, root);
    }
    
    /**
     * search through each node and return an ordered array of elements
     * 
     * @param   indicesArray        an ordered array of each node's index data
     * @param   node                the current node in the tree
     * @return                      an ordered array of each node's index data   
     */
    private int[] getIndices(int[] indicesArray, TernaryNode<E> node) {
        if (node != null) {
            // down the left chain
            indicesArray = getIndices(indicesArray, node.left);
            // down the same chain
            indicesArray = getIndicesSame(indicesArray, node.same);
            // work on this node and add it to the array
            // increase the length of the array by 1
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            // and add the current node's index data at the end
            indicesArray[indicesArray.length - 1] = node.index;
            // down the right chain
            indicesArray = getIndices(indicesArray, node.right);
        }
        return indicesArray;
    }
    
    /**
     * get the index data of each unary node
     * 
     * @param   indicesArray        an ordered array of each node's index data
     * @param   node                the current node in the tree
     * @return                      an ordered array of each node's index data   
     */
    private int[] getIndicesSame(int[] indicesArray, UnaryNode node) {
        if (node != null) {
            indicesArray = getIndicesSame(indicesArray, node.next);
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            indicesArray[indicesArray.length - 1] = node.index;
        }
        return indicesArray;
    }
}
