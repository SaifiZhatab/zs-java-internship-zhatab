package com.zs.hobbies.cache;

import java.util.LinkedHashMap;

/**
 * This is Cache generic class implementation.
 *This class use LinkHashMap data structure to store object
 * @param <String> the key
 * @param <V>  the value object
 */
public class Cache <String,V>{
    /**
     * This variable store the size of capacity
     */
    private int capacity;

    /**
     * This is the cache storage
     */
    LinkedHashMap<String,V> cache;

    /**
     * This is a constructor.
     * this constructor initialise all the private variables and set the capacity of lru Cache
     * @param capacity  capacity of LRU Cache
     */
    public Cache(int capacity){
        this.capacity = capacity;
        cache = new LinkedHashMap<String,V>();
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
    public V get(String key) {
        /**
         * check key present or not in Cache
         */
        if(cache.containsKey(key)) {
            V value = cache.get(key);

            /**
             * remove key in Cache and insert in ending of list
             */
            cache.remove(key);

            cache.put(key,value);
        }
        return null;
    }

    /**
     * This function help you to insert the object in LRU Cache using key
     * If the key is already present in LRU Cache, then it will replace by object
     * if the key isn't present, then check the capacity of LRU Cache is full or not
     * if the LRU Cache is not full means you can directly enter the object in it.
     * if the LRu Cache is full, then you need to delete the least recent used object in LRU Cache
     * (first object in LinkedHashMap is least recent used object)
     * and insert the new object in last
     * @param key the key id
     * @param value the object
     */
    public void put(String key, V value) {
        /**
         * if key is already present in lru Cache, then you delete the object and insert new object on the
         * same key
         */
        if(cache.containsKey(key)) {
            /**
             * delete the key in Cache
             */
            cache.remove(key);

        }
        /**
         * if key isn't present in lru Cache
         * then check the size of LUR Cache and based on it take action
         */
        else {

            /**
             * if the size of LRU Cache is full, then you need to delete the least recent used object in LRU Cache
             */
            if(cache.size() == capacity) {
                /**
                 * delete the first object in Cache
                 */
                cache.remove(cache.keySet().iterator().next());

            }
        }
        /**
         * insert object in Cache
         */
        cache.put(key,value);
    }
}
