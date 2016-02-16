package Thread;

public class printerclass {

	public int count;
	public printerclass(){
		count=0;
	}
	
	public synchronized void incrementCount(){
		for (int i=0; i<3; i++){
			try { 
                Thread.sleep((long)(Math.random() * 10)); 
            } catch (InterruptedException ie) { }
            
			count++;
			System.out.println(count);
	}
}
}
