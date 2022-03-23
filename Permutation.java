/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Deque<String> deck = new Deque<String>();
        int q=0;
        while (!StdIn.isEmpty()) {
            if (q%2==0) {
                deck.addFirst(StdIn.readString());
            }else{deck.addLast(StdIn.readString());}
            q++;
        }
        for (int i=0; i<k; i++){
            int rnd = StdRandom.uniform(1000);
            if (rnd%2==0) {
                System.out.println(deck.removeFirst());
            }else{System.out.println(deck.removeLast());}
        }
    }
}
