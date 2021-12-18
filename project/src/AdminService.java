import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Condition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//server
public class AdminService {
    private ServerSocket server;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException, InterruptedException {
        System.out.println("Attempting to establish connection...");

        server = new ServerSocket(port); // opens server socket
        socket = server.accept(); // opens client socket

        System.out.println("Connection established. Being served on port " + port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String input;
        while ((input = in.readLine()) != "Q") {
            System.out.println(input);
            if ("U".equals(input)) {
                System.out.println("Message received: Update inventory.");
                System.out.println("Processing...");
                while (input.contains("{")) {
                    input = in.readLine();
                    updateInv(input);
                    System.out.println(input);
                }
                out.println("Inventory update complete.");
                System.out.println("Inventory updated successfully, confirmation sent to client.");
                
            } else if ("R".equals(input)) {
                System.out.println("Message received: Return inventory.");
                System.out.println("Processing...");
                // returninv method
                System.out.println("Inventory returned under 'inventory.json'. Confirmation sent to client.");
                continue;
            } else {
                continue;
            }
        }
        System.out.println("Server is shut down.");
    }

    public void updateInv(String input) throws IOException {
        FileWriter invUpdate = new FileWriter("inventory.json", true); // opens filewriter to write to JSON file
        PrintWriter invUpdateWriter = new PrintWriter(invUpdate); // printwriter that channels through filewriter
        if (input.equals("U")) {
            invUpdateWriter.close();
            invUpdate.close();
            System.out.println("Stuck here?");
            return;
        } else {
            invUpdateWriter.println(input);
            invUpdateWriter.close();
            invUpdate.close();
            System.out.println("updateInvclient executes");
        }
    }

    public void messageClient(String message) throws IOException {
        out.println(message);
    }

    public String recMsg() throws IOException {
        return in.readLine();
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
        AdminService service = new AdminService();
        try {
            service.start(6666);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
