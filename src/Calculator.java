import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class Calculator {
	String[] tokensKind = {"\\+", "-", "\\*", "/", "(0|([1-9]\\d*))(\\.\\d+)?", "\\(", "\\)"};
	ArrayList<String> tokens = new ArrayList<>();
	int pointer = 0;
	private void consumeToken(){
		pointer ++;
	}
	private String nextToken(){
		if (pointer < 0 || pointer >= tokens.size()){
			return null;
		}
		return tokens.get(pointer);
	}
	
	private Expression parseExp1(){
		// Exp2
		// Exp2 + Exp1
		// Exp2 - Exp1
		Expression expLeft = parseExp2();
		Expression expRight;
		while(nextToken() != null && (nextToken().equals("+") || nextToken().equals("-"))){
			String type = nextToken();
			consumeToken();
			expRight = parseExp2();
			Expression[] args = {expLeft, expRight};
			expLeft = new FuncExpression(type, args);
		}
		return expLeft;
	}
	private Expression parseExp2(){
		// - Exp2
		// Exp3
		// Exp3 * Exp2
		// Exp3 / Exp2
		Expression expLeft = null;
		if (nextToken().equals("-")){
			consumeToken();
			Expression exp1 = new ConstExpression("-1");
			Expression[] args = { exp1, parseExp2() };
			expLeft = new FuncExpression("*",  args);
		}else{
			expLeft = parseExp3();
		}
		Expression expRight;
		while(nextToken() != null && (nextToken().equals("*") || nextToken().equals("/"))){
			String type = nextToken();
			consumeToken();
			expRight = parseExp3();
			Expression[] args = {expLeft, expRight};
			expLeft = new FuncExpression(type, args);
		}
		return expLeft;
	}
	private Expression parseExp3(){
		// Num
		// ( Exp1 )
		Expression exp = null;
		if (nextToken().equals("(")) {
			consumeToken();
			exp = parseExp1();
			// ")"を消費
			if (!nextToken().equals(")")){
				throw new IllegalArgumentException();
			}
			consumeToken();
		}else {
			exp = parseNum();
		}
		return exp;
	}
	private Expression parseNum(){
		Expression exp = new ConstExpression(nextToken());
		consumeToken();
		return exp;
	}
	private boolean splitTokens(String line){
		String patterns = "";
		for(int i = 0; i < tokensKind.length; i ++){
			if (i > 0){
				patterns += "|";
			}
			patterns += "(" + tokensKind[i] + ")";
		}
		Pattern p = Pattern.compile(patterns);
		Matcher m = p.matcher(line);
		while(m.find()){
			String token = m.group();
			tokens.add(token);
			line = line.substring(token.length());
		}
		return line.equals("");
	}
	
	public double getAnswer(String line) throws IllegalArgumentException{
		if (!splitTokens(line)){
			throw new IllegalArgumentException("couldn't calculate");
		}
		
		/* デバッグ用
		for(int i = 0; i < tokens.size(); i++){
			System.out.println(tokens.get(i));
		}
		*/ 
		return parseExp1().calc();
	}
}
