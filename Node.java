// CLASS: NODE
//
// Author: Sijin Lee, 7822352
//
// REMARKS: Implements a node class to use the creating linked list
//
//-----------------------------------------

public class Node
{
	private Object value;
	private Node next;

    //------------------------------------------------------
    // Node()
    //
    // PURPOSE:  constructor for Node Class
    // PARAMETERS:
    //    None
    // Returns: empty Node
    public Node()
    {
        this.value = null;
        this.next = null;
    }

    //------------------------------------------------------
    // Node(Object value)
    //
    // PURPOSE:  constructor for Node Class
    // PARAMETERS:
    //    Object
    // Returns: Node with value and set next pointer to null

    public Node(Object value)
    {
        this.value = value;
        this.next = null;
    }

    //------------------------------------------------------
    // add(Object value)
    //
    // PURPOSE:  inserts a Object into the List
    // PARAMETERS:
    //   Object
    // Returns: None

    public void add(Object value)
    {
        if(this.value == null)
        {
            this.value = value;
            this.next = null;
            return;
        }

        Node lastNode = this;

        while(lastNode.getNext() != null)
        lastNode = lastNode.getNext();

        Node temp = new Node(value);
        lastNode.setNext(temp);

    }

    //------------------------------------------------------
    // add(Object value)
    //
    // PURPOSE:  to check if the value of Object is empty
    // PARAMETERS:
    //   Object
    // Returns: Boolean

    public boolean isEmpty()
    {
        return (this.value == null);
    }

    //------------------------------------------------------
    // remove(Object other)
    //
    // PURPOSE:  removes a Object from the List
    // PARAMETERS:
    //   Object
    // Returns: None
    public boolean remove(Object other)
    {
        if(isEmpty())
        return false;

        Node temp = this;
        Node prev = null;

        while(temp != null && !temp.getValue().equals(other))
        {
            prev = temp;
            temp = temp.getNext();
        }

        if(temp == null)
        return false;

        if(temp == this)
        {
        	if(next == null)
            value = null;
            else
            {
                value = next.getValue();
                next = next.getNext();
            }
        }
        else
        {
            prev.setNext(temp.getNext());
        }

        return true;

    }

    //------------------------------------------------------
    // getValue()
    //
    // PURPOSE:  get Node value of current Object
    // PARAMETERS:
    //   None
    // Returns: Object

    public Object getValue()
    {
        return value;
    }

    //------------------------------------------------------
    // getNext()
    //
    // PURPOSE:  get next pointer of current Object
    // PARAMETERS:
    //   None
    // Returns: Node
    public Node getNext()
    {
        return next;
    }

    //------------------------------------------------------
    // getNext()
    //
    // PURPOSE:  set next pointer of current Object
    // PARAMETERS:
    //   Node
    // Returns: None
    public void setNext(Node next)
    {
        this.next = next;
    }
}
