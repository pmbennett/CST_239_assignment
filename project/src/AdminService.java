import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.*;
import java.util.concurrent.locks.Condition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//server
public class AdminService {
    private ServerSocket server;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Starts the server and enables communications with the client; also receives
     * commands from the client and processes them, updating master inventory and
     * returning master inventory to the client in JSON format, depending on the
     * command received.
     * 
     * @param port The port the server should listen on for client connection.
     * @throws IOException
     * @throws InterruptedException
     */
    public void start(int port) throws IOException, InterruptedException {
        System.out.println("Attempting to establish connection...");

        server = new ServerSocket(port); // opens server socket
        socket = server.accept(); // opens client socket

        System.out.println("Connection established. Being served on port " + port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String input;
        while ((input = in.readLine()) != "Q") {
            if ("U".equals(input)) {
                System.out.println("Message received: Update inventory.");
                System.out.println("Processing...");

                for (int i = 0; i < readLines(); i++) {
                    input = in.readLine();
                    updateInv(input);
                }
                out.println("Inventory update complete.");
                System.out.println("Inventory updated successfully, confirmation sent to client.");
            } else if ("R".equals(input)) {
                System.out.println("Message received: Return inventory.");
                System.out.println("Processing...");
                messageClient("Current store inventory as JSON: ");
                returnInv("inventory.json");
                System.out.println("Inventory returned to client.");
            } else if("Q".equals(input)) {
                break;
            }
        }
        cleanup();
        System.out.println("Server is shut down.");
    }

    /**
     * Sends a message from the server to the client through the output stream.
     * 
     * @param message The communication to be sent to the client.
     * @throws IOException
     */
    public void messageClient(String message) throws IOException {
        out.println(message);
    }

    /**
     * Receives and reads a message from the client through the input stream.
     * 
     * @return The message from the client.
     * @throws IOException
     */
    public String recMsg() throws IOException {
        return in.readLine();
    }

    /**
     * When a JSON string payload is received from the client, appends
     * it to the master inventory JSON file that controls the store inventory.
     * 
     * @param input the JSON string to be added to the master inventory file.
     * @throws IOException
     */
    public void updateInv(String input) throws IOException {
        FileWriter invUpdate = new FileWriter("inventory.json", true); // opens filewriter to write to JSON file
        PrintWriter invUpdateWriter = new PrintWriter(invUpdate); // printwriter that channels through filewriter

        invUpdateWriter.println(input);
        invUpdateWriter.close();
        invUpdate.close();
    }

    /**
     * Processes a request from the client to return the entire inventory of the
     * store as JSON strings to the client console. Reads from master inventory file
     * to do so.
     * 
     * @param filename The filename of the master JSON inventory file.
     */
    public void returnInv(String filename) {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String json = sc.nextLine();
                messageClient(json);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads through the JSON file containing new inventory strings, and counts each
     * line, thereby effectively returning a count of new products.
     * 
     * @return The line count of the JSON file containing the new inventory items.
     */
    public long readLines() {
        long count = 0;
        try {
            Path file = Paths.get("invupdate.json");
            count = Files.lines(file).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Closes all server/client connections and I/O streams.
     * 
     * @throws IOException
     */
    public void cleanup() throws IOException {
        in.close();
        out.close();
        //socket.close();
        server.close();
    }

    public static void main(String[] args) throws IOException {
        AdminService service = new AdminService();
        try {
            service.start(6666);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
