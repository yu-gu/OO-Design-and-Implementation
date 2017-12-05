package commands;

import containers.StaffSetAccess;
import containers.CustomerSetAccess;

/**
 * Obtain the state of the system for testing and debugging purposes.
 */
public class SystemState extends CommandStatus {
    /**
     * String representation of the current state of the system.
     * 
     * @return a string representation of the current state of the system
     */
    @Override
    public String toString() {
        String result =
                "The staff members are \n"
                        + StaffSetAccess.dictionary().toString();
        result = result + "\nNote their passwords are the same as their ids.";
        result =
                result + "\nThe customers are \n"
                        + CustomerSetAccess.dictionary().values().toString();
        successful = true;
        return (result);
    }
}
