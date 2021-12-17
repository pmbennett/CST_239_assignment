import java.io.IOException;

//server thread
public class AdminThread extends Thread {

    public void run() {
        AdminService admin = new AdminService();
        try {
            admin.start(6666);
        } catch (IOException e) {
            System.out.println("IO error occurred.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
