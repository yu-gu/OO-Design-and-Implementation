package util;

/*
 * FormatUos.java --------------------------------------------- Copyright (c) 2004 University of
 * Saskatchewan All Rights Reserved ---------------------------------------------
 */

/**
 * A class with a method to format data into a field of a specified width, and a method to format a
 * Double value into decimal notation. The first method can be used to align output into columns to
 * facilitate pleasant display.
 */
public class FormatUos {
    /**
     * Pad the string representation of `obj' to size at least `width' according to `format'
     * specification.
     * 
     * @param obj the object whose string representation is to be formated
     * @param width the width of the field into which the value is to be placed
     * @param format `c', `l', or `r' for centering, left justified, or right justified
     * @precond format == 'c', 'l', or 'r'
     */
    public static String pad(Object obj, int width, char format) {
        if (format != 'c' && format != 'l' && format != 'r')
            throw new IllegalArgumentException(
                    "format must be 'c', 'l', or 'r'.");

        String ObjAsString = obj.toString();
        if (ObjAsString.length() >= width)
            return ObjAsString;

        StringBuilder result = new StringBuilder(width);
        int numberSpaces = width - ObjAsString.length();
        if (format == 'c') {
            int frontSpaces = numberSpaces / 2;
            for (int i = 0; i < frontSpaces; i++)
                result = result.append(" ");
            result.append(ObjAsString);
            for (int i = 0; i < numberSpaces - frontSpaces; i++)
                result = result.append(" ");
        } else if (format == 'l') {
            result.append(ObjAsString);
            for (int i = 0; i < numberSpaces; i++)
                result = result.append(" ");
        } else if (format == 'r') {
            for (int i = 0; i < numberSpaces; i++)
                result = result.append(" ");
            result.append(ObjAsString);
        }
        return result.toString();
    }

    /**
     * Obtain a String representation of value in decimal notation with d decimal places.
     * 
     * @param value the value to be formated
     * @param d the number of decimal places to be included
     * @precond d >= 0
     */
    public static String withDecimals(double value, int d) {
        if (d < 0)
            throw new IllegalArgumentException(
                    "Number of decimal places must be >= 0.");

        String result = "" + Math.round(value * Math.pow(10, d));
        if (d == 0)
            return result;
        else {
            while (result.length() < d + 1)
                result = "0" + result;

            return result.substring(0, result.length() - d) + "."
                    + result.substring(result.length() - d, result.length());
        }
    }

    /**
     * A method to test the methods of the class.
     */
    public static void main(String[] args) {
        if (!pad(1, 6, 'l').equals("1     "))
            System.out.println("pad(1, 6, 'l') failed.  It yielded "
                    + pad(1, 6, 'l'));
        if (!pad(1, 6, 'r').equals("     1"))
            System.out.println("pad(1, 6, 'l') failed.  It yielded "
                    + pad(1, 6, 'r'));
        if (!pad(1, 6, 'c').equals("  1   "))
            System.out.println("pad(1, 6, 'l') failed.  It yielded "
                    + pad(1, 6, 'c'));

        if (!pad("bye.", 6, 'l').equals("bye.  "))
            System.out.println("pad(\"bye.\", 6, 'l') failed.  It yielded "
                    + pad("bye.", 6, 'l'));
        if (!pad("bye.", 6, 'r').equals("  bye."))
            System.out.println("pad(\"bye.\", 6, 'l') failed.  It yielded "
                    + pad("bye.", 6, 'r'));
        if (!pad("bye.", 6, 'c').equals(" bye. "))
            System.out.println("pad(\"bye.\", 6, 'l') failed.  It yielded "
                    + pad("bye.", 6, 'c'));

        if (!withDecimals(4.7523, 1).equals("4.8"))
            System.out.println("withDecimals(4.7523, 1) failed.  It yielded "
                    + withDecimals(4.7523, 1));
        if (!withDecimals(4.7523, 3).equals("4.752"))
            System.out.println("withDecimals(4.7523, 3) failed.  It yielded "
                    + withDecimals(4.7523, 3));
        if (!withDecimals(4.7523, 0).equals("5"))
            System.out.println("withDecimals(4.7523, 0) failed.  It yielded "
                    + withDecimals(4.7523, 0));
        if (!withDecimals(4.7523, 6).equals("4.752300"))
            System.out.println("withDecimals(4.7523, 6) failed.  It yielded "
                    + withDecimals(4.7523, 6));

        if (!withDecimals(4, 4).equals("4.0000"))
            System.out.println("withDecimals(4, 4) failed.  It yielded "
                    + withDecimals(4, 4));
        if (!withDecimals(0.00068, 3).equals("0.001"))
            System.out.println("withDecimals(0.00068, 3) failed.  It yielded "
                    + withDecimals(0.00068, 3));
        if (!withDecimals(0.00068, 2).equals("0.00"))
            System.out.println("withDecimals(0.00068, 2) failed.  It yielded "
                    + withDecimals(0.00068, 2));

        if (!pad(withDecimals(4, 1), 5, 'l').equals("4.0  "))
            System.out
                    .println("pad(withDecimals(4, 1), 5, 'l') failed.  It yielded "
                            + pad(withDecimals(4, 1), 5, 'l'));
        if (!pad(withDecimals(4, 1), 5, 'r').equals("  4.0"))
            System.out
                    .println("pad(withDecimals(4, 1), 5, 'r') failed.  It yielded "
                            + pad(withDecimals(4, 1), 5, 'r'));
        if (!pad(withDecimals(4, 1), 5, 'c').equals(" 4.0 "))
            System.out
                    .println("pad(withDecimals(4, 1), 5, 'c') failed.  It yielded "
                            + pad(withDecimals(4, 1), 5, 'c'));

        if (!pad(withDecimals(4, 0), 5, 'l').equals("4    "))
            System.out
                    .println("pad(withDecimals(4, 0), 5, 'l') failed.  It yielded "
                            + pad(withDecimals(4, 0), 5, 'l'));
        if (!pad(withDecimals(4, 0), 5, 'r').equals("    4"))
            System.out
                    .println("pad(withDecimals(4, 0), 5, 'r') failed.  It yielded "
                            + pad(withDecimals(4, 0), 5, 'r'));
        if (!pad(withDecimals(4, 0), 5, 'c').equals("  4  "))
            System.out
                    .println("pad(withDecimals(4, 0), 5, 'c') failed.  It yielded "
                            + pad(withDecimals(4, 0), 5, 'c'));
    }
}
