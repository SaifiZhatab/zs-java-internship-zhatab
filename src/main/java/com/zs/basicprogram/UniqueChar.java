package com.zs.basicprogram;

import java.util.*;

/**
 * This class help you to find the unique word in given string
 */
public class UniqueChar {

    void uniqueChar(String sentence) {

        /**
         * Use hashmap to store the unique word
         * hashmap contain unique key only
         */
        Set<Character> storage = new HashSet<Character>();

        int len = sentence.length();

        for(int i=0;i<len;i++) {
            storage.add(sentence.charAt(i));
        }

       Iterator iterator = storage.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
    }
    public static void main(String[] args) {
        UniqueChar uniqueChar = new UniqueChar();
        uniqueChar.uniqueChar("abcdefabcefabcd");
    }
}
