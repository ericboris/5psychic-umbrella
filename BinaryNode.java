/**
 * A node in a binary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class BinaryNode<E> {
    /** data            the comparable data stored in the node */
    public E data;
    /** position        the position data stored in the node */
    public int position;
    /** left            the node to the left of this one */
    public BinaryNode<E> left;
    /** right           the node to the right of this one */
    public BinaryNode<E> right;
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   position    the position data to store in this node
     */
    public BinaryNode(E data, int position) {
        this(data, position, null, null);
    }
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   position    the position data to store in this node
     * @param   left        the node to the left of this one
     * @param   right       the node to the right of this one
     */
    public BinaryNode(E data, int position, BinaryNode<E> left, BinaryNode<E> right) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.data = data;
        this.position = position;
        this.left = left;
        this.right = right;
    } 
}
