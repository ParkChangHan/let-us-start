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
    	boolean p = false;
    	for(int i = 0 ; i < tokens.length; i++){
    		st.push(tokens[i]);
    		
    		if(st.peek().equals("(")){
    			st.pop();
    			for(int j = i; j < tokens.length; j++){
    				if(tokens[j].equals(")")){
    					String[] temp_tokens = new String[j-i-1];
    					System.arraycopy(tokens, i+1, temp_tokens, 0, j-i-1);
    					st.push(String.valueOf(calc(temp_tokens)));
    					i = j;
    					break;
    				}
    			}
    		}
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
    	for(int i = 0 ; i < st.size();){
    		st1.push(st.pop());
    	}
    	
    	for(int i = 0 ; i < st1.size()-1;){
    		st1.push(priority((String)st1.pop(),(String)st1.pop(),(String)st1.pop()));
    	}

    	return Double.parseDouble((String)st1.pop());  
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
