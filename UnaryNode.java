/**
 * A node in the ternary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class UnaryNode {
    /** index                   the index data stored in the node */
    public int index;
    /** next                    the next node with the same table data */
    public UnaryNode next;
    
    /**
     * create a new node
     * 
     * @param   index           the index data stored in the node
     */
    public UnaryNode(int index) {
        this(index, null);
    }
    
    /**
     * create a new node
     * 
     * @param   index           the index data stored in the node
     * @param   next            the next node with the same table data
     */
    public UnaryNode(int index, UnaryNode next) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index : " + index);
        }
        this.index = index;
        this.next = next;
    }
}
