package main.java.com.zs.hobbies.extrafeature;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.*;

public class Lru {
   int capacity;
   private Deque<Node> doubleQueue;
   private HashSet<Node> hashSet;

   public Lru(int capacity){
       this.capacity = capacity;
       doubleQueue = new LinkedList<Node>();
       hashSet = new HashSet<Node>();
   }

   public void refer(Node node){
        if(!hashSet.contains(node)){
            /**
             * if the object doesn't present in LRU cache
             */
            if(doubleQueue.size() == capacity){
                /**
                 * if size of LRU cache is full, then you need to delete the last recent used object
                 * and insert this object
                 */
                Node last = doubleQueue.removeLast();
                hashSet.remove(last);
            }else {
                /**
                 * if this object present in LRU Cache, then just insert this object in starting
                 */
                doubleQueue.remove(node);
            }

            /**
             * insert object in LRU cache
             */
            doubleQueue.push(node);
            hashSet.add(node);
        }
   }

   public void display(){
       Iterator<Node> iterator = doubleQueue.iterator();

       while(iterator.hasNext()){
            Node node = iterator.next();
           System.out.println("Total Objects in LRU cache ");

            if(node.getBadminton() != null) {
                System.out.println("This is Badminton object " + node.getBadminton().getId());
            }else if(node.getChess() != null) {
                System.out.println("This is Chess object " + node.getChess().getId());
            }else if(node.getTravelling() != null) {
                System.out.println("This is Travelling Object " + node.getTravelling().getId());
            }else if(node.getVideoWatching() != null) {
                System.out.println("This is Video Watching object " +node.getVideoWatching().getId() );
            }else if(node.getPerson() != null) {
                System.out.println("This is Person Object " + node.getPerson().getId());
            }
       }
   }
}
