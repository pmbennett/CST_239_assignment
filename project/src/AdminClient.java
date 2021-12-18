import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//client
public class AdminClient {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Starts the client and establishes a connection to the server; also opens IO
     * streams for transmission of server commands and data transmission and
     * reception.
     * 
     * @param ip   The IP address or host name of the server.
     * @param port The port to establish a connection on with the server.
     * @throws UnknownHostException
     * @throws IOException
     */
    public void start(String ip, int port) throws UnknownHostException, IOException {

        client = new Socket(ip, port);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void messageServer(String message) throws IOException {
        out.println(message);
    }

    public String recMsg(BufferedReader in) throws IOException {
        return in.readLine();
    }

    /**
     * \
     * Reads a JSON file and sends each line in string format to the server for
     * processing. Critical method for the client to work correctly.
     * 
     * @param invFile The JSON file to be read for processing and transmission to
     *                the server.
     * @throws IOException
     */
    public void readInv(String invFile) throws IOException {

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
    }

    /**
     * Receives transmission of inventory from the server and prints to console in
     * JSON format.
     * 
     * @throws IOException
     */
    public void getFullInv() throws IOException {
        for (int i = 0; i < readLines(); i++) {
            System.out.println(recMsg(in));
        }
    }

    /**
     * Reads through the JSON file containing master inventory, and counts each
     * line, thereby effectively returning a count of total products.
     * 
     * @return The line count of the JSON file containing the new inventory items.
     */
    public long readLines() {
        long count = 0;
        try {
            Path file = Paths.get("inventory.json");
            count = Files.lines(file).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 
     * Sends a JSON string representing a saleable product to the server for
     * processing.
     * 
     * @param item The product object to be converted to JSON and sent.
     */
    public void sendToServer(Product item) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(item);
        out.println(json);
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

    public static void main(String[] args) {
        AdminClient admin = new AdminClient();
        try {
            admin.start("localhost", 6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(System.in);
        char userInput = 'X';

        while (userInput != 'Q' || userInput != 'q') {
            System.out.println("To update the store, press U.");
            System.out.println("To return salable inventory, press R.");
            System.out.println("To quit the Admin Client, press Q.");
            userInput = scan.next().charAt(0);
            if (userInput == 'U' || userInput == 'u') {
                try {
                    String response;
                    admin.messageServer("U");// tells server to start listening for updates
                    admin.readInv("invupdate.json");// reads new file, puts into output stream
                    response = admin.recMsg(admin.in);
                    System.out.println("From server: " + response);
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userInput == 'R' || userInput == 'r') {
                try {
                    admin.messageServer("R");
                    admin.recMsg(admin.in);
                    admin.getFullInv();
                    continue;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userInput == 'Q' || userInput == 'q') {
                try {
                    admin.messageServer("Q");
                } catch (IOException e) {
                    e.printStackTrace();
                }
               break;
            }
        }
        admin.cleanup();
        System.out.println("Admin service shut down.");
    }
}
