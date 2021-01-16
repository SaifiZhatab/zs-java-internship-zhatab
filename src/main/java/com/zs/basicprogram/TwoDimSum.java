package main.java.com.zs.basicprogram;

/**
 * This class help you to add the two 2D array
 */
public class TwoDimSum {

    void twoDimSum(int arr1[][] ,int arr2[][] ,int row, int col) {
        int sum[][] = new int[row][col];

        for(int i=0;i<row; i++) {
            for(int j = 0;j<col ;j++) {
                sum[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        for(int i=0;i<row;i++) {
            for(int j=0 ; j< col ; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}
