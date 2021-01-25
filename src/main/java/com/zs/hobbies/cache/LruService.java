package main.java.com.zs.hobbies.cache;

public interface LruService {
    void put(Node node);
    Node get(int id,String name);
    void display();
}
