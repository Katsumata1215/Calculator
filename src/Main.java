import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);
		while(true){
			
			System.out.println("input expression");
			System.out.print(">>");
			String line = sc.nextLine();
			if (line.equals("")){
				System.out.println("exit...");
				break;
			}
			try{
				Calculator c = new Calculator();
				System.out.println("result : " + c.getAnswer(line));
			} catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
		}
		sc.close();
	}

}