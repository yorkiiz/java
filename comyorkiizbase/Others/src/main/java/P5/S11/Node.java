package P5.S11;

/**
 * @auther:
 * @date:
 * @describtion:
 **/

public class Node<T> {

    T item;
    Node<T> prev;
    Node<T> next;

    public Node(Node<T> prev,Node<T> item,Node<T> next){
        this.prev=prev;
        this.item= (T) item;
        this.next=next;
    }

}
