package commands;

import java.util.Collection;

import systemEntities.Printer;

import containers.AccountSetAccess;

import accounts.Account;

/**
 * The command to print out all the month end statements.
 */
public class MonthEndCommand extends Command {
    /** The listing for the end-of-month statements. */
    public String statement;

    /**
     * Obtain a listing of the end-of-month statements, and place it in statement.
     */
    public void runStatements() {
        statement = "";
        Collection<Account> coll = AccountSetAccess.dictionary().values();
        for (Account a : coll)
            statement += a.generateStmt();
        successful = true;
    }

    /**
     * Print the month end statements.
     * 
     * @precond wasSuccessful()
     */
    public void printStatements() {
        if (!wasSuccessful())
            throw new RuntimeException("The last execution must have been "
                    + "successful in order to print the statements.");
        Printer.printMessage(statement);
    }
}
