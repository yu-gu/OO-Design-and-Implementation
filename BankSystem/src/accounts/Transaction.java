package accounts;

import util.FormatUos;

/**
 * This class saves the details of a command for statement generation and to permit audits.
 */
public class Transaction {
    /** The amount of money involved in the transaction */
    private float amount;

    /** The line of credit involved in the transaction */
    private float lineOfCredit;

    /** The date of the transaction */
    private int date;

    /** A description of the transaction */
    private String description;

    /**
     * A constructor for a transaction that takes in a date, description, amount, and line of credit
     * 
     * @param d the date (in int format)
     * @param des a description of the transaction
     * @param amt the change in the balance as a result of the transaction
     * @param loc the new line of credit, or 0.0 if unchanged
     */
    public Transaction(int d, String des, float amt, float loc) {
        date = d;
        description = des;
        amount = amt;
        lineOfCredit = loc;
    }

    /**
     * Return a header line for a listing of transactions via toString().
     * 
     * @return a header line for a listing of transactions via toString()
     */
    public static String header() {
        return "\n" + FormatUos.pad("Date", 5, 'l') + "  "
                + FormatUos.pad("Description of transaction", 36, 'l')
                + FormatUos.pad("Amount", 9, 'r');
    }

    /**
     * Return a string representation of the transaction.
     * 
     * @return a string representation of the transaction
     */
    @Override
    public String toString() {
        String descWithLOC = description;
        if (lineOfCredit != 0.0)
            descWithLOC += FormatUos.withDecimals(lineOfCredit, 2);
        return "\n" + FormatUos.pad(date, 5, 'r') + "  "
                + FormatUos.pad(descWithLOC, 36, 'l')
                + FormatUos.pad(FormatUos.withDecimals(amount, 2), 9, 'r');
    }

    /**
     * Return the amount.
     * 
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Return the date.
     * 
     * @return the date
     */
    public int getDate() {
        return date;
    }

    /**
     * Return the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return the lineOfCredit.
     * 
     * @return the lineOfCredit
     */
    public float getLineOfCredit() {
        return lineOfCredit;
    }
}
