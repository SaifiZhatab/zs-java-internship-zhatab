package main.java.com.zs.hobbies.cache;

/**
 * This is a interface used for LruServiceImp class.
 * This interface provide the functionality to LRU cache
 * This interface give the get put functionality
 */
public interface LruService {
    void put(String key, Node node);
    Node get(String key);
}
