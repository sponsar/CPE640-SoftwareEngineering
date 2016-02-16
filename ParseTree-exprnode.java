package test2;

public class exprnode {
	int val;
	char oper;
	exprnode leftchild;
	exprnode rightchild;
	exprnode parentnode;
	
	public exprnode(int value, char operat){
		val=value;
		oper=operat;
	}
	public int evalnode(){
		int eval=0;
		switch(oper){
		case '+':eval = leftchild.evalnode() + rightchild.evalnode();break;
		case '-':eval = leftchild.evalnode() - rightchild.evalnode();break;
		case '*':eval = leftchild.evalnode() * rightchild.evalnode();break;
		case '/':eval = leftchild.evalnode()/rightchild.evalnode();break;
		}
		return eval;
	}

}
