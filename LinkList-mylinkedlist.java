package linklist;

public class mylinkedlist {
	public mynode startnode;
	public mynode endnode;
	
	public mylinkedlist(){
		startnode = null;
		endnode = null;
	}
	
	public void addelement(int value){
		mynode tempnode = new mynode(value);
		
		if (startnode == null)
			startnode = tempnode;
		
		if (endnode == null)
			endnode = tempnode;
		else {
			endnode.nextnode = tempnode;
			endnode = tempnode;
		}
	}
	
	public void printlist(){
		mynode tempnode = startnode;
		while (tempnode != null){
			System.out.print(tempnode.value+ " ");
			tempnode = tempnode.nextnode;
		}
	}
}
