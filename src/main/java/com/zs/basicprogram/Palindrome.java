package main.java.com.zs.basicprogram;

/**
 * This class help you to check the number is palindrome or not
 */
public class Palindrome {
    public boolean palindrome(int num) {
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
            return true;
        }else {
            System.out.println("Yes this number isn't palindorm");
            return false;
        }
    }
}
