import java.io.*;
import java.net.*;

//server
public class AdminService {
    private ServerSocket server;
    private static Socket socket;
    private PrintWriter out;
    private static BufferedReader in;

    public void start(int port) throws IOException {
        System.out.println("Attempting to establish connection...");

        server = new ServerSocket(port); // opens server socket
        socket = server.accept(); // opens client socket

        System.out.println("Connection established. Being served on port " + port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String input;

       while ((input = in.readLine()) != null) {
            if (".".equals(input)) {
                System.out.println("Shutdown command received.");
                out.println("QUIT");
                break;
            } else {
                System.out.println("Message received: " + input);
                out.println("OK");
            }
        }
        System.out.println("Server is shut down.");
        }
    

    /**
     * Closes all server/client connections and I/O streams.
     * 
     * @throws IOException
     */
    public void cleanup() throws IOException {
        in.close();
        out.close();
        socket.close();
        server.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("check server");
        AdminService service = new AdminService();
        service.start(6666);

    }
}
