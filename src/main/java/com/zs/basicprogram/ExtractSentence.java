package com.zs.basicprogram;

import java.util.ArrayList;

/**
 * This class help you to extract the words in given sentence.
 */
public class ExtractSentence {
    void extractSentence(String sentence) {
        String st = "";
        int len = sentence.length();

        /**
         * Use arraylist to store the words in sentence.
         */
        ArrayList<String> words = new ArrayList<>();

        for(int i=0;i<len;i++) {
            if(sentence.charAt(i) == ' ') {
                /**
                 * Add the word in arraylist when space come because space divide the word in string.
                 */
                words.add(st);
                st = "";
            }else {
                st = st + sentence.charAt(i);
            }
        }
        if(st.length() > 0) {
            words.add(st);
        }

        for(int i=0;i<words.size() ;i ++) {
            System.out.println(words.get(i));
        }
    }

//    public static void main(String[] args) {
//        ExtractSentence ext = new ExtractSentence();
//        ext.extractSentence("abc def ghi jkl");
//    }
}
