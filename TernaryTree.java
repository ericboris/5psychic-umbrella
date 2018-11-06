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
    
    /**
     * construct a new tree
     */
    public TernaryTree() {
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
    private int[] getAll(int[] indicesArray, TernaryNode<E> node) {
        if (node != null) {
            // down the left chain
            indicesArray = getAll(indicesArray, node.left);
            // down the same chain
            indicesArray = getAllSame(indicesArray, node.same);
            // work on this node and add it to the array
            // increase the length of the array by 1
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            // and add the current node's index data at the end
            indicesArray[indicesArray.length - 1] = node.index;
            // down the right chain
            indicesArray = getAll(indicesArray, node.right);
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
    private int[] getAllSame(int[] indicesArray, UnaryNode node) {
        if (node != null) {
            indicesArray = getAllSame(indicesArray, node.next);
            indicesArray = Arrays.copyOf(indicesArray, indicesArray.length + 1);
            indicesArray[indicesArray.length - 1] = node.index;
        }
        return indicesArray;
    }
    
    /**
     * balance the tree's nodes
     */
    public void balance() {
        // get an array of nodes ordered by data
        TernaryNode[] nodeArray = new TernaryNode[0];
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
    private TernaryNode<E>[] getOrderedNodes(TernaryNode<E>[] nodeArray, TernaryNode<E> node) {
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
    private TernaryNode<E> arrayToTree(TernaryNode[] nodeArray, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start + end) / 2;
        TernaryNode<E> node = nodeArray[mid];

        node.left = arrayToTree(nodeArray, start, mid - 1);
        node.right = arrayToTree(nodeArray, mid + 1, end);
        return node;
    }
}
