package Thread;

public class mythreadclass extends Thread{

	printerclass myprinter;
	String	myname;
	public mythreadclass(String tname, printerclass tprinter){
		myname = tname;
		myprinter = tprinter;
	}
	
	public void run(){
		for (int i=0; i<10; i++)
			myprinter.incrementCount();
	}
}
