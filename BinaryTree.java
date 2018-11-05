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
    /** size        the number of nodes in the tree */
    private int size;
    
    /**
     * construct a new tree
     */
    public BinaryTree() {
        root = null;
        size = 0;
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
        size++;
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
    
    public int[] getAll() {
        int[] positionsArray = new int[0];
        int index = 0;
        return getAll(positionsArray, index, root);
    }
    
    private int[] getAll(int[] positionsArray, int index, BinaryNode<E> node) {
        if (node != null) {
            positionsArray = getAll(positionsArray, index, node.left);
            // increase the length of the array by 1
            positionsArray = Arrays.copyOf(positionsArray, positionsArray.length + 1);
            // and add the current node's position data at the end
            positionsArray[positionsArray.length - 1] = node.position;
            positionsArray = getAll(positionsArray, index, node.right);
        }
        return positionsArray;
    }
}
