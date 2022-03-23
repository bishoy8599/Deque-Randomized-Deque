import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N ;
    private int head ;
    private int tail ;

    // construct an empty randomized queue
    public RandomizedQueue(){
        s = (Item[])new Object[2];
        head=tail=0;
        N=0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){return N==0;}

    // return the number of items on the randomized queue
    public int size(){return N;}

    private void resize(int capacity)
    {
       Item copy[] = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[(head+i)];
        s = copy;
        head=0;
        tail=N;
    }
    // add the item
    public void enqueue(Item item) {
        if (item != null) {
            if (N == s.length) resize((2 * s.length));
            s[tail] = item;
            //if (isEmpty()) {
            //    head = tail;
            //}
            tail++;
            N++;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        //int rnd = StdRandom.uniform(2);
        Item out;
        //if (rnd ==1){
            out = s[tail-1];
            s[tail-1]=null ;
            tail--;
       // }else{
        //    out = s[head];
        //    s[head]=null;
        //    head++;
        //}
        N--;
        if (N > 0 && N == s.length/4) resize(s.length/2);
    return out ;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(head==tail){throw new NoSuchElementException();}
        int rnd = StdRandom.uniform(N);
        return s[head+rnd];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new ListIterator(); }


    private class ListIterator implements Iterator<Item>
    {
        private int current = head;
        public boolean hasNext() { return current < tail; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (!hasNext()){ throw new NoSuchElementException();}
            Item item =  s[current];
            current++;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }


}