package com.zs.exercise1;

/*
This class convert the decimal to binary number
 */

public class DecimalToBinary {
    // function change the decimal to binary string
    void binaryToDecimal(int num){
        String binary = "";
        while(num != 0) {
            binary = (num%2 + '0') + binary;
            num=num/2;
        }
        System.out.println(binary);
    }
}
