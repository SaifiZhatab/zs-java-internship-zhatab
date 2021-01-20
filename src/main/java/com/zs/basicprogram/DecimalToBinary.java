package main.java.com.zs.basicprogram;

/**
 * This class help you to convert decimal to binary number.
 */
public class DecimalToBinary {

    public String binaryToDecimal(int num){
        String binary = "";
        while(num != 0) {
            /**
             * when the num is odd, then add '1' in string
             * when the num is even, then add '0' in string
             */

            binary = (char)(num%2 + '0') + binary;
            num=num/2;
        }
         return binary;
    }
}
