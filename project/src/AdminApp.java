//server app
public class AdminApp {
      public static void main(String[] args) throws InterruptedException {
          AdminThread thread = new AdminThread();
          thread.start();
      }
}
