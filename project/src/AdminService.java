import java.io.*;
import java.net.*;
import java.util.*;


//server
public class AdminService {
    private ServerSocket server;
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void startService(int port) {
        System.out.println("Attempting to establish connection...");
        try {
            server = new ServerSocket(port); // opens server socket
            client = server.accept(); // opens client socket

            System.out.println("Connection established.");

        } catch (UnknownHostException e) {
            System.out.println("Host could not be reached.");
        } catch (IOException e) {
            System.out.println("IO error occurred.");
        }
    }

    public void appendToFile(String inputLine) throws IOException {
            out = new PrintWriter(client.getOutputStream(), true); // recieves info from client
            in = new BufferedReader(new InputStreamReader(client.getInputStream())); //returns info to client

            
            Scanner scan = new Scanner(in.readLine()); //reads input from client
            FileWriter invUpdate = new FileWriter("inventory.json", true); //opens filewriter to write to JSON file
            PrintWriter invUpdateWriter = new PrintWriter(invUpdate); //printwriter that channels through filewriter
           
           
            while (scan.hasNext()) {
                String json = scan.nextLine();
                invUpdateWriter.println(json);
            }
            scan.close();
            invUpdateWriter.close();
            invUpdate.close();
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
        server.close();
    }

    public Socket getClient(){
        return client;
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException,InterruptedException {
        AdminService service = new AdminService();
        service.startService(6666);
        BufferedReader in = new BufferedReader(new InputStreamReader(service.getClient().getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != "Quit"){
            if ((inputLine = in.readLine())!= null){
                service.appendToFile(inputLine);
            } else {
                Thread.sleep(100);
            }
        }
    }
}
