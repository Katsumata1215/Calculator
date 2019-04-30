import java.util.ArrayList;

public class FuncExpression extends Expression{
	private String type;
	private ArrayList<Expression> args;
	FuncExpression(String t, ArrayList<Expression> a){
		type = t;
		args = a;
	}
	public double calc(){
		switch(type){
		case("+"):
			return args.get(0).calc() + args.get(1).calc();
		case("-"):
			return args.get(0).calc() - args.get(1).calc();
		case("*"):
			return args.get(0).calc() * args.get(1).calc();
		case("/"):
			return args.get(0).calc() / args.get(1).calc();
		default:
			return 0;
		}
	}
}
