package com.zs.exercise1;
/*
This class is use to add the two 1D array
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
