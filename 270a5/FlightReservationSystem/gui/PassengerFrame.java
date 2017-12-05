//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package gui;

import commands.*;
import javax.swing.*;

/**
 * The class is to display information about Passengers that exist in containers
 * including the name and address of the passenger
 * 
 * @author yugu
 *
 */
public class PassengerFrame extends JFrame{
	
	public static final long serialVersionUID = 1;

	public PassengerFrame()
	{
		setTitle("Display Passenger Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocation(0, 200);
		setVisible(true);
		add(new PassengerPanel());
	}
	
	/**
	 * This class it to construct a panel with proper components and 
	 * be added to the frame, the components of panel are used for
	 * displaying passenger info including name and address of passenger
	 * 
	 * @author yugu
	 *
	 */
	private class PassengerPanel extends JPanel{
		
		/** The area to contain passenger info */
		JTextArea text;
		
		/** The pane to contain text area and scroll */
		JScrollPane pane;
		
		/**
		 * Initialize the panel with proper label set and have text area
		 * and scroll pane set
		 */
		public PassengerPanel(){
			add(new JLabel("Passenger Information"));
			
			
			text= new JTextArea(10,30);
			text.setText(new DisplayPassengersCommand().getPassengersString());
			text.setSize(300, 400);
			
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
