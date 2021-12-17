import java.net.*;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public void messageServer(String message) throws IOException {
        out.println(message);
    }

    public String recMsg() throws IOException {
        return in.readLine();
    }

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
        System.out.println("updateInvserver executes");
    }

    public BufferedReader getInStream() {
        return in;
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

        System.out.println("To update the store, press U.");
        System.out.println("To return salable inventory, press R.");
        System.out.println("To quit the Admin Client, press Q.");

        Scanner scan = new Scanner(System.in);
        Character userInput = scan.next().charAt(0);
        System.out.println("To update the store, press U.");
        System.out.println("To return salable inventory, press R.");
        System.out.println("To quit the Admin Client, press Q.");
        while (userInput != 'Q') {

            if (userInput.equals('U')) {
                String response;
                admin.messageServer("U");// tells server to start listening for updates
                admin.readInv("invupdate.json");// reads new file, puts into output stream
                response = admin.recMsg();
                System.out.println("From server: " + response);
            } else if (userInput.equals('R')) {
                String response;
                admin.messageServer("R");
                response = null;
                // return store inventory method
                System.out.println("From server: " + response);
            } else {
                userInput = 'X';
                userInput = scan.next().charAt(0);
                Thread.sleep(1000);
                System.out.println("Enter new reqeust when ready.");
                continue;
            }
        }
        admin.cleanup();
    }
}
