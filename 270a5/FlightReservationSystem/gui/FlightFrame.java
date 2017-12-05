//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package gui;


import commands.*;
import javax.swing.*;

/**
 * This class is to display information about flight that exist in containers 
 * @author yugu
 *
 */
public class FlightFrame extends JFrame{
	
	public static final long serialVersionUID = 1;

	/**
	 * Initialize the flight frame with proper element such as title,size
	 * and location and visible
	 */
	public FlightFrame()
	{
		setTitle("Display Flight Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocation(0, 200);
		setVisible(true);
		/* add the panel into the flight frame */
		add(new FlightPanel());
	}
	
	
	private class FlightPanel extends JPanel{
		/** area for displaying flight information*/
		JTextArea text;
		/** pane for containing text area and scroll to view */
		JScrollPane pane;
		
		/**
		 * Initialize text with proper info and add it to pane
		 * set text area and scroll pane with proper size and location
		 */
		public FlightPanel(){
			add(new JLabel("Flight Information Listing"));
			
			text= new JTextArea(10,30);
			text.setText(new DisplayFlightsCommand().getFlightsString());
			text.setSize(300, 600);
			
			pane = new JScrollPane(text);
	        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        pane.setSize(10,10);
	        
	        add(pane);
			text.setEditable(false);
			text.setVisible(true);			
		}
		public static final long serialVersionUID = 1;
	}	
}
