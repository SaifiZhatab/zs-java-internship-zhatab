package com.zs.exc1;

/*
This class is use for ectract the word in sentence
 */

import java.util.ArrayList;

public class ExtractSentence {
    void ExtractSentence(String sentence) {
        String st = "";
        int len = sentence.length();

        // use array list to store the word
        ArrayList<String> words = new ArrayList<>();

        for(int i=0;i<len;i++) {
            if(sentence.charAt(i) == ' ') {
                words.add(st);
                st = "";
            }else {
                st = st + sentence.charAt(i);
            }
        }

        for(int i=0;i<words.size() ;i ++) {
            System.out.println(words.get(i));
        }
    }
}
