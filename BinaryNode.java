/**
 * A node in a binary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class BinaryNode<E> {
    /** data                the comparable data stored in the node */
    public E data;
    /** index               the index data stored in the node */
    public int index;
    /** left                the node to the left of this one */
    public BinaryNode<E> left;
    /** right               the node to the right of this one */
    public BinaryNode<E> right;
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   index       the index data to store in this node
     */
    public BinaryNode(E data, int index) {
        this(data, index, null, null);
    }
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   index       the index data to store in this node
     * @param   left        the node to the left of this one
     * @param   right       the node to the right of this one
     */
    public BinaryNode(E data, int index, BinaryNode<E> left, BinaryNode<E> right) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.data = data;
        this.index = index;
        this.left = left;
        this.right = right;
    } 
}
