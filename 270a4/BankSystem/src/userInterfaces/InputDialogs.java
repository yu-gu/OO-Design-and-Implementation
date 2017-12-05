package userInterfaces;

import javax.swing.JOptionPane;

/** Four methods to facilitate input of values using dialogs. */
public class InputDialogs {
    /**
     * Read in a String using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the String obtained
     */
    public static String readString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * Read in an int value using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the int value obtained
     */
    public static int readInt(String prompt) {
        String valueAsString = JOptionPane.showInputDialog(null, prompt);
        int value;
        if (valueAsString != null && valueAsString.length() > 0) {
            try {
                value = Integer.parseInt(valueAsString);
            } catch (NumberFormatException e) {
                value = readInt("Please enter an integer value!\n" + prompt);
            }
        } else
            value = readInt("Please enter an integer value!\n" + prompt);
        return value;
    }

    /**
     * Read in a float value using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the float value obtained
     */
    public static float readFloat(String prompt) {
        String valueAsString = JOptionPane.showInputDialog(null, prompt);
        float value;
        if (valueAsString != null && valueAsString.length() > 0) {
            try {
                value = Float.parseFloat(valueAsString);
            } catch (NumberFormatException e) {
                value = readFloat("Please enter a float value!\n" + prompt);
            }
        } else
            value = readFloat("Please enter a float value!\n" + prompt);
        return value;
    }

    /**
     * Read in a double value using the specified prompt.
     * 
     * @param prompt the String to be used to prompt the user
     * @return the double value obtained
     */
    public static double readDouble(String prompt) {
        String valueAsString = JOptionPane.showInputDialog(null, prompt);
        double value;
        if (valueAsString != null && valueAsString.length() > 0) {
            try {
                value = Double.parseDouble(valueAsString);
            } catch (NumberFormatException e) {
                value = readDouble("Please enter a double value!\n" + prompt);
            }
        } else
            value = readDouble("Please enter a double value!\n" + prompt);
        return value;
    }
}
