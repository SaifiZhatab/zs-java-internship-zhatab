package com.zs.hobbies.cache;

/**
 * This is a interface used for CacheImp class.
 * This interface provide the functionality to LRU Cache
 * This interface give the get put functionality
 */
public interface LruService {
    void put(String key, Node node);
    Node get(String key);
}
