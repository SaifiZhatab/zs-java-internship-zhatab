package main.java.com.zs.hobbies.cache;

import java.util.HashMap;
import java.util.Map;

public class LruServiceImpl implements LruService {
    int capacity;
    private Node head ,last;
    private Map<String,Node> map;

    public LruServiceImpl(int capacity){
        this.capacity = capacity;
        map = new HashMap<String,Node>();

        head = new Node();
        last = new Node();

        head.setNext(last);
        last.setPrevious(head);
    }

    void delete(Node node){
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());

        node.setPerson(null);
        node.setNext(null);

    }

    void insert(Node node){
        node.setNext(head.getNext());
        head.getNext().setPrevious(node);

        node.setPrevious(head);
        head.setNext(node);
    }

    public Node get(int id, String name){
        String key = String.valueOf(id) + "_" + name;

        Node node = null;

        if(map.containsKey(key)) {
            /**
             * if key is available in the map
             */
            node = map.get(key);

            /**
             * move into starting of list
             */
            delete(node);
            insert(node);
        }
        return  node;
    }

    /**
     * insert object into LRU cache
     */
    public void put(Node node) {
        String key = node.getId();

        /**
         * if key is already present in lru cache
         */
        if(map.containsKey(key)) {
             delete(map.get(key));
        }
        /**
         * if key isn't present in lru cache
         */
        else {

            /**
             * if the lru is full
             */
            if (map.size() >= capacity) {

                /**
                 * delete the least recent used node in the LRU cache
                 */
                String delete_id = last.getPrevious().getId();
                delete(map.get(delete_id));
                map.remove(delete_id);

            }

            /**
             * insert new object in the LRU cache
             */
            map.put(key,node);
        }
        insert(node);
    }
    public void display(){
        Node node = head;
        node = node.getNext();
        while(node != null) {
            System.out.println(node.getId());
            node = node.getNext();
        }
    }
}
