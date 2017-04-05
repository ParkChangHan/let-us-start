package com.riotgames.sample;

import java.util.Arrays;
import java.util.Stack;
/**
 * Calculator application
 */
public class CalcApp {
    public double calc(String[] tokens) {
    	Stack st = new Stack();
    	Stack st1 = new Stack();
    	double sum = 0;
    	boolean p = false;;
    	for(int i = 0 ; i < tokens.length; i++){
    		st.push(tokens[i]);
    		if(st.peek().equals("x") || st.peek().equals("/")){
    			st1.push(st.pop());
    			p = true;
    			continue;
    		}
    		if(p){
    			st.push(priority((String)st.pop(),(String)st1.pop(),(String)st.pop()));
    			p = false;
    		}
    	}
    	for(int i = 0 ; i < st.size()-1; i++){
    		st.push(priority((String)st.pop(),(String)st.pop(),(String)st.pop()));
    	}
    	return Double.parseDouble((String)st.pop());

    }
    public String priority(String first, String op, String second){
    	Operator operator = Operator.findOperator(op);
    	double firstOperand = Double.parseDouble(first);
    	double secondOperand = Double.parseDouble(second);
    	return String.valueOf(operator.evaluate(firstOperand, secondOperand));
    }

    public static void main( String[] args ) {
        final CalcApp app = new CalcApp();
        final StringBuilder outputs = new StringBuilder();
        Arrays.asList(args).forEach(value -> outputs.append(value + " "));
        System.out.print( "Addition of values: " + outputs + " = ");
        System.out.println(app.calc(args));
    }
}
