
//server thread
public class AdminThread extends Thread {

    public void run(){
        AdminService admin = new AdminService();
        admin.startService(6666);
    }
}
