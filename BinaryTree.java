import java.util.Arrays;

/**
 * A generic-type binary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class BinaryTree<E extends Comparable<E>> {
    /** root                the root of the binary tree */
    private BinaryNode<E> root;
    /** size                the size of the tree; */
    private int size;

    /**
     * construct a new tree
     */
    public BinaryTree() {
        size = 0;
        root = null;
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
        size++;
    }

    /**
     * add a new node
     * 
     * @param   data        the data for the node
     * @param   index       the index data for the node
     * @param   node        the root node to compare against
     * @return              return a new node
     */
    private BinaryNode<E> add(E data, int index, BinaryNode<E> node) {
        if (node == null) {
            node = new BinaryNode<E>(data, index);
        } else if (node.data.compareTo(data) >= 0) {
            node.left = add(data, index, node.left);
        } else {
            node.right = add(data, index, node.right);
        }
        return node;
    }

    /**
     * get all the nodes of the tree in order
     * 
     * @return              an ordered array of all of the nodes' indices
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
    private int[] getIndices(int[] indicesArray, BinaryNode<E> node) {
        if (node != null) {
            indicesArray = getIndices(indicesArray, node.left);
            // increase the length of the array by 1
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            // and add the current node's index data at the end
            indicesArray[indicesArray.length - 1] = node.index;
            indicesArray = getIndices(indicesArray, node.right);
        }
        return indicesArray;
    }
}
