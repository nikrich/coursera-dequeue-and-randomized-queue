
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jannik.Richter
 */
public class Permutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
       
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        
        int num = Integer.parseInt(args[0]);
        
        for (int i = 0; i < num; i ++) {
            StdOut.println(rq.dequeue());
        }
    }
    
}
