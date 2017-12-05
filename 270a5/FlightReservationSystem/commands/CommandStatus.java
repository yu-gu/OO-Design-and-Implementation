package commands;

/**	
 * The common ancestor for all commands that records whether the last execution
 * of the command was successful, and if not, records the error message. 
 */
public class CommandStatus 
{
	/**	Specification of whether or not the command was successfully executed. */
	protected boolean successful = false;

	/**	If the command was not successful, an appropriate error message. */
	protected String errorMessage;

	/**
	 * @return the successful status from the last execution
	 */
	public boolean wasSuccessful() 
	{
		return successful;
	}
	
	/**
	 * @precond ! wasSuccessful()
	 * @return the errorMessage
	 */
	public String getErrorMessage() 
	{
		if (wasSuccessful())
			throw new RuntimeException("The last execution must be " 
					 + "unsuccessful in order to retrieve its error message.");			
		return errorMessage;
	}
}
