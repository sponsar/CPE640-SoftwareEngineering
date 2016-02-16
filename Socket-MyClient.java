package HW2_2;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextArea;
*/
import java.io.*;
import java.net.*;
import javax.swing.*;

public class MyClient {
private static boolean Send;
private static boolean isClose=false;
private static String msg;
private static Socket client;
private Writer writer;
private BufferedReader br;
private StringBuffer sb;
private static JTextArea output;
   public MyClient(String host,int port,JTextArea output) {
	   this.output=output;
     try {
		client = new Socket(host, port);
		writer = new OutputStreamWriter(client.getOutputStream());
	    br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    sb = new StringBuffer();
	    new Thread(new Receivetask(br,sb)).start();
	    new Thread(new Sendtask(writer)).start();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		System.out.println("Unable to connect the host.");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
     //String temp;
     //writer.write("Hello Server. "+readLine+" "+readLines+" ");
    // writer.write("Hello Server. "+String.valueOf(Math.random())+" "+"11"+" ");
     //writer.write("a\n");
     //writer.write("e\n");
     //writer.write("i\n");
     //writer.write("eof\n");
     //writer.flush();
      /*int index;
      while ((temp=br.readLine()) != null) {
         if ((index = temp.indexOf("eof")) != -1) {
            sb.append(temp.substring(0, index));
            //include 0 and exclude index
            break;
         }
         sb.append(temp);
      }
      System.out.println("Server: " + sb);
      writer.close();
      br.close();
      client.close();*/
   }
   static class Sendtask implements Runnable{
	   private Writer writer;
	   public Sendtask(Writer writer){
		   this.writer=writer;
	   }
	   public void run(){
		   while(!isClose&&!client.isClosed()){
			   if(client.isClosed())
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
			synchronized (client) {
			if(!client.isClosed()){
			writer.close();
			client.close();
			System.out.println("Clost socket in write");}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to close socket");
		}
	   }
   }
   static class Receivetask implements Runnable{
	   private BufferedReader br;
	   private StringBuffer sb;
	   public Receivetask(BufferedReader br,StringBuffer sb){
		   this.br=br;
		   this.sb=sb;
	   }
	   public void run(){
		   while(!client.isClosed()&&!isClose){
			   if(client.isClosed())
					 break;
			   int index;
			   String temp;
			   try{
				   
			      while ((temp=br.readLine()) != null&&!client.isClosed()) {
			         if ((index = temp.indexOf("eof")) != -1) {
			            sb.append(temp.substring(0, index));
			            //include 0 and exclude index
			            break;
			         }
			         sb.append(temp);
			      }}
			   catch(Exception e){
				   e.printStackTrace();
			   }
			      if(sb.length()>0){
			      System.out.println("Server:" + sb); 
			      output.setText(output.getText()+"\nServer:"+sb.toString());}
			      sb.delete(0, sb.length());
		   }
	    	  try {
	      		synchronized (client) {
	      			if(!client.isClosed()){
	      				br.close();
	      				client.close();
	      				System.out.println("Close socket in task");}
	  			}  
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  			System.out.println("Unable to close socket");
	  		}
	   }
   }
   public void Send(String msg){
		this.msg=msg;
		Send=true;
		Show("Client:"+msg);
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
