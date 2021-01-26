package main.java.com.zs.hobbies.cache;

public interface LruService {
    void put(Node node);
    Node get(int id,String name);
    void delete(Node node);
    void insert(Node node);
    void display();
    Node getLastTick(int id,String hobby);
    int setLongestStreak(int id, String hobby);
    void clearLongestStreakCache(int id,String hobby);
}
