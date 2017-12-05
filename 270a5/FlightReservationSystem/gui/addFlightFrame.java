//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import commands.AddFlightCommand;
/**
 * This class is to represent the function of first button and add the flight into the
 * system when first button is pressed including the flight number, width,capacity
 * @author yugu
 *
 */
public class addFlightFrame extends JFrame 
{
	/** Initialize the frame and add a panel to obtain the information
	 * for the new owner.  */
	public addFlightFrame()
	{
		setTitle("Add Flight Information");
		setSize(400, 300);
		setLocation(0, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addFlightPanel  panel = new addFlightPanel();
		add(panel);
		setVisible(true);
	}

	public static final long serialVersionUID = 1;
	

	/**
	 * The panel to obtain the flight number, width, capacity of flight.
	 * A button is used to submit the information. If the flight information 
	 * was created successful, the frame will be automatically disappear 
	 * so as to return to the main menu  
	 * 
	 * @author yugu
	 *
	 */
	private class addFlightPanel extends JPanel
	{
		/** The text field to enter the flight number */
		JTextField numberField;
		
		/** The text field to enter the width of the flight */
		JTextField widthField;
		
		/** The text field to enter the capacity of the flight */
		JTextField capacityField;

		
		/** Initialize the panel with the fields and the submit button. 
		 *  Add a listener for the button, that creates the new flight
		 *  with flight number, width, capacity all set */
		public addFlightPanel()
		{
			setLayout(new GridLayout(10,1));
			/*build the name panel and name text field*/
			JPanel namePanel = new JPanel();
			namePanel.add(new JLabel("number "));
			numberField = new JTextField(20);
			namePanel.add(numberField);
			add(namePanel, BorderLayout.PAGE_START);
			/*build the width panel and width text field*/
			JPanel widthPanel = new JPanel();
			widthPanel.add(new JLabel("width "));
			widthField = new JTextField(20);
			widthPanel.add(widthField);
			add(widthPanel, BorderLayout.CENTER);
			/*build the capacity panel and capacity text field*/
			JPanel capcityPanel = new JPanel();
			capcityPanel.add(new JLabel("capacity "));
			capacityField = new JTextField(20);
			capcityPanel.add(capacityField);
			add(capcityPanel, BorderLayout.PAGE_END);
			/*The submit button*/
			JPanel submitPanel = new JPanel();
			JButton submitButton = new JButton("Submit");
			submitPanel.add(submitButton);
			add(submitPanel, BorderLayout.PAGE_END);
			submitButton.addActionListener(new SubmitListener());
	        
		}
		
		
		/** The listener to the submit button of the panel. */
		private class SubmitListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{
				/* Prevent either one of the fields are empty
				 * and also prevent if the input is given 
				 * with wrong number format 
				 */
				if (numberField.getText().isEmpty() || widthField.getText().isEmpty() || capacityField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), 
					"all three information are required.", "Error", 
					JOptionPane.ERROR_MESSAGE);
					new addFlightPanel().add(new JOptionPane());
				}
				else{	
				try{
					/* check if input format was correct*/
					Integer.parseInt(numberField.getText());
					Integer.parseInt(widthField.getText());
					Integer.parseInt(capacityField.getText());
					int number = Integer.parseInt(numberField.getText());
					int width = Integer.parseInt(widthField.getText());
					int capacity = Integer.parseInt(capacityField.getText());
					AddFlightCommand cmd = new AddFlightCommand();
					cmd.addFlight(number, width, capacity);
					dispose();
					if (!cmd.wasSuccessful())
					{
						JOptionPane.showMessageDialog(getTopLevelAncestor(), 
						cmd.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException e){
					/*Pop up error message if input format not correct*/
					JOptionPane.showMessageDialog(getTopLevelAncestor(), 
							"Please enter in correct format", "Error", 
							JOptionPane.ERROR_MESSAGE);
							new addFlightPanel().add(new JOptionPane());				
				}
				}
			}
		}
		public static final long serialVersionUID = 1;
	}
	
}