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

    public void messageServer(String message) throws IOException {
        out.println(message);
    }

    public String recMsg() throws IOException{
        return in.readLine();
    }

    public void updateInv(String response) throws IOException {
        FileWriter invUpdate = new FileWriter("inventory.json", true); // opens filewriter to write to JSON file
        PrintWriter invUpdateWriter = new PrintWriter(invUpdate); // printwriter that channels through filewriter

        invUpdateWriter.println(response);
        invUpdateWriter.close();
        invUpdate.close();
        System.out.println("updateInvclient executes");
    }

    public BufferedReader getInStream(){
        return in;
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

        while (userInput != 'Q') {
            System.out.println("To update the store, press U.");
            System.out.println("To return salable inventory, press R.");
            System.out.println("To quit the Admin Client, press Q.");
            String response;
            if (userInput.equals('U')) {
                admin.messageServer("U");
                while ((response = admin.recMsg())!= null){
                admin.updateInv(response);
                System.out.println("Server response was: " + response);
                }  
            } else if (userInput.equals('R')) {
                admin.messageServer("R");
                response = null;
                // return store inventory method
                System.out.println("Server response was: " + response);
            } else {
                Thread.sleep(100);
            }
            userInput = scan.next().charAt(0);
        }
        admin.cleanup();
    }
}
