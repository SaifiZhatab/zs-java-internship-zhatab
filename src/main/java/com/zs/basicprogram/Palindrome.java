package com.zs.basicprogram;

/**
 * This class help you to check the number is palindrome or not
 */
public class Palindrome {
    void palindrome(int num) {
        /**
         * keep the copy of original number
         */
        int copy = num ;
        int pali = 0;

        while(num != 0) {
            pali = pali*10 + num%10;
            num = num/10;
        }

        /**
         * check the number and its reverse is same or not
         */
        if(pali == copy) {
            System.out.println("Yes this number is palindorm");
        }else {
            System.out.println("Yes this number isn't palindorm");
        }
    }
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        palindrome.palindrome(12221);
        palindrome.palindrome(123432);
    }
}
