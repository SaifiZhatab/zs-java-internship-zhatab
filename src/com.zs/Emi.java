package com.zs.exc1;

/*
This class use for check the Emi
 */
public class Emi {
    void emi(int loan_amount, int interest ,int time_period) {
        float emi= 0l;

        interest = interest / (12 * 100);
        time_period = time_period * 12;

        emi = (loan_amount * interest * (float)Math.pow(1 + interest, time_period))
                / (float)(Math.pow(1 + interest, time_period) - 1);

       System.out.println(emi);
    }
}
