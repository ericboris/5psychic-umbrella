/**
 * A node in the ternary search tree
 *
 * @author Eric Boris
 * @version 11/4/18
 */
public class UnaryNode {
    /** position        the position data stored in the node */
    public int position;
    /** next            the next node with the same table data */
    public UnaryNode next;
    
    /**
     * create a new node
     * 
     * @param   position        the position data stored in the node
     */
    public UnaryNode(int position) {
        this(position, null);
    }
    
    /**
     * create a new node
     * 
     * @param   position        the position data stored in the node
     * @param   next            the next node with the same table data
     */
    public UnaryNode(int position, UnaryNode next) {
        this.position = position;
        this.next = next;
    }
}
