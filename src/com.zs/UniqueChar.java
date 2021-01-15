package com.zs.exc1;

/*
This class help you to find the unique character in Strnig
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UniqueChar {
    void uniqueChar(String sentence) {
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
