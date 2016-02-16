package test2;//这是hw1第四题老师给的方法
import java.util.Scanner;

public class ParseTreeDemo {
	static String expr;
	static exprnode treeroot;
	
	public static boolean isoper(char c){
		if(c=='+'||c=='-'||c=='*'||c=='/')
				return true;
		return false;
	}
	
	public static exprnode buildParseTree(){
		exprnode currnode=new exprnode(0,'n');
		exprnode parentnode=new exprnode(0,')');
		int i=0;
		
		
		while(i<expr.length()){
			if(expr.charAt(i)=='('){
				currnode.leftchild = new exprnode(0,'n');
				parentnode = currnode;
				currnode = currnode.leftchild;
				currnode.parentnode = parentnode;
			}
			if(expr.charAt(i)==')'){
				if(currnode.parentnode!=null)
					currnode = currnode.parentnode;
			}
			if(isoper(expr.charAt(i))){
				currnode.oper = expr.charAt(i);
				currnode.rightchild = new exprnode(0,'n');
				parentnode = currnode;
				currnode = currnode.rightchild;
				currnode.parentnode = parentnode;
			}
			else {
				currnode.val = Character.getNumericValue(expr.charAt(i));
				currnode = currnode.parentnode;
			}
			i++;
		}
		
		
		return currnode;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner myscanner = new Scanner(System.in);
		System.out.println("Enter Expression: ");
		expr = myscanner.nextLine();
		System.out.println(expr);
		treeroot = buildParseTree();
		System.out.println(treeroot.evalnode());
		
	}

}
