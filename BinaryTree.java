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

    /**
     * balance the tree's nodes
     */
    public void balance() {
        // get an array of nodes ordered by data
        //BinaryNode[] nodeArray = new BinaryNode[0];
        int[] indicesArray = getIndices(new int[0], root);
        int[] dataArray = getData(new int[0], root);
        //nodeArray = getOrderedNodes(nodeArray, root);
        // rebuild a balanced tree from the array of nodes
        this.root = arrayToTree(dataArray, indicesArray, 0, dataArray.length - 1);
    }

    /**
     * search through each node and return an ordered array of elements
     * 
     * @param   indicesArray        an ordered array of each node's index data
     * @param   node                the current node in the tree
     * @return                      an ordered array of each node's index data   
     */
    private int[] getData(int[] dataArray, BinaryNode<E> node) {
        if (node != null) {
            dataArray = getData(dataArray, node.left);
            // increase the length of the array by 1
            dataArray = Arrays.copyOf(dataArray, dataArray.length + 1);
            // and add the current node's index data at the end
            dataArray[dataArray.length - 1] = (Integer) node.data;
            dataArray = getData(dataArray, node.right);
        }
        return dataArray;
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
    private BinaryNode<E> arrayToTree(int[] dataArray, int[] indicesArray, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BinaryNode<E> node = new BinaryNode(dataArray[mid], indicesArray[mid]);

        node.left = arrayToTree(dataArray, indicesArray, start, mid - 1);
        node.right = arrayToTree(dataArray, indicesArray, mid + 1, end);
        return node;
    }
}
