package HW2_2;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextArea;
*/
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class MyServer extends Thread{
	static boolean isClose=false,Send=false;
	static String msg;
	ServerSocket server;
	static JTextArea output;
	private static ArrayList<Socket> socketList;
	int port;
   public MyServer(int port,JTextArea output){
	   this.output=output;
	   this.port=port;
	   socketList=new ArrayList<Socket>();
     try {
		server = new ServerSocket(port);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		System.out.println("Sorry invalid port number.");
	}
      }
   public void run(){
	   Socket socket = null;
	      while (!isClose) {
	          try{
	           server.setSoTimeout(100);
	           socket= server.accept();
	           socketList.add(socket);
	           //Writer writer = new OutputStreamWriter(socket.getOutputStream());
	           //writer.write("Hello! How are you doing?\n");
	           //writer.flush();
	           //writer.close();
	           new Thread(new Task(socket)).start();
	           new Thread(new Tsk(socket)).start();
              }
	          catch(Exception e){
	        	  //e.printStackTrace();
	        	  //System.out.println("Miss connect");
 	          }
   }
	      	try {
				server.close();
				System.out.println("Server Closed. Port No."+port);
				isClose=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Fail to close server.");
			}
   }
   static class Tsk implements Runnable{
	   private Socket socket;
	   private Writer writer;
	   public Tsk(Socket socket) throws IOException{
		   this.socket=socket;
		   writer = new OutputStreamWriter(socket.getOutputStream());
	   }
	   public void run(){
		   while(!isClose&&!socket.isClosed()){
			   if(socket.isClosed())
				   break;
		       if(Send){
				   try {
						writer.write(msg);
						writer.write("eof\n");
			            writer.flush();
			            //writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
		          	
		              Send=false;
		         }
	   }
		   try {
			synchronized (socket) {
			if(!socket.isClosed()){
			writer.close();
			socket.close();
			System.out.println("Clost socket in Tsk");
	        }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to close socket");
		}
	   }
   }
   static class Task implements Runnable {
 
      private Socket socket;
      private BufferedReader br;
      public Task(Socket socket) throws IOException {
         this.socket = socket;
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      }
      
      public void run() {
 while(!isClose&&!socket.isClosed()){
	 if(socket.isClosed())
		 break;
         try {
            handleSocket(br);
         } catch (Exception e) {
            e.printStackTrace();
         }
 }
    	  try {
    		synchronized (socket) {
    			if(!socket.isClosed()){
    				br.close();
    				socket.close();
    				System.out.println("Close socket in task");
    			}
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to close socket");
		}
    	  }
      
      private void handleSocket(BufferedReader br) throws Exception {
         
         StringBuilder sb = new StringBuilder();
         String temp;

         int index;
         while ((temp=br.readLine()) != null&&!socket.isClosed()) {
        	 
            if ((index = temp.indexOf("eof")) != -1) {
            	sb.append(temp.substring(0, index));
            	break;
            }
            sb.append(temp);
         }
         if(sb.length()>0){
         System.out.println("Client"+(socketList.indexOf(socket)+1)+":" + sb); 
         output.setText(output.getText()+"\nClient"+(socketList.indexOf(socket)+1)+":"+sb.toString());
         }}
      
   }
   public void Send(String msg){
		this.msg=msg;
		Send=true;
		Show("Server:"+msg);
		output.setText(output.getText()+"\nMe:"+msg);
   }
   public void Show(String msg){
	   System.out.println(msg);
   }
   public void Close(){
	   isClose=true;
	   System.out.println("Gonna disconnect..");
   }
}

