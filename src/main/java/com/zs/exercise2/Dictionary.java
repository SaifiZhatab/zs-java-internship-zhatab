package main.java.com.zs.exercise2;

import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {

    static final int ALPHABET_SIZE = 26;

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

    static Node head;

    public Dictionary(){
        head = new Node();
    }

    public static void insert(String word, String meaning) {
        int length = word.length();
        Node node = head;

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                node.child[ch-'a'] = new Node();
            }
            node = node.child[ch-'a'];
        }
        node.isEndOfWord = true;
        node.meaning = meaning;
    }

    public static boolean search(String word) {
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                return false;
            }
            node = node.child[ch-'a'];
        }
        return node.isEndOfWord;
    }

   public static String meaning(String word){
        Node node = head;
        int  length = word.length();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            if(node.child[ch-'a'] == null) {
                return null;
            }
            node = node.child[ch-'a'];
        }
        return node.meaning;
    }


    public static ArrayList<String> correctWord(String word){
        int length = word.length();

        ArrayList<String> correct = new ArrayList<String>();

        for(int i=0;i<length;i++) {
            char ch = word.charAt(i);

            for(int j=0;j<ALPHABET_SIZE;j++){
                if((ch-'a') != j){
                    String st = word.substring(0,i) + (char)('a' + j) + word.substring(i+1);
                    if(search(st)) {
                        correct.add(st);
                    }
                }
            }
        }
        return correct;
    }

   public static void addInList(Node node, ArrayList<String>  matchWord , String word){
        if(node.isEndOfWord ){
            matchWord.add(word);
        }

        for(int i=0;i<ALPHABET_SIZE;i++) {
            if(node.child[i] != null) {
                addInList(node.child[i],matchWord, word + (char)('a' + i) );
            }
        }
    }

   public static ArrayList<String> matchWord(String word){
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
        return matchWord;
    }

    public static void main(String st[]){
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
        do{
            String word, meaning;
            boolean check;

            System.out.print("Please choose which operation you want to perform = ");
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
                    ArrayList<String> correctWord = correctWord(word);

                    if(correctWord.size() == 0) {
                        System.out.println("No word present in dictionary similar to "+ word);
                    }else {
                        int index=1;
                        System.out.println("Words start with " + word );

                        for(String match : correctWord) {
                            System.out.println(index + ") " + match );
                            index++;
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
                        int index=1;
                        System.out.println("Words start with " + word );

                        for(String match : matchWord) {
                            System.out.println(index + ") " + match );
                            index++;
                        }
                    }
                    break;

                default:
                    break;
            }

        }while(choice != 6);
    }
}
