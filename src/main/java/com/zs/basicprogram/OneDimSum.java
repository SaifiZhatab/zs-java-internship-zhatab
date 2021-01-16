package main.java.com.zs.basicprogram;

/**
 * This class help you to find the sum of two 1D array
 */
public class OneDimSum {

    void oneDimSum(int arr1[] ,int arr2[] , int no_of_element) {
        int sum[] = new int[no_of_element];
        for(int i=0;i<no_of_element;i++) {
            sum[i] = arr1[i] + arr2[i];
        }

        for(int i=0;i<no_of_element;i++) {
            System.out.print(sum[i] + " ");
        }
    }
}
