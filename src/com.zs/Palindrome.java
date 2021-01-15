package com.zs.exc1;

/*
This class check the number is pallindrom or not
 */
public class Palindrome {
    void palindrome(int num) {
        int copy = num ;
        int pali = 0;

        while(num != 0) {
            pali = pali*10 + num%10;
            num = num/10;
        }

        // check the number and its reverse is same or not
        if(pali == copy) {
            System.out.println("Yes this number is palindorm");
        }else {
            System.out.println("Yes this number isn't palindorm");
        }
    }
}
