/**
 * A node in the ternary search tree
 *
 * @author Eric Boris 
 * @version 11/4/18
 */
public class TernaryNode<E>{
    /** data                the comparable data stored in the node */
    public E data;
    /** index               the index data stored in the node */
    public int index;
    /** left                the node to the left of this one */
    public TernaryNode<E> left;
    /** same                the next node that stores the same comparable data */
    public UnaryNode same;
    /** right               the node to the right of this one */
    public TernaryNode<E> right;
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   index       the index data to store in this node
     */
    public TernaryNode(E data, int index) {
        this(data, index, null, null, null);
    }
    
    /**
     * create a new node
     * 
     * @param   data        the comparable data to store in this node
     * @param   index       the index data to store in this node
     * @param   left        the node to the left of this one
     * @param   same        the next node that stores the same comparable data
     * @param   right       the node to the right of this one
     */
    public TernaryNode(E data, int index, TernaryNode<E> left, UnaryNode same, TernaryNode<E> right) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index : " + index);
        }
        this.data = data;
        this.index = index;
        this.left = left;
        this.same = same;
        this.right = right;
    }
}
