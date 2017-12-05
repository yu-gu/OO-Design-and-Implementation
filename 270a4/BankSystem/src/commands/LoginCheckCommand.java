package commands;

import java.lang.IllegalStateException;
import containers.StaffSetAccess;
import people.Staff;
import people.Admin;
import people.Teller;

/**
 * Check that the id and password entered are valid.
 */
public class LoginCheckCommand extends CommandStatus {
    /** The staff member who is logging in. */
    private Staff user = null;

    /**
     * StaffType is an enumerated type to record the different types of staff members that can log
     * in.
     */
    public enum StaffType {
        ADMIN, TELLER, OTHER
    };

    /**
     * Check if the id and password are valid for a person.
     * 
     * @param id the identifier for a user logging in
     * @param password the password supplied by the user
     */
    public void login(int id, String password) {
        if (password == null) {
            successful = false;
            errorMessage =
                    "A password must be entered." + "  Session terminated.";
            return;
        }

        user = StaffSetAccess.dictionary().get(id);
        if (user == null) {
            successful = false;
            errorMessage =
                    "User number " + id + " was not found."
                            + "  Session terminated.";
            return;
        } else if (password.equals(user.getPassword()))
            successful = true;
        else {
            successful = false;
            errorMessage = "Incorrect password.  Session terminated.";
        }
        return;
    }

    /**
     * Obtain the user who logged in.
     * 
     * @precond successful
     */
    public Staff getUser() throws IllegalStateException {
        if (!successful)
            throw new IllegalStateException("Last login must be successful "
                    + " to access the user.");

        return user;
    }

    /**
     * Obtain staff type of the user who logged in.
     * 
     * @precond successful
     */
    public StaffType getStaffType() throws IllegalStateException {
        if (!successful)
            throw new IllegalStateException("Last login must be successful "
                    + " to access the staff type.");

        if (user instanceof Admin)
            return StaffType.ADMIN;
        else if (user instanceof Teller)
            return StaffType.TELLER;
        else
            return StaffType.OTHER;
    }
}
