package com.zs.exercise1;

/*
This class help you to find the prime number
between 1 to N
 */
public class Prime {
    void prime(int num) {
        for(int i=1;i<num ;i++) {
            boolean check = true;

            for(int j=1;j<i ; j ++) {
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
}
