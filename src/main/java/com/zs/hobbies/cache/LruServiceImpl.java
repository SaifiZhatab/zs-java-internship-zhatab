package com.zs.hobbies.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used for LRU cache implementation
 * In this class we use map and double linkedlist concept
 * It take O(1) time complexity for both put and get method
 */
public class LruServiceImpl implements LruService {
    /**
     * it is the capacity of the LRU cache i.e. how many number of object LRU cache store at time
     */
    int capacity;

    /**
     * this node is indicate the head and last node of the lru cache
     * head always point to the first object of double linkedlist
     * last always point to the last object of double linkedlist
     */
    private Node head ,last;

    /**
     * map is used for check the key is already present or not in the linkedlist
     * map take O(1) time complexity for insert and delete function for some particular size of map
     */
    private Map<String,Node> map;

    /**
     * This is a constructor.
     * this constructor initialise all the private variables and set the capacity of lru cache
     * @param capacity  capacity of LRU cache
     */
    public LruServiceImpl(int capacity){
        this.capacity = capacity;
        map = new HashMap<String,Node>();

        head = new Node();
        last = new Node();

        /**
         * Connect the head and last node to each other
         */
        head.setNext(last);
        last.setPrevious(head);
    }

    /**
     * this method help you to delete the object in LRU cache
     * this function take O(1) time to delete a object
     * @param node  delete node
     */
     public void delete(Node node){
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());

        node.setPrevious(null);
        node.setNext(null);
    }

    /**
     * This function help you to insert the object in LRU cache
     * This function take O(1) time to inset in LRU cahce
     * @param node  insert node
     */
    public void insert(Node node){
        node.setNext(head.getNext());
        head.getNext().setPrevious(node);

        node.setPrevious(head);
        head.setNext(node);
    }

    /**
     * This function help you to find to get the object on the basis of given key
     * This function use map for checking the key is present in LRU cache or not
     *      if the key present in LRU cache, then it insert into starting or LRU list and return the object
     *      if the key isn't present in LRU cache, then it return null
     * @param key   key to find the object in LRU cache
     * @return  if the key is present in LRU cache, then return key mapped object
     *          if the key isn't present in LRY cache, then return null object
     */
    public Node get(String key){
        Node node = null;

        /**
         * containKey check the key is present in map or not
         * if the key is present in map, then it return true
         * if the key isn't present in map, then it return false
         */
        if (map.containsKey(key)) {
            node = map.get(key);

            /**
             * if the key is present, then move the object in start
             */
            delete(node);
            insert(node);
        }
        return node;
    }

    /**
     * This function help you to insert the object in LRU cache using key
     * If the key is already present in LRU cache, then it will replace by object
     * if the key isn't present, then check the capacity of LRU cache is full or not
     * if the LRU cache is not full means you can directly enter the object in it.
     * if the LRu cache is full, then you need to delete the least recent used object in LRU cache
     * and insert the new object in it
     */
    public void put(String key, Node node) {
        /**
         * if key is already present in lru cache, then you delete the object and insert new object on the
         * same key
         */
        if (map.containsKey(key)) {
            /**
             * delete the already present object in lru cache
             */
            delete(map.get(key));
            map.remove(key);
        }
        /**
         * if key isn't present in lru cache
         * then check the size of LUR cache and based on it take action
         */
        else {

            /**
             * if the size of LRU cache is full, then you need to delete the least recent used object in LRU cache
             */
            if (map.size() >= capacity) {

                /**
                 * find the least recent used object in list
                 */
                String leastRecentUsedKey = last.getPrevious().getKey();

                /**
                 * delete the least recent used object in LRU cache
                 */
                delete(map.get(leastRecentUsedKey));
                map.remove(leastRecentUsedKey);
            }
        }
        /**
         * insert new object in the LRU cache
         */
        map.put(key, node);
        insert(node);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<String, Node> getMap() {
        return map;
    }

    public void setMap(Map<String, Node> map) {
        this.map = map;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }
}
