
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jannik.Richter
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] a;
    private int n;
   
    /**
     * @description construct an empty randomized queue
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }  
    
    /**
     * @description is the randomized queue empty?
     */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /**
     * @description return the number of items on the randomized queue
     * @return Integer
     */
    public int size() {
        return n;
    }
    
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }
    
    /**
     * @description add the item    
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if(n == a.length) {
            resize(2 * a.length);
        }
        
        a[n++] = item;
    }
    
    /**
     * @description remove and return a random item
     * @return Generic Item
     */
    public Item dequeue() {
        if(n == 0) {
            throw new NoSuchElementException();
        }
        
        int rnd = StdRandom.uniform(n);
        Item item = a[rnd]; 
        a[rnd] = a[--n];
        a[n] = null;
        
        if(n > 0 && n == a.length/4) {
            resize(a.length/2);
        }
        
        return item;
    }
    
    /**
     * @description return a random item (but do not remove it)
     * @return Generic Item
     */   
    public Item sample() {
        if(n == 0) {
            throw new NoSuchElementException();
        }
        
        return a[StdRandom.uniform(n)];
    }  
    
    /**
     * @description return an independent iterator over items in random order
     * @return Iterator
     */  
    public Iterator<Item> iterator() {
        return new ListIterator();
    }  
    
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        int current = 0;
        
        @Override
        public boolean hasNext()  { 
            return current != n;                     
        }
        
        @Override
        public void remove() { 
            throw new UnsupportedOperationException();  
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();   
            return a[current++];
        }
    }
    
    /**
     * @description unit testing (optional)
     */  
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(6);
        assert(rq.a.length == 2);
        rq.enqueue(34);
        assert(rq.a.length == 4);
        rq.enqueue(11);
        rq.enqueue(8);
        assert(rq.a.length == 8);
        
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        assert(rq.a.length == 4);
        rq.dequeue();
        assert(rq.a.length == 2);
        rq.dequeue();
        
    }   
}
