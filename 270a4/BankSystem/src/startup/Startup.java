package startup;

import userInterfaces.LoginInterface;
import commands.SystemState;
import commands.LoginCheckCommand;

/**
 * A class to run the bank application using console I/O.
 */
public class Startup {
    /**
     * Run the bank application.
     */
    public static void main(String[] args) {
        /*
         * For debugging purposes, output the initial state of the system so that a tester knows
         * login ids and passwords, and knows what people and accounts already exist.
         */
        SystemState is = new SystemState();
        LoginInterface loginInterface = new LoginInterface();
        loginInterface.sendMessage(is.toString());
        /*
         * Input a sequence of login requests. For each one, run the command to verify the person
         * logging in has authorization, obtain the user from the command, and then create and
         * initiate a controller to handle the interaction with this person.
         */
        int id = loginInterface.getLoginID();
        while (id != 0) {
            String password = loginInterface.getPassword();
            LoginCheckCommand loginCmd = new LoginCheckCommand();
            loginCmd.login(id, password);
            if (loginCmd.wasSuccessful()) {
                /*
                 * StaffType is an enumerated type defined in class LoginCheckCommand to record the
                 * different types of staff members that can log in.
                 */
                LoginCheckCommand.StaffType staffType = loginCmd.getStaffType();
                if (staffType == LoginCheckCommand.StaffType.ADMIN
                        || staffType == LoginCheckCommand.StaffType.TELLER) {
                    Controller controller = new Controller();
                    controller.processCommandsFor(staffType);
                } else
                    loginInterface
                            .sendMessage("***Must be a teller or administrator.");
            } else
                loginInterface
                        .sendMessage("*** Invalid user number or password.");
            id = loginInterface.getLoginID();
        }
    }
}
