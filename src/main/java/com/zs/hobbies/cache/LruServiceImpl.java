package com.zs.hobbies.cache;

import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.cache.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is LRU Cache implementation which used hashmap data structure
 * In this class we use map and double linkedlist concept
 * It take O(1) time complexity for both put and get method
 */
public class LruServiceImpl implements LruService {
    /**
     * it is the capacity of the LRU Cache i.e. how many number of object LRU Cache store at time
     */
    int capacity;

    /**
     * this node is indicate the head and last node of the lru Cache
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
     * this constructor initialise all the private variables and set the capacity of lru Cache
     * @param capacity  capacity of LRU Cache
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
     * this method help you to delete the object in LRU Cache
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
     * This function help you to insert the object in LRU Cache
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
     * This function use map for checking the key is present in LRU Cache or not
     *      if the key present in LRU Cache, then it insert into starting or LRU list and return the object
     *      if the key isn't present in LRU Cache, then it return null
     * @param key   key to find the object in LRU Cache
     * @return  if the key is present in LRU Cache, then return key mapped object
     *          if the key isn't present in LRY Cache, then return null object
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
     * This function help you to insert the object in LRU Cache using key
     * If the key is already present in LRU Cache, then it will replace by object
     * if the key isn't present, then check the capacity of LRU Cache is full or not
     * if the LRU Cache is not full means you can directly enter the object in it.
     * if the LRu Cache is full, then you need to delete the least recent used object in LRU Cache
     * and insert the new object in it
     */
    public void put(String key, Node node) {
        /**
         * if key is already present in lru Cache, then you delete the object and insert new object on the
         * same key
         */
        if (map.containsKey(key)) {
            /**
             * delete the already present object in lru Cache
             */
            delete(map.get(key));
            map.remove(key);
        }
        /**
         * if key isn't present in lru Cache
         * then check the size of LUR Cache and based on it take action
         */
        else {

            /**
             * if the size of LRU Cache is full, then you need to delete the least recent used object in LRU Cache
             */
            if (map.size() >= capacity) {

                /**
                 * find the least recent used object in list
                 */
                String leastRecentUsedKey = last.getPrevious().getKey();

                /**
                 * delete the least recent used object in LRU Cache
                 */
                delete(map.get(leastRecentUsedKey));
                map.remove(leastRecentUsedKey);
            }
        }
        /**
         * insert new object in the LRU Cache
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
