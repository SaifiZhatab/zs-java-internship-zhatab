package main.java.com.zs.basicprogram;

/**
 * This class help you to check the emi.
 */
public class Emi {
    void emi(int loan_amount, int interest ,int time_period) {
        float emi= 0l;

        interest = interest / (12 * 100);
        time_period = time_period * 12;

        /**
         * formula to calculate emi
         *      E = (P.r.(1+r)n) / ((1+r)n – 1)
         *      Here,
         *      P = loan amount i.e principal amount
         *      R = Interest rate per month
         *      T = Loan time period in year
         */
        emi = (loan_amount * interest * (float)Math.pow(1 + interest, time_period))
                / (float)(Math.pow(1 + interest, time_period) - 1);

       System.out.println(emi);
    }
}
