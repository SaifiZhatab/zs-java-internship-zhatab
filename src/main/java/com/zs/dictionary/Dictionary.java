package com.zs.dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *  Implement the Dictionary using tries data structure.
 *  Feature:
 *      insert word in dictionary
 *      search word in dictionary
 *      find meaning in dictionary
 *      find the correct word
 *      find the words start with given string
 */
public class Dictionary {

    static final int ALPHABET_SIZE = 26;
    private static Logger logger;

    /**
     *  This is the node which use to store the Characters is the tries node structure
     */
    static class Node {
        Node child[] = new Node[ALPHABET_SIZE];
        boolean isEndOfWord;
        String meaning;

        Node() {
            isEndOfWord = false;
            meaning = "";
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                child[i] = null;
            }
        }
    };

    /**
     *  This is the node which store the head of tries
     *  use to move in the trie
     */
    static Node head;

    /**
     * the function insert the word in trie
     * @param word the word
     * @param meaning the meaning of word
     */
    static void insert(String word , String meaning) {
        logger.info("insert the word in dictionary");

        int length = word.length();
        Node node = head;
        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);
            if(node.child[ch-'a'] == null) {
                /**
                 * when the node is null, then insert new node
                 */
                node.child[ch-'a'] = new Node();
            }
            node = node.child[ch-'a'];
        }
        logger.info("move in last node");
        node.isEndOfWord = true;
        node.meaning = meaning;

        logger.info("insert the word in dictionary successfully");
    }

    /**
     * return true or false
     *      if word present in dictionary, then return true
     *      if word not present in dictionary, then return false;
     * @param word the word
     * @return  true or false
     */
    static boolean search(String word) {
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);
            if(node.child[(int)(ch -'a')] == null) {
                return false;
            }
            node = node.child[ch-'a'];
        }

        return node.isEndOfWord;
    }

    /**
     * return the meaning of word
     *      if the word is null, then return null string
     *
     * @param word the word
     * @return the meaning of word
     */
    static String meaning(String word) {
        logger.info("find the word in dictionary");
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                //  word isn't present in dictionary

                logger.warning("word isn't present in dictionary");
                return null;
            }
            node = node.child[ch-'a'];
        }

        return node.meaning;
    }

    /**
     * return the arraylist whose present the similar word
     *      only match with similar length word in dictionary
     *
     * @param word  the word
     * @return  arraylist of similar data
     */
    static ArrayList<String> correctWord(String word) {
        logger.info("find the correct word which similar to given word");
        int length = word.length();

        ArrayList<String> correct = new ArrayList<String>();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            for(int j=0;j<ALPHABET_SIZE;j++) {
                if((ch-'a') != j ) {
                    /**
                     * change every possibility
                     */
                    String st = word.substring(0,i) + (char)('a' + j) + word.substring(i+1);

                    if(search(st)) {
                        correct.add(st);
                    }
                }
            }
        }
        logger.info("successfully return the similar words");
        return correct;
    }

    /**
     * return all string which contain given node
     * @param node  current node in trie
     * @param matchWord  store result
     * @param word  the word
     */
    static void addInList(Node node, ArrayList<String>  matchWord , String word) {

        if(node.isEndOfWord ) {
            matchWord.add(word);
        }

        for(int i=0;i<ALPHABET_SIZE;i++) {
            if(node.child[i] != null) {
                addInList(node.child[i],matchWord, word + (char)('a' + i) );
            }
        }
    }

    /**
     * return the arraylist whose contain the who start with given string
     *      if the given string is null, then the return empty arraylist
     *      if no word start with given string, then the return empty arraylist
     * @param word  the word
     * @return  the arraylist
     */
    static ArrayList<String> matchWord(String word) {
        logger.info("find all word start with given word");
        Node node = head;
        int len = word.length();
        ArrayList<String> matchWord = new ArrayList<String>();

        for(int i=0;i<len;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                return matchWord;
            }
            node = node.child[ch - 'a'];
        }
        addInList(node , matchWord , word);

        logger.info("successfully fetch all matched words");
        return matchWord;
    }

    /**
     * this is main method who control the dictionary
     * @param st command line argument
     */
//    public static void main(String st[])  {
//
//        logger = Logger.getLogger(Dictionary.class.getName());
//
//        Scanner in = new Scanner(System.in);
//        head = new Node();
//
//        logger.info("Please read the instruction carefully");
//        logger.info("1 - word insert in dictionary");
//        logger.info("2 - check word in dictionary");
//        logger.info("3 - find meaning in dictionary");
//        logger.info("4 - find similar word");
//        logger.info("5 - word start with ");
//        logger.info("6 - for exit ");
//
//        int choice;
//        String word, meaning;
//        boolean check;
//        do {
//
//            logger.info("Please choose which operation you want to perform = ");
//            choice = in.nextInt();
//
//            switch(choice) {
//                case 1:
//                    System.out.print("Please enter word = ");
//                    word = in.next();
//                    System.out.print("Please enter meaning = ");
//                    meaning = in.next();
//                    insert(word,meaning);
//                    break;
//
//                case 2:
//                    System.out.print("Please enter which you want to check = ");
//                    word = in.next();
//                    check = search(word);
//                    if(check) {
//                        logger.info("Yes this word is available in dictionary");
//                    }else {
//                        logger.info("No, this word isn't present in dictionary");
//                    }
//                    break;
//
//                case 3:
//                    System.out.print("Please enter the word = ");
//                    word = in.next();
//
//                    check = search(word);
//                    if(check) {
//                        meaning = meaning(word);
//                        logger.info(word + " : " + meaning);
//                    }else {
//                        logger.info("Sorry,this word isn't present in dictionary");
//                    }
//                    break;
//
//                case 4:
//                    System.out.print("Please enter the word = ");
//                    word = in.next();
//                    ArrayList<String> correctWord = correctWord(word);
//
//                    if(correctWord.size() == 0) {
//                        logger.info("No word present in dictionary similar to "+ word);
//                    }else {
//                        int index=1;
//                        logger.info("Words start with " + word );
//
//                        for(String match : correctWord) {
//                            logger.info(index + ") " + match );
//                            index++;
//                        }
//                    }
//                    break;
//
//                case 5:
//                    System.out.print("Please enter word = ");
//                    word = in.next();
//
//                    ArrayList<String> matchWord = matchWord(word);
//
//
//                    if(matchWord.size() == 0) {
//                        logger.info("No word present in dictionary who match with "+ word);
//                    }else {
//                        int index=1;
//                        logger.info("Words start with " + word );
//
//                        for(String match : matchWord) {
//                            logger.info(index + ") " + match );
//                            index++;
//                        }
//                    }
//                    break;
//
//                default:
//                    break;
//            }
//
//        }while(choice != 6);
//    }
}
