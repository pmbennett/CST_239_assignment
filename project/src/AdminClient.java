import java.net.*;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

//client
public class AdminClient {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) {
        try {
            client = new Socket(ip, port);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            // TODO add cleanup method to close everything and execute if either of these
            // fail
        } catch (UnknownHostException e) {
            System.out.println("Host could not be reached.");
            return;
        } catch (IOException e) {
            System.out.println("IO error occurred.");
            return;
        }
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

    }

    public void sendToServer(Product item) {
        String jsonObj = item.toString();
        out.println(jsonObj);
    }

    public void stopServer(){
        out.println("Quit");
    }

    /**
     * Closes all server/client connections and I/O streams.
     * 
     * @throws IOException
     */
    public void cleanup() throws IOException {
        in.close();
        out.close();
        client.close();
    }

    public static void main(String[] args) throws IOException {
        AdminClient admin = new AdminClient();
        admin.start("local host", 6666);
        Scanner scan = new Scanner(System.in);
        char userInput = scan.next().charAt(0);

        while (userInput != 'Q') {
            System.out.println("To update the store, press U.");
            System.out.println("To return salable inventory, press R.");
            System.out.println("To quit the Admin Client, press Q.");

            if (userInput == 'U') {
                try {
                    admin.updateInv("inventoryupdate.json");
                } catch (IOException e) {
                    System.out.println("IO error occurred.");
                }
            } else if (userInput == 'R') {
                // return store inventory method
            }
            userInput = scan.next().charAt(0);
        }
        admin.stopServer();
        admin.cleanup();
    }
}
