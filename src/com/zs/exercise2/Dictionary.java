package com.zs.exercise2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Dictionary {

    static final int ALPHABET_SIZE = 26;
    private static Logger logger;

    //This is the node which use to store the Character
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

    // This is root node of trie tree
    static Node head;

    //This function help you to insert the word in dictionary
    static void insert(String word , String meaning) {
        logger.info("insert the word in dictionary");

        int length = word.length();
        Node node = head;

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);
            if(node.child[ch-'a'] == null) {
                node.child[ch-'a'] = new Node();
            }
            node = node.child[ch-'a'];
        }
        logger.info("move in last node");
        node.isEndOfWord = true;
        node.meaning = meaning;

        logger.info("insert the word in dictionary successfully");
    }

    //This function help you to search the word in dictionary
    static boolean search(String word) {
        logger.info("search the word in dictionary");
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);
            if(node.child[(int)(ch -'a')] == null) {
                logger.warning("word isn't present in dictionary");
                return false;
            }
            node = node.child[ch-'a'];
        }
        if(node.isEndOfWord == false) {
            logger.warning("word isn't present in dictionary");
        }else{
            logger.info("word is present in dictionary");
        }
        return node.isEndOfWord;
    }

    //This function help you to find the meaning in dictionary
    static String meaning(String word){
        logger.info("find the word in dictionary");
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                logger.warning("word isn't present in dictionary");
                return null;
            }
            node = node.child[ch-'a'];
        }

        return node.meaning;
    }



	//This function help you to check the correct word in dictionary
    static ArrayList<String> correctWord(String word){
        logger.info("find the correct word which similar to given word");
        int length = word.length();

        ArrayList<String> correct = new ArrayList<String>();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            for(int j=0;j<ALPHABET_SIZE;j++){
                if((ch-'a') != j ){
                    String st = word.substring(0,i) + (char) ('a' + j) + word.substring(i+1);
                    logger.info("check for every possible similar word");
                    if(search(st) == true) {
                        correct.add(st);
                    }
                }
            }
        }
        logger.info("successfully return the similar words");
        return correct;
    }

    static void addInList(Node node, ArrayList<String>  matchWord , String word){

        if(node.isEndOfWord == true){
            matchWord.add(word);
        }

        for(int i=0;i<ALPHABET_SIZE;i++) {
            if(node.child[i] != null) {
                addInList(node.child[i],matchWord, word + (char)('a' + i) );
            }
        }
    }

    //This function help you to find the match string in dictionary
    static ArrayList<String> matchWord(String word){
        logger.info("find all word start with given word");
        Node node = head;
        int len = word.length();
        ArrayList<String> matchWord = new ArrayList<String>();

        for(int i=0;i<len;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null){
                return matchWord;
            }
            node = node.child[ch - 'a'];
        }
        addInList(node , matchWord , word);

        logger.info("successfully fetch all matched words");
        return matchWord;
    }

    public static void main(String st[])  {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/resources/logging.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger = Logger.getLogger(Dictionary.class.getName());

        Scanner in = new Scanner(System.in);
        head = new Node();

        System.out.println("Please read the instruction carefully");
        System.out.println("1 - word insert in dictionary");
        System.out.println("2 - check word in dictionary");
        System.out.println("3 - find meaning in dictionary");
        System.out.println("4 - find similar word");
        System.out.println("5 - word start with ");
        System.out.println("6 - for exit ");

        int choice;
        String word, meaning;
        boolean check;
        do{

            System.out.println("Please choose which operation you want to perform = ");
            choice = in.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Please enter word = ");
                    word = in.next();
                    System.out.print("Please enter meaning = ");
                    meaning = in.next();
                    insert(word,meaning);
                    break;

                case 2:
                    System.out.print("Please enter which you want to check = ");
                    word = in.next();
                    check = search(word);
                    if(check) {
                        System.out.println("Yes this word is available in dictionary");
                    }else {
                        System.out.println("No, this word isn't present in dictionary");
                    }
                    break;

                case 3:
                    System.out.print("Please enter the word = ");
                    word = in.next();

                    check = search(word);
                    if(check){
                        meaning = meaning(word);
                        System.out.println(word + " : " + meaning);
                    }else{
                        System.out.println("Sorry,this word isn't present in dictionary");
                    }
                    break;

                case 4:
                    System.out.print("Please enter the word = ");
                    word = in.next();
                    ArrayList<String> correctWord = correctWord((String) word);

                    if(correctWord.size() == 0) {
                        System.out.println("No word present in dictionary similar to "+ word);
                    }else {
                        int ind=1;
                        System.out.println("Words start with " + word );

                        for(String match : correctWord) {
                            System.out.println(ind + ") " + match );
                            ind++;
                        }
                    }
                    break;

                case 5:
                    System.out.print("Please enter word = ");
                    word = in.next();

                    ArrayList<String> matchWord = matchWord(word);


                    if(matchWord.size() == 0) {
                        System.out.println("No word present in dictionary who match with "+ word);
                    }else {
                        int ind=1;
                        System.out.println("Words start with " + word );

                        for(String match : matchWord) {
                            System.out.println(ind + ") " + match );
                            ind++;
                        }
                    }
                    break;

                default:
                    break;
            }

        }while(choice != 6);
    }
}
