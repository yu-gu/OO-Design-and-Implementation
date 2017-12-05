package systemEntities;

/**
 * A class to access clock features. The current version simulates time passing by advancing the
 * time and date whenever either is accessed.
 */
public class Clock {
    /*
     * For one run of the system, accessing the actual system clock would always yield the same date
     * and nearly the same time. The current implementation is defined to just return a
     * progressively larger value every time the time() or date() methods are called. As there is
     * only one clock, the time field and all methods are static to facilitate access of the values.
     */

    /** The current time */
    private static int currentTime;

    /**
     * Return the current time.
     * 
     * @return the current time
     */
    public static int getTime() {
        currentTime += 10; // 10 minutes after the last invocation
        return currentTime;
    }

    /**
     * Return the current date.
     * 
     * @return the current date
     */
    public static int getDate() {
        currentTime += 2400; // one day after the last invocation
        return currentTime;
    }
}
