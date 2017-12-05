//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package startup;

import javax.swing.*;
import gui.FlightFrame;
import gui.HandleFlightFrame;
import gui.PassengerFrame;
import gui.addFlightFrame;
import gui.addPassengerFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * The main frame that list all the six buttons(options)
 * and correspond to each of the operations
 * @author yugu
 *
 */
public class WelcomeFrame extends JFrame 
{
	public static final long serialVersionUID = 1;
	/** The main frame*/
	private JFrame frame;
		
	/** Initialize the frame and add a WelcomePanel to it.  */
	public WelcomeFrame()
	{
		
		frame = new JFrame();
		frame.setTitle("Welcome to Flight Reservation System Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLocation(400, 200);
		frame.add(new WelcomePanel());
		frame.setVisible(true);
	}
	
	/** A panel with a welcome message, and a field to enter the size of the kennel.  
	 *  Entering a size will create the kennel, and open a window for the user
	 *  to enter various operations. */
	private class WelcomePanel extends JPanel
	{		
		/*
		 * The button one to handle add Flight
		 */
		private JButton Button1;
		
		/*
		 * The button two to handle add Passenger
		 */
		private JButton Button2;

		/*
		 * The button three to handle display flights
		 */
		private JButton Button3;

		/*
		 * The button four to handle display passengers
		 */
		private JButton Button4;
		
		/*
		 * The button five
		 */
		private JButton Button5;
		
		/*
		 * The label for a prompt for flight number input
		 */
		private JLabel Label;
		
		/*
		 * The text field for flight number input 
		 */
		private JTextField Option6;
		
		public static final long serialVersionUID = 1;

		public WelcomePanel()
		{
			setLayout(new GridLayout(10,1));
			
			Button1 = new JButton();
			Button1.setSize(100, 100);
			Button1.setText("1.add Flight");
		
			Button1.addActionListener(new Button1Listener());
			add(Button1);
			
			Button2 = new JButton();
			Button2.setSize(100, 100);
			Button2.setText("2.add Passenger");
			Button2.addActionListener(new Button2Listener());
			add(Button2);			
			
			Button3 = new JButton();
			Button3.setSize(100, 100);
			Button3.setText("3.display Flight");
			Button3.addActionListener(new Button3Listener());

			add(Button3);			
			
			Button4 = new JButton();
			Button4.setSize(100, 100);
			Button4.setText("4.display Passenger");
			Button4.addActionListener(new Button4Listener());
			add(Button4);
			
			
			Button5 = new JButton();
			Button5.setSize(100, 100);
			Button5.setText("5.Quit");
			Button5.addActionListener(new CloseListener());
			add(Button5);

			Label = new JLabel();
			Label.setText("Enter flight number:");
			Label.setSize(100, 100);
			Label.setVisible(true);
			add(Label);
			
			
			Option6 = new JTextField(15);
			Option6.addKeyListener(new KeyAdapter(){
	  			public void keyPressed(KeyEvent e) 
	  			{
	  				if (e.getKeyCode()==KeyEvent.VK_ENTER){
	  					try{
	  						Integer.parseInt(Option6.getText());
	  						int Flightnum = Integer.parseInt(Option6.getText());
		  					new HandleFlightFrame(Flightnum).setVisible(true);
	  					}
	  					catch(NumberFormatException e1){
	  						JOptionPane.showMessageDialog(getTopLevelAncestor(), 
	  								"Please enter in correct format", "Error", 
	  								JOptionPane.ERROR_MESSAGE);
	  					}
	  				}	 
	  			}
	          });
			add(Option6);
		}
		/**
		 * First listener for button1 to respond to add flight command
		 * @author yugu
		 *
		 */
		private class Button1Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new addFlightFrame();
			}
		}
		/**
		 * Second listener for button2 to respond to add passenger command
		 * @author yugu
		 *
		 */
		private class Button2Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new addPassengerFrame();
			}
		}
		/**
		 * Third listener for button3 to display existing flights in system
		 * @author yugu
		 *
		 */
		private class Button3Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new FlightFrame();
			}
		}
		/**
		 * Fourth listener for button4 to display passengers in system
		 * @author yugu
		 *
		 */
		private class Button4Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				new PassengerFrame();
			}
		}
		/**
		 * Fifth listener for quit button, terminate the program when pressed
		 * 
		 * @author yugu
		 *
		 */
		private class CloseListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
		        System.exit(0);
			}
		}
		
	}
	
	/**
	 * The main function initialize the main frame object
	 * @param args
	 */
	public static void main(String[] args){
		new WelcomeFrame();
	}	

}