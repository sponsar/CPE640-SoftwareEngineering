package linklist;

public class testclassdemo {

		static mynode node;
		static mylinkedlist myll;
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			myll = new mylinkedlist();
			
			myll.addelement(1);
			myll.addelement(10);
			myll.addelement(11);
			myll.addelement(12);
			myll.addelement(13);
			myll.printlist();
			
			//String	s = new String();
			//s = node.toString();
			//System.out.println(s);
			
		}
}
