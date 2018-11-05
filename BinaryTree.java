import java.util.Arrays;

/**
 * A generic-type binary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class BinaryTree<E extends Comparable<E>> {
    /** root        the root of the binary tree */
    private BinaryNode<E> root;
    
    /**
     * construct a new tree
     */
    public BinaryTree() {
        root = null;
    }
    
    /** 
     * add a new node 
     * 
     * @param   data        the data for the node
     * @param   position    the position data for the node
     */
    public void add(E data, int position) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        root = add(data, position, root);
    }
    
    /**
     * add a new node
     * 
     * @param   data        the data for the node
     * @param   position    the position data for the node
     * @param   node        the root node to compare against
     * @return              return a new node
     */
    private BinaryNode<E> add(E data, int position, BinaryNode<E> node) {
        if (node == null) {
            node = new BinaryNode(data, position);
        } else if (node.data.compareTo(data) >= 0) {
            node.left = add(data, position, node.left);
        } else {
            node.right = add(data, position, node.right);
        }
        return node;
    }
    
    /**
     * get all the nodes of the tree in order
     * 
     * return               an ordered array of all of the nodes' positions
     */
    public int[] getAll() {
        int[] positionsArray = new int[0];
        return getAll(positionsArray, root);
    }
    
    /**
     * search through each node and return an ordered array of elements
     * 
     * @param   positionsArray      an ordered array of each node's position data
     * @param   node                the current node in the tree
     * @return                      an ordered array of each node's position data   
     */
    private int[] getAll(int[] positionsArray, BinaryNode<E> node) {
        if (node != null) {
            positionsArray = getAll(positionsArray, node.left);
            // increase the length of the array by 1
            positionsArray = Arrays.copyOf(positionsArray, positionsArray.length + 1);
            // and add the current node's position data at the end
            positionsArray[positionsArray.length - 1] = node.position;
            positionsArray = getAll(positionsArray, node.right);
        }
        return positionsArray;
    }
}
