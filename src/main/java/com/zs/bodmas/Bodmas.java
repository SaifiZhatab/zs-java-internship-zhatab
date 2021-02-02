package com.zs.bodmas;

import java.util.Stack;

import static java.lang.Character.isDigit;

/**
 *  implement bodmas
 */

public class Bodmas {

    /**
     *  return the sum of first argument and second argument
     * @param a the integer number
     * @param b the integer number
     * @return the value a + b
     */
    public int sum(int a,int b){
        return (a+b);
    }

    /**
     *  return the subtract of first argument and second argument
     * @param a the integer number
     * @param b the integer number
     * @return the value a - b
     */
    public int subtract(int a,int b){
        return (a-b);
    }

    /**
     *  return the multiplication of first argument and second argument
     * @param a the integer number
     * @param b the integer number
     * @return the value a*b
     */
    public int multiply(int a,int b){
        return (a*b);
    }

    /**
     *  return the sum of first argument and second argument.Special case:
     *      if the second argument value is 0,then function give "divide by 0" exception
     *
     * @param a the integer number
     * @param b the integer number
     * @return the value a/b
     */
    public int divide(int a,int b){
        try {
            return (a/b);
        }catch(Exception ex) {
            System.out.println("divide by 0 ");
            return 0;
        }
    }


    /**
     *  return the precedence of operator
     * @param op the character
     * @return precedence
     */
    public int precedence(char op){
        switch(op){
            case '-' : return 1;
            case '+' : return 1;
            case '*' : return 2;
            case '/' : return 2;
            default  : return 0;
        }
    }

    /**
     * perform the operation of the basis of operator and return output
     * @param a first integer number
     * @param b second integer number
     * @param op the operator
     * @return the value on the basis of operator
     */
    public int perform(int a,int b, char op) {
        switch(op) {
            case '+' : return sum(a,b);
            case '-' : return subtract(a,b);
            case '*' : return multiply(a,b);
            case '/' : return divide(a,b);
        }
        return 0;
    }

    /**
     * solve the bodmas expression.Special case:
     *      if the expression is wrong, then they give exception.
     * @param exp the arithmetic expression
     */
    public int bodmasSolver(String exp) {
        int length = exp.length();
        int ans=0;
        Stack<Integer> value = new Stack<Integer>();
        Stack<Character> operator = new Stack<Character>();

        /**
         *  use try-catch
         *  in case of any exception occur
         *  i.e. NULLPOINTER , INDEXOUTOFBOUND exception.
         */
        try {
            for (int i = 0; i < length; i++) {
                if (exp.charAt(i) == ' ') {
                    continue;
                } else if (exp.charAt(i) == '(') {
                    /**
                     * when the open bracket come means expression in expression
                     */
                    operator.push(exp.charAt(i));
                } else if (isDigit(exp.charAt(i))) {
                    /**
                     * when numeric value come, then join the string and make the number
                     */
                    int val = 0;
                    while (i < length && isDigit(exp.charAt(i))) {
                        val = val * 10 + (exp.charAt(i) - '0');
                        i++;
                    }
                    i--;
                    value.push(val);

                } else if (exp.charAt(i) == ')') {
                    /**
                     * when the arithmetic expression is end
                     */
                    while (operator.peek() != '(') {
                        int value2 = value.peek();
                        value.pop();

                        int value1 = value.peek();
                        value.pop();

                        char op = operator.peek();
                        operator.pop();

                        int output = perform(value1, value2, op);
                        value.push(output);
                    }
                    operator.pop();
                } else {
                    /**
                     * Solve the arithmetic expression.
                     * First solve those operation which precedence is high or equal.
                     */
                    while ( !operator.empty() && precedence(operator.peek()) >= precedence(exp.charAt(i))) {
                        int value2 = value.peek();
                        value.pop();

                        int value1 = value.peek();
                        value.pop();

                        char op = operator.peek();
                        operator.pop();

                        int output = perform(value1, value2, op);
                        value.push(output);
                    }
                    operator.push(exp.charAt(i));
                }
            }

            /**
             * when the operators are available in stack, then applied on operand
             * i.e. "1+(2*3)+5
             */
            while ( !operator.empty()) {
                int value2 = value.peek();
                value.pop();

                int value1 = value.peek();
                value.pop();

                char op = operator.peek();
                operator.pop();

                int output = perform(value1, value2, op);
                value.push(output);
            }
        }
        catch(Exception ex) {
            System.out.println("You give wrong expression ");
            return 0;
        }
        return value.peek() ;
    }

//    public static void main (String [] args) {
//        Bodmas bodmas = new Bodmas();
//        bodmas.bodmasSolver("(5+8)*2");
//    }

}
