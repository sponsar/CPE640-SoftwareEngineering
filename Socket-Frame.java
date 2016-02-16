package HW2_2;
/*
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Frame extends JFrame{
	private MyServer server;
	private MyClient client;
	private boolean isServer;
	private JLabel status;
	private String ip;
	public void setStatus(String str){
		status.setText(str);
	}
    public static void main(String[] args){
    	new Frame();
    }
	public Frame(){
		super("Socket programming");
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Container c=getContentPane();
		final Checkbox join;
		final Checkbox host;
		final CheckboxGroup g=new CheckboxGroup();
		JButton butcs=new JButton("Connect/Start");
		JButton butds=new JButton("Disconnect");
		status=new JLabel("Status: Disconnected");
		status.setBackground(Color.white);
		status.setText("Status: Disconnected");
		final JTextArea output=new JTextArea("",11,42);
		output.setBackground(Color.white);
		output.setEditable(false);
		JScrollPane scroll=new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		join=new Checkbox("Join the Chat",g,false);
		host=new Checkbox("Host the Chat",g,true);
		final JTextField input=new JTextField(ip,20);
		input.setEditable(false);
		final JTextField inputt=new JTextField("Input port Number",10);
		inputt.setToolTipText("Input number");
		final JTextField message=new JTextField("Input text",43);
		message.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()=='\n'){
				if(isServer)
					server.Send(message.getText());
				else
					client.Send(message.getText());
				message.setText("");
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		message.setMinimumSize(new Dimension(600,20));
		message.setToolTipText("Input whatever you want to say");
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout(0));
		JPanel lowpanel=new JPanel();
		lowpanel.setLayout(new FlowLayout(0));
		panel.add(host);
		panel.add(join);
		panel.add(input);
		panel.add(inputt);
		panel.add(butcs);
		panel.add(butds);
		panel.add(status);
		lowpanel.add(scroll);
		lowpanel.add(message);
		c.setLayout(new GridLayout(2,1));
		c.add(panel);
		c.add(lowpanel);
		setVisible(true);
		setSize(500, 510);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		join.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				input.setEditable(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		host.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				input.setText(ip);
				input.setEditable(false);
			}@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		butds.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setStatus("Status: Disconnected");
				if(isServer){
					server.Close();
				}
				else{
					client.Close();
				}	
			}
		});
		butcs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(g.getSelectedCheckbox()==join){
					System.out.println("Join");
					try{
						String host=input.getText();
						int port=Integer.parseInt(inputt.getText());
						client=new MyClient(host,port,output);
						isServer=false;
						setStatus("Status: Connected");
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				if(g.getSelectedCheckbox()==host){
					System.out.println("Server Start");
					try{
						int port=Integer.parseInt(inputt.getText());
						server=new MyServer(port,output);
						Thread t=new Thread(server);
						t.start();
						isServer=true;
						setStatus("Status: Connected");
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});

	}

}
