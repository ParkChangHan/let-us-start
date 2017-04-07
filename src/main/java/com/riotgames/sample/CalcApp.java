package com.riotgames.sample;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 * Calculator application
 */
public class CalcApp {
    public double calc(String[] tokens) {
    	Deque<String> st = new ArrayDeque<>();
    	Deque<String> st1 = new ArrayDeque<>();
    	boolean p = false;

   	for(int i = 0 ; i < tokens.length; i++){
    		st.push(tokens[i]);
    		if("(".equals(st.peek())){
    			
    			st.pop();
    			i = bracket(st,tokens,i+1);
    		}
    		if("x".equals(st.peek()) || "/".equals(st.peek())){
    			st1.push(st.pop());
    			p = true;
    			continue;
    		}
    		if(p){
    			st.push(priority(st.pop(),st1.pop(),st.pop()));
    			p = false;
    		}
    	}
    	for(int i = 0 ; i < st.size();){
    		st1.push(st.pop());
    	}
    	
    	for(int i = 0 ; i < st1.size()-1;){
    		st1.push(priority(st1.pop(),st1.pop(),st1.pop()));
    	}
    	return Double.parseDouble(st1.pop());

    }
    
    //java com.riotgames.sample.CalcApp '(' 1 + 2 ')' + '(' 3 - 2 ')' x '(' 3 + 4 ')' - '(' 4 + 5 x '(' 5 + 6 ')' ')' + 4
    public String priority(String first, String op, String second){
    	Operator operator = Operator.findOperator(op);
    	double firstOperand = Double.parseDouble(first);
    	double secondOperand = Double.parseDouble(second);
    	return String.valueOf(operator.evaluate(firstOperand, secondOperand));
    }
    
    public int bracket(Deque<String> st, String[] tokens, int index){
    	int j;
    	int count = 0;
    	for(j = index; j < tokens.length; j++){
    		st.push(tokens[j]);
    		if(")".equals(tokens[j])){
				st.pop();
				String[] temp_tokens = new String[count];
				for(int i = count-1 ; i >= 0; i--){
					temp_tokens[i]=st.pop();
				}
				st.push(String.valueOf(calc(temp_tokens)));
				break;
			}
			if("(".equals(st.peek())){
				st.pop();
				j = bracket(st, tokens, j+1);
			}
			count++;
		}
	return j;
    }
	
	public static void main( String[] args ) {
        final CalcApp app = new CalcApp();
        final StringBuilder outputs = new StringBuilder();
        Arrays.asList(args).forEach(value -> outputs.append(value + " "));
        System.out.print( "Addition of values: " + outputs + " = ");
        System.out.println(app.calc(args));
    }
}
