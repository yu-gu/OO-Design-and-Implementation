//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import commands.*;
import java.awt.event.KeyEvent;


/**
 * This class is to add a passenger's information to the frame
 * and save the passenger in the system
 * @author yugu
 *
 */
public class addPassengerFrame extends JFrame 
{
	public static final long serialVersionUID = 1;

	/** Initialize the frame with proper size 
	 * and location and set it visible. 
	 */
	public addPassengerFrame()
	{
		setTitle("Add Passenger Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocation(0, 200);
		setVisible(true);
		add(new PassengerPanel());
	}


	/** The panel to enter the information for a new passenger,including the name and phone number */
	private class PassengerPanel extends JPanel
	{


		/** The field for the entry of passenger name. */
		 JTextField nameField;
		
		/** The area for entering phone number of passenger*/
		 JTextField addressField;
		
		public static final long serialVersionUID = 1;

				
		/**
		 * To initialize the passenger panel being added to frame
		 * in which the text fields, buttons and their corresponding listeners
		 * are all set
		 */
		public PassengerPanel()
		{
		 setLayout(new GridLayout(10,1));
         JPanel passengerPanel = new JPanel();
         add(passengerPanel, BorderLayout.PAGE_START);
         passengerPanel.add(new JLabel("Name"));
         nameField = new JTextField(15);
         passengerPanel.add(nameField);
         nameField.addKeyListener(new KeyAdapter(){
        	 /*The enter key listener*/
 			public void keyPressed(KeyEvent e) 
 			{
 				if (e.getKeyCode()==KeyEvent.VK_ENTER){
 					if (nameField.getText().isEmpty() || addressField.getText().isEmpty() )
 					{
 						JOptionPane.showMessageDialog(getTopLevelAncestor(), 
 						"all two information are required", "Error", 
 						JOptionPane.ERROR_MESSAGE);
 					}
 					else{
 						String name = (nameField.getText());
 	 					String address = (addressField.getText());
 						AddPassengerCommand cmd = new AddPassengerCommand();
 	 					cmd.addPassenger(name, address);
 	 					nameField.setText("");
 	 					addressField.setText("");
 					}
 					
 				}	 
 			}
         });
         
         JPanel addressPanel = new JPanel();
         add(addressPanel, BorderLayout.PAGE_START);
         addressPanel.add(new JLabel("Address"));
         addressField = new JTextField(15);
         addressPanel.add(addressField);  
         addressField.addKeyListener(new KeyAdapter(){
  			public void keyPressed(KeyEvent e) 
  			{
  				if (e.getKeyCode()==KeyEvent.VK_ENTER){
  					if (nameField.getText().isEmpty() || addressField.getText().isEmpty() )
  					{
  						JOptionPane.showMessageDialog(getTopLevelAncestor(), 
  						"all two information are required", "Error", 
  						JOptionPane.ERROR_MESSAGE);
  					}
  					else{
  					String name = (nameField.getText());
  	  				String address = (addressField.getText());
  					AddPassengerCommand cmd = new AddPassengerCommand();
  					cmd.addPassenger(name, address);
  					nameField.setText("");
 					addressField.setText("");
  					}
  				}	 
  		   }
         });
         JPanel submitPanel = new JPanel();
         add(submitPanel, BorderLayout.PAGE_END);
         JButton submitButton = new JButton("Submit");
         submitPanel.add(submitButton);
         submitButton.addActionListener(new
         ActionListener()
         {
        	/*Listener for the submit button*/ 
         	public void actionPerformed (ActionEvent event)
         	{
         		/* check name or address is null exception, pop up error if null*/
         		if (nameField.getText().isEmpty() || addressField.getText().isEmpty() )
				{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), 
					"all two information are required", "Error", 
					JOptionPane.ERROR_MESSAGE);
				}
         		else{	
					AddPassengerCommand cmd = new AddPassengerCommand();
					cmd.addPassenger(nameField.getText(), addressField.getText());
					nameField.setText("");
 					addressField.setText("");
					if (!cmd.wasSuccessful())
					{
						JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
					}
         		}
  			}
         	});
		}
}				
}
