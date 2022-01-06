package main;
/**
 * Runs the administrative server thread, containing the server app.
 * 
 * @author Paul Bennett
 * @version 1.0
 * @since 12/18/21
 */
public class AdminApp {
    public static void main(String[] args) throws InterruptedException {
        AdminThread thread = new AdminThread();
        thread.start();
    }
}
