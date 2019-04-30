
public class ConstExpression extends Expression{
	private String value;
	
	ConstExpression(String v){
		value = v;
	}
	
	public double calc(){
		return Double.parseDouble(value);
	}
}
