
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
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
   
    private int n;
    private Node first;
    private Node last;
    
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    /**
     * @description construct an empty deque 
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }
    
    /**
     * @description is the deque empty? 
     * @return boolean
     */   
    public boolean isEmpty() {
        return n == 0;
    }              
   
    /**
     * @description return the number of items on the deque
     * @return int
     */      
    public int size() {
        return n;
    }            
   
    /**
     * @description add the item to the front
     */  
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        Node tmpNode = new Node();
        tmpNode.item = item;
        
        if(first == null) {
            first = tmpNode;
            last = tmpNode;
        } else { 
            Node oldFirst = first;
            first = tmpNode;
            first.next = oldFirst;
            first.previous = null;
            oldFirst.previous = first;            
        }
        
        n++;
        assert check();
    }   

    /**
     * @description add the item to the end
     */     
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        
        Node tmpNode = new Node();
        tmpNode.item = item;
        
        if(last == null) {
            first = tmpNode;
            last = tmpNode;
        } else {
            Node oldLast = last;   
            last = tmpNode;
            last.next = null;
            last.previous = oldLast;
            oldLast.next = last;
        }
        
        
        n++;
        assert check();
    }   

    /**
     * @description remove and return the item from the front
     * @return Generic Item
     */  
    public Item removeFirst() {
        if(n == 0){
            throw new NoSuchElementException ();
        }
        
        Item item = first.item;
        
        if(n == 1) {
            first = null;
            last = null;
        } else {            
            first = first.next;
            first.previous = null;
        }
        
        n--;
        
        assert check();
        
        return item;
    }              

    /**
     * @description remove and return the item from the end
     * @return Generic Item
     */    
    public Item removeLast() {
        if(n == 0){
            throw new NoSuchElementException ();
        }
        
        Item item = last.item;
        
        if(n == 1) {
            first = null;
            last = null;
        } else {            
            last = last.previous;
            last.next = null;
        }        
        
        return item;
    }

    /**
     * @description return an iterator over items in order from front to end
     * @return Iterator<Item>
     */      
    @Override
    public Iterator<Item> iterator() { 
        return new ListIterator();
    }   
    
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        
        @Override
        public boolean hasNext()  { 
            return current != null;                     
        }
        
        @Override
        public void remove() { 
            throw new UnsupportedOperationException();  
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    // check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        switch (n) {
            case 0:
                if (first != null) return false;
                break;
            case 1:
                if (first == null)      return false;
                if (last == null)      return false;
                if (first.next != null) return false;
                if (first.previous != null) return false;
                break;
            default:
                if (first == null)      return false;
                if (first.next == null) return false;
                if (first.previous == null) return false;
                break;
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }
        return numberOfNodes == n;
    }

    /**
     * @description unit testing
     */ 
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(1);
        dq.addLast(5);
        assert(dq.first.next.item == dq.last.item);
        
        dq.removeFirst();
        dq.removeLast();
        assert(dq.first == null);
        assert(dq.last == null);
        
        dq.addLast(9);
        dq.addLast(2);
        dq.addLast(5);
        assert(dq.last.item == 5);
        assert(dq.first.item == 9);
        assert(dq.n == 3);
        
        Iterator it = dq.iterator();
        assert((Integer)it.next() == 2);
        assert((Integer)it.next() == 5);
    }  
}
