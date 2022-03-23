
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue



    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> before;
    }


    // construct an empty deque
    public Deque(){
        first = null;
        last  = null;
        n = 0;
    }


    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item != null) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.before = null;
            if (oldfirst==null) {last = first;}
            else {
                first.next = oldfirst;
                first.next.before = first ;
            }
            n++;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    // add the item to the back
    public void addLast(Item item){
            if(item != null){
                Node<Item> oldlast = last;
                last = new Node<Item>();
                last.item = item;
                last.next = null;
                if (isEmpty()) first = last;
                else{
                    oldlast.next = last;
                    last.before = oldlast ;
                }
                n++;
            }else {
                throw new IllegalArgumentException();
            }
        }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
        Item item = first.item;
        first = first.next;
        //first.before = null;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
        Item item = last.item;
        last=last.before;
        n--;
        if (n==0) {first = null; last=null;}  // to avoid loitering
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        { return new ListIterator(); }

    }
    private class ListIterator implements Iterator<Item>
    {
        private Node<Item> current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()){ throw new NoSuchElementException();}
            Item item =  current.item;
            current = current.next;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args){
        Deque<String> deck = new Deque<String>();
        int q=0;
        while (!StdIn.isEmpty()) {
            if (q%2==0) {
                deck.addFirst(StdIn.readString());
            }else{deck.addLast(StdIn.readString());}
            q++;
        }
        for (String s : deck)
            StdOut.println(s);
        System.out.println("removing out the first element which is "+ deck.removeFirst());
        System.out.println("removing out the last element which is "+ deck.removeLast());
        for (String s : deck)
            StdOut.println(s);
    }

}