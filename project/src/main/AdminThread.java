package main;
import java.io.IOException;

/**
 * Starts a new instance of the administrative server in its own thread so as to
 * not disturb the user of the Storefront app.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 12/18/21
 */
public class AdminThread extends Thread {
    /**
     * Defines the run task for the server thread. Allows this to be run in the
     * background so the user of the storefront is not disturbed.
     */
    public void run() {
        AdminService admin = new AdminService();
        try {
            admin.start(6666);
        } catch (IOException e) {
            System.out.println("IO error occurred in adminThread.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
