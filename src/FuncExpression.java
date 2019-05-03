import java.util.ArrayList;

public class FuncExpression extends Expression{
	private String type;
	private Expression[] args;
	FuncExpression(String t, Expression[] a){
		type = t;
		args = a;
	}
	public double calc(){
		switch(type){
		case("+"):
			return args[0].calc() + args[1].calc();
		case("-"):
			return args[0].calc() - args[1].calc();
		case("*"):
			return args[0].calc() * args[1].calc();
		case("/"):
			return args[0].calc() / args[1].calc();
		default:
			return 0;
		}
	}
}
