package com.zs.basicprogram;

/**
 * This class help you to find the prime number  between 1 to N range
 */
public class Prime {
    void prime(int num) {
        for(int i=2;i<num ;i++) {
            boolean check = true;

            /**
             * check the number i is divide by any its smaller number.
             * if yes, then the number is not prime
             * if no, then the number is prime
             */
            for(int j=2;j<i ; j ++) {
                if(i%j == 0) {
                    check = false;
                    break;
                }
            }
            if(check) {
                System.out.print(i +" ");
            }
        }
    }
//    public static void main(String[] args) {
//        Prime prime = new Prime();
//        prime.prime(20);
//    }
}
