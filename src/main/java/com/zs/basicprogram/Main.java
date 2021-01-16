package main.java.com.zs.basicprogram;

import java.util.Scanner;

/**
 * This class is make for control all the classes
 */
public class Main {
    public static void main(String[] args) {
	// write your code
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the valid expression = ");
        String exp = in.nextLine();

        /**
         * check for extract words in sentence
         */
        ExtractSentence ext = new ExtractSentence();
        ext.extractSentence(exp);
    }
}
