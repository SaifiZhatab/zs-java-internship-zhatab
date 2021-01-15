package com.zs.exc1;

/*
implement bodmas
 */

import java.util.Stack;

import static java.lang.Character.isDigit;

public class Bodmas {

    // This function return two number sum
    public int sum(int a,int b){
        return (a+b);
    }

    // This function return two number subtract
    public int subtract(int a,int b){
        return (a-b);
    }

    // This function return two number multiply
    public int multiply(int a,int b){
        return (a*b);
    }

    // This function return two number divide
    public int divide(int a,int b){
        return (a/b);
    }

    // This function return power
    public int power(int a,int b){
        return (int)Math.pow(a,b);
    }

    // This function return the precedence of the operaator
    public int precedence(char op){
        switch(op){
            case '-' : return 1;
            case '+' : return 1;
            case '*' : return 2;
            case '/' : return 2;
            case '^' : return 3;
            default  : return 0;
        }
    }

    // This function perform the arthmatic operation on two number
    public int perform(int a,int b, char op) {
        switch(op) {
            case '+' : return sum(a,b);
            case '-' : return subtract(a,b);
            case '*' : return multiply(a,b);
            case '/' : return divide(a,b);
            case '^' : return power(a,b);
        }
        return 0;
    }

    // This function solve the bodmas expression
    public void bodmasSolver(String exp) {
        int length = exp.length();
        int ans=0;
        Stack<Integer> value = new Stack<Integer>();
        Stack<Character> operator = new Stack<Character>();

        // use try-catch in case of any exception occur
        try {
            for (int i = 0; i < length; i++) {
                if (exp.charAt(i) == ' ') {
                    continue;
                } else if (exp.charAt(i) == '(') {
                    operator.push(exp.charAt(i));
                } else if (isDigit(exp.charAt(i))) {            // when it is numeric value
                    int val = 0;
                    while (i < length && isDigit(exp.charAt(i))) {
                        val = val * 10 + (exp.charAt(i) - '0');
                        i++;
                    }
                    i--;
                    value.push(val);

                } else if (exp.charAt(i) == ')') {              // when the bracket expression will complete
                    while (operator.peek() != ')') {
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
                    // solve the arthmatic expression
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

            // in case of any operator remain in stack
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
            return ;
        }
        System.out.println(value.peek() );
    }
}
