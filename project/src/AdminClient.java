import java.net.*;
import java.io.*;
import java.util.*;

//client
public class AdminClient {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) throws UnknownHostException, IOException {

        client = new Socket(ip, port);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public String messageServer(String message) throws IOException {
        out.println(message);
        return in.readLine();
    }

    /**
     * Closes all server/client connections and I/O streams.
     * 
     * @throws IOException
     */
    public void cleanup() {
        try {
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        AdminClient admin = new AdminClient();
        admin.start("localhost", 6666);

        String response;
        for (int i = 0; i < 10; i++) {
            String message;
            if (i != 9) {
                message = ("Hello from client " + i);
            } else {
                message = ".";
            }
            response = admin.messageServer(message);
            System.out.println("Server response was: " + response);
            if (response.equals("QUIT")) {
                break;
            }
            Thread.sleep(5000);
        }
        admin.cleanup();
    }
}
