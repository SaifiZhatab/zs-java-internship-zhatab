package main.java.com.zs.basicprogram;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class help you to find the unique word in given string
 */
public class UniqueChar {

    void uniqueChar(String sentence) {

        /**
         * Use hashmap to store the unique word
         * hashmap contain unique key only
         */
        HashMap<Character,Integer> storage = new HashMap<Character, Integer>();

        int len = sentence.length();

        for(int i=0;i<len;i++) {
            if( !storage.containsKey(sentence.charAt(i)) ) {
                storage.put(sentence.charAt(i) , 1);
            }
        }

        if( !storage.isEmpty() ) {
            Iterator it = storage.entrySet().iterator();

            while(it.hasNext()) {
                Map.Entry  pair = (Map.Entry) it.next();
                System.out.println(pair.getKey());
            }
        }
    }
}
