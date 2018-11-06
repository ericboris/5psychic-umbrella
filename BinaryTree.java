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
    public int[] getAll() {
        int[] indicesArray = new int[0];
        return getAll(indicesArray, root);
    }
    
    /**
     * search through each node and return an ordered array of elements
     * 
     * @param   indicesArray        an ordered array of each node's index data
     * @param   node                the current node in the tree
     * @return                      an ordered array of each node's index data   
     */
    private int[] getAll(int[] indicesArray, BinaryNode<E> node) {
        if (node != null) {
            indicesArray = getAll(indicesArray, node.left);
            // increase the length of the array by 1
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            // and add the current node's index data at the end
            indicesArray[indicesArray.length - 1] = node.index;
            indicesArray = getAll(indicesArray, node.right);
        }
        return indicesArray;
    }
    
    /**
     * balance the tree's nodes
     */
    public void balance() {
        // get an array of nodes ordered by data
        BinaryNode[] nodeArray = new BinaryNode[0];
        nodeArray = getOrderedNodes(nodeArray, root);
        // rebuild a balanced tree from the array of nodes
        this.root = arrayToTree(nodeArray, 0, nodeArray.length - 1);
    }
    
    /**
     * get an ordered array of all the nodes in the tree 
     * 
     * @param   nodeArray       an array to add nodes to
     * @param   node            the current root node
     * @return                  an ordered array of nodes
     */
    private BinaryNode<E>[] getOrderedNodes(BinaryNode<E>[] nodeArray, BinaryNode<E> node) {
        if (node != null) {
            nodeArray = getOrderedNodes(nodeArray, node.left);
            nodeArray = Arrays.copyOf(nodeArray, nodeArray.length + 1);
            nodeArray[nodeArray.length - 1] = node;
            nodeArray = getOrderedNodes(nodeArray, node.right);
        }
        return nodeArray;
    }
    
    /**
     * return the root node of a balanced binary tree from an ordered array of nodes
     * 
     * @param   nodeArray       the ordered array
     * @param   start           the current start index of the array
     * @param   end             the currend end index of the array
     * @return                  the root node of a balance binary tree
     */
    private BinaryNode<E> arrayToTree(BinaryNode[] nodeArray, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start + end) / 2;
        BinaryNode<E> node = nodeArray[mid];

        node.left = arrayToTree(nodeArray, start, mid - 1);
        node.right = arrayToTree(nodeArray, mid + 1, end);
        return node;
    }
}
