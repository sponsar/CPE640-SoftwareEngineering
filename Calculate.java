package HW1_4;


import java.util.ArrayList;
import java.util.Scanner;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;


public class calculate {
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
		 public static void main(String[] args) throws Exception{
			 String combine;
			 System.out.println("enter your input: ");
			 Scanner scanner = new Scanner(System.in);
			 String exp[] = scanner.nextLine().split("");
			 
			 for(int i = 0;i<exp.length;i++)
			 {
				 String s = exp[i];
				 
				 if(!s.equals("(")&&!s.equals(")")&&!s.equals("+")&&!s.equals("-")&&!s.equals("*")
						 &&!s.equals("/"))
				 {
					 System.out.println(s+" = ");
					 exp[i] = scanner.nextLine();
					 if(isNumeric(exp[i])==false)
					 {
						 System.out.print("input value not available");
						 return;
					 }
				 }
				 }
			 combine = exp[0];
			 for(int i = 1 ; i<exp.length;i++){
				 combine = combine +exp[i];
			 }
			    ScriptEngineManager mgr = new ScriptEngineManager();
			    ScriptEngine engine = mgr.getEngineByName("JavaScript");
			    //String foo = combine;
			    System.out.println("The answer is "+engine.eval(combine));
			    } 
	
}
