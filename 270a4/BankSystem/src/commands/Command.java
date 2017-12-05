package commands;

import accounts.Account;

/**
 * This class contains attributes that are common to all commands, except LoginCheckCommand.
 */
public class Command extends CommandStatus {
    /** Date of the command. */
    public int date;

    /** The main account pertaining to the command. */
    public Account acct;

    /** The amount that the command involves. */
    public float amount;
}
