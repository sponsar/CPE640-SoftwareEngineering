package Thread;

import java.util.ArrayList;

public class threaddemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		printerclass myprinter = new printerclass();
		ArrayList<mythreadclass> mythreadlist;
		mythreadlist = new ArrayList<mythreadclass>();
		
		for(int i=0; i<100; i++){
			mythreadlist.add(new mythreadclass("*", myprinter));
			mythreadlist.get(i).start();
		}
		*/
		printerclass myprinter = new printerclass();
		mythreadclass thread1, thread2, thread3;
		thread1 = new mythreadclass("*", myprinter);
		thread2 = new mythreadclass("+", myprinter);
		thread3 = new mythreadclass("-", myprinter);
		
		thread1.start();
		thread2.start();
		thread3.start();
		
	}

}
