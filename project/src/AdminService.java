import java.io.*;
import java.net.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//server
public class AdminService {
    private ServerSocket server;
    private static Socket socket;
    private PrintWriter out;
    private static BufferedReader in;

    public void start(int port) throws IOException, InterruptedException {
        System.out.println("Attempting to establish connection...");

        server = new ServerSocket(port); // opens server socket
        socket = server.accept(); // opens client socket

        System.out.println("Connection established. Being served on port " + port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String input;

        while ((input = in.readLine()) != null) {
            if ("Q".equals(input)) {
                System.out.println("Shutdown command received.");
                out.println("QUIT");
                break;
            } else if ("U".equals(input)) {
                System.out.println("Message received: Update inventory.");
                System.out.println("Processing...");
                updateInv("invupdate.json");
                System.out.println("Inventory updated successfully, confirmation sent to client.");
            } else if ("R".equals(input)) {
                System.out.println("Message received: Return inventory.");
                System.out.println("Processing...");
                // returninv method
                System.out.println("Inventory returned under 'inventory.json'. Confirmation sent to client.");
            } else {
                Thread.sleep(100);
            }
        }
        System.out.println("Server is shut down.");
    }

    public void updateInv(String invFile) throws IOException {

        File file = new File(invFile);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            String json = scan.nextLine();
            ObjectMapper mapper = new ObjectMapper();
            if (json.contains("damage")) {
                Weapon item = mapper.readValue(json, Weapon.class);
                sendToServer(item);
            } else if (json.contains("protection")) {
                Armor item = mapper.readValue(json, Armor.class);
                sendToServer(item);
            } else if (json.contains("healthRegen")) {
                Health item = mapper.readValue(json, Health.class);
                sendToServer(item);
            }
        }
        scan.close();
        System.out.println("updateInvserver executes");
    }

    public void sendToServer(Product item) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(item);
        out.println(json);
        System.out.println("sendtoserver exec");
        System.out.println(json);
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
        }
    }
}
