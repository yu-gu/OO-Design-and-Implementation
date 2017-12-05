//Name:YU GU Student#:11195367 NSID:yug242 Course:01 Tutorial:T03
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import commands.AssignSeatCommand;
import commands.BookFirstClassCommand;
import commands.BookPassengerCommand;
import commands.GetFlightCommand;
import entities.Flight;
import entities.Passenger;

/**
 * The frame to display current information for a flight, and to
 * perform operations on the flight: book a regular passenger,
 * book a first-class passenger, and assign a seat to a regular
 * passenger.
 */
public class HandleFlightFrame extends JFrame 
{
	/*
	 * The number of the flight being handled.
	 */
	int number;
	
	/*
	 * The flight being handled in this window.
	 */
	Flight flight;
	
	/*
	 * The main panel in the frame of the window.
	 */
	JPanel mainPanel;
		
	/**
	 * Construct the window to handle the flight with the 
	 * specified number, and carry out the operations entered
	 * into the window.
	 */
	public HandleFlightFrame(int number)
	{
		this.number = number;
		
		/* Initialize the frame and add a panel for the components. */
		setTitle("Handle Flight " + number);
		setSize(700, 400);
		mainPanel = new JPanel();
		add(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		
		/* Obtain the flight corresponding to the number, and
		 * place the panels in the main panel in order
		 * to display the information and carry out the operations.
		 * If the flight access fails, display the error message.
		 * If an operation fails, display the error in the label
		 * at the top of the window.
		 */
		GetFlightCommand getFlightCmd = new GetFlightCommand();
		getFlightCmd.accessFlight(number);
		if (getFlightCmd.wasSuccessful())
		{
			flight = getFlightCmd.getFlight();
			JPanel headingPanel = obtainHeadingPanel("Welcome");
			mainPanel.add(headingPanel, BorderLayout.PAGE_START);
			JPanel operationsPanel = obtainOperationsPanel();
			mainPanel.add(operationsPanel, BorderLayout.CENTER);
			/* Display on the left the empty seats of the flight.  */
			JScrollPane emptySeatsPane = getEmptySeatsPanel();
			mainPanel.add(emptySeatsPane, BorderLayout.LINE_START);
			/* Display on the right the regular passengers without a seat. */
			JScrollPane bookedPassPane = getBookedPassPanel();	
			mainPanel.add(bookedPassPane, BorderLayout.LINE_END);
		}
		else
		{
			JLabel errorLabel = new JLabel("There is no flight with number " + number);
			mainPanel.add(errorLabel);
		}
	}
	
	/** 
	 * The label with greeting or the error message.
	 */
	JLabel greetingLabel;
	
	/**
	 * The label with the flight number and remaining capacity.
	 */
	JLabel flightLabel;
	
	/**
	 * Create a panel containing the greeting label and the flight number
	 * and remaining capacity.
	 * @param greeting the message to be display in the greeting label
	 * @return the panel for the top of the window
	 */
	private JPanel obtainHeadingPanel(String greeting)
	{
		JPanel greetingPanel = new JPanel();
		greetingPanel.setLayout(new BorderLayout());
		greetingLabel = new JLabel("Welcome", JLabel.CENTER);
		greetingPanel.add(greetingLabel, BorderLayout.PAGE_START);
		flightLabel = new JLabel("Flight " + number 
				+ "  with a remaining capacity of " + flight.remainingCapacity(), JLabel.LEFT);
		greetingPanel.add(flightLabel, BorderLayout.CENTER);
		greetingPanel.add(new JLabel("    "), BorderLayout.PAGE_END);
		return greetingPanel;
	}
	
	/**
	 * Create the panel for the operations on the flight: book
	 * a regular passenger, book a first-class passenger, and assign
	 * a seat to a regular passenger.
	 * @return the panel for the middle of the window
	 */
	private JPanel obtainOperationsPanel()
	{
		JPanel operationsPanel = new JPanel();
		operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.Y_AXIS));
		
		/* Add the panel to book a regular passenger. */
		JPanel bookPassPanel = obtainBookPassPanel();
		operationsPanel.add(bookPassPanel);
		
		/* Add the panel to assign seat to a regular passenger. */
		JPanel assignSeatPanel = obtainAssignSeatPanel();
		operationsPanel.add(assignSeatPanel);
		
		/* Add the panel to book a first-class passenger. */
		JPanel firstClassPanel = obtainFirstClassPanel();
		operationsPanel.add(firstClassPanel);
		
		/* Add the panel to exit this window. */
		JPanel returnPanel = obtainReturnPanel();
		operationsPanel.add(returnPanel);
		
		return operationsPanel;
	}
	
	/**
	 * @return the panel to book a regular passenger
	 */
	private JPanel obtainBookPassPanel()
	{
		JPanel bookPassPanel = new JPanel();
		bookPassPanel.add(new JLabel("Book passenger:  "));
		/* Add a panel for the prompt. */
		JPanel promptPanel = new JPanel();
		promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
		promptPanel.add(new JLabel("Name"));
		bookPassPanel.add(promptPanel);
		/* Add a panel for the field to entry the passenger's name. */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		final JTextField nameField = new JTextField(6);
		fieldPanel.add(nameField);
		bookPassPanel.add(fieldPanel);
		/* Add a listener to the name field, and add its listener. */
		class BookPassFieldListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = nameField.getText();
				BookPassengerCommand bookCmd = new BookPassengerCommand();
				bookCmd.bookPassenger(name, number);
				if (!bookCmd.wasSuccessful())
					greetingLabel.setText("****Error: " 
							        + bookCmd.getErrorMessage());
				else
				{
					greetingLabel.setText("Welcome");
					flightLabel.setText("Flight " + number 
							+ "  with a remaining capacity of " + flight.remainingCapacity());
					nameField.setText("");
					rebuildBookedPassPanel();
				}
				mainPanel.revalidate();
			}
		}
		nameField.addActionListener(new BookPassFieldListener());
		return bookPassPanel;
	}
	
	/**
	 * @return the panel to assign a seat to a regular passenger
	 */
	private JPanel obtainAssignSeatPanel()
	{
		JPanel assignSeatPanel = new JPanel();
		assignSeatPanel.add(new JLabel("Assign seat:    "));
		/* Add a panel for the prompts. */
		JPanel promptPanel = new JPanel();
		promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
		promptPanel.add(new JLabel("Name"));
		promptPanel.add(new JLabel("Seat"));
		assignSeatPanel.add(promptPanel);
		/* Add a panel for the input fields. */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		final JTextField nameField = new JTextField(6);
		fieldPanel.add(nameField);
		final JTextField seatField = new JTextField(6);
		fieldPanel.add(seatField);
		assignSeatPanel.add(fieldPanel);
		/* Add a listener to the seat field, and define the listener class. */
		class BookPassFieldListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = nameField.getText();
				String seat = seatField.getText();
				AssignSeatCommand assignSeatCmd = new AssignSeatCommand();
				assignSeatCmd.assignSeatForPassenger(name, number, seat);
				if (!assignSeatCmd.wasSuccessful())
					greetingLabel.setText("****Error: " 
							        + assignSeatCmd.getErrorMessage());
				else
				{
					greetingLabel.setText("Welcome");
					nameField.setText("");
					seatField.setText("");
					rebuildBookedPassPanel();
					rebuildEmptySeatsPanel();
				}
				mainPanel.revalidate();
			}
		}
		seatField.addActionListener(new BookPassFieldListener());
		return assignSeatPanel;
	}
	
	/**
	 * @return the panel to book a first-class passenger
	 */
	private JPanel obtainFirstClassPanel()
	{
		JPanel firstClassPanel = new JPanel();
		firstClassPanel.add(new JLabel("First Class booking:  "));
		/* Add a panel with the prompts. */
		JPanel promptPanel = new JPanel();
		promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
		promptPanel.add(new JLabel("Name"));
		promptPanel.add(new JLabel("Seat"));
		promptPanel.add(new JLabel("Meal"));
		firstClassPanel.add(promptPanel);
		/* Add a panel with the fields. */
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		final JTextField nameField = new JTextField(6);
		fieldPanel.add(nameField);
		final JTextField seatField = new JTextField(6);
		fieldPanel.add(seatField);
		final JTextField mealField = new JTextField(6);
		fieldPanel.add(mealField);
		firstClassPanel.add(fieldPanel);
		/* Add a listener to the meal plan field and define the listener. */
		class FirstClassFieldListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = nameField.getText();
				String seat = seatField.getText();
				String meal = mealField.getText();
				BookFirstClassCommand bookCmd = new BookFirstClassCommand();
				bookCmd.bookFirstClassPassenger(name, number, seat, meal);
				if (!bookCmd.wasSuccessful())
					greetingLabel.setText("****Error: " 
							        + bookCmd.getErrorMessage());
				else
				{
					greetingLabel.setText("Welcome");
					flightLabel.setText("Flight " + number 
							+ "  with a remaining capacity of " + flight.remainingCapacity());
					nameField.setText("");
					seatField.setText("");
					mealField.setText("");
					rebuildEmptySeatsPanel();
				}
				mainPanel.revalidate();
			}
		}
		mealField.addActionListener(new FirstClassFieldListener());
		return firstClassPanel;
	}
	
	/**
	 * @return the panel with the button to close this window
	 */
	private JPanel obtainReturnPanel()
	{
		JButton returnButton = new JButton("Return to main menu");
		// Place the button in a panel so that the button doesn't grow too large
		JPanel enclosingPanel = new JPanel();
		enclosingPanel.add(returnButton);
		returnButton.addActionListener(
				new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							setVisible(false);
							// The window for the main menu should still be visible.
						}
					}
				);
		return enclosingPanel;
	}
	
	/**
	 * The text area is display the empty seats of the flight.
	 */
	JTextArea emptySeatsText;
	
	/*
	 * @return the scroll pane that contains the string to display the empty seats.
	 */
	private JScrollPane getEmptySeatsPanel()
	{
		if (flight == null)
			throw new RuntimeException("Class in invalid state.  "
					   + "Flight field should be non-empty.");
		emptySeatsText = new JTextArea(flight.availableSeats());
		emptySeatsText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		JScrollPane emptySeatsPane = new JScrollPane(emptySeatsText);
		emptySeatsPane.setPreferredSize(new Dimension(200, 300));
		return emptySeatsPane;
	}
	
	/** 
	 * Update the text to display the current empty seats of the flight. 
	 */
	private void rebuildEmptySeatsPanel()
	{
		if (flight == null)
			throw new RuntimeException("Class in invalid state.  "
					   + "Flight field should be non-empty.");
		emptySeatsText.setText(flight.availableSeats());
	}
	
	/**
	 * The text area is display the regular passengers booked on the flight
	 * but who have not yet been assigned a seat.
	 */
	JTextArea noSeatText;
	
	/*
	 * @return the scroll pane that contains the string to display 
	 *         the regular passengers with no seat yet
	 */
	private JScrollPane getBookedPassPanel()
	{
		noSeatText = new JTextArea("Passengers with no seat:\n");
		if (flight == null)
			throw new RuntimeException("Class in invalid state.  "
					   + "Flight field should be non-empty.");
		for (Passenger p: flight.getWaitingList())
			noSeatText.append("   " + p.getName() + "\n");
		JScrollPane  bookedPassPane = new JScrollPane(noSeatText);
		bookedPassPane.setPreferredSize(new Dimension(200, 300));
		return bookedPassPane;
	}

	/** 
	 * Update the text to display the current passengers that have
	 * not yet been assigned a seat.
	 */
	private void rebuildBookedPassPanel()
	{
		noSeatText.setText("Passengers with no seat:\n");
		if (flight == null)
			throw new RuntimeException("Class in invalid state.  "
					   + "Flight field should be non-empty.");
		for (Passenger p: flight.getWaitingList())
			noSeatText.append("   " + p.getName() + "\n");	
	}
	
	public static final long serialVersionUID = 1;
}
