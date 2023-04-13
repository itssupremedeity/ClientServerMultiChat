package MultiChat.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    public static ArrayList<Server> clients = new ArrayList<>();
    public static final int PORT = 3000;
    private final BufferedReader in;
    private final PrintStream out;
    private ClientHandler clientHandler;

    public Server(Socket socket) throws IOException {
        String clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);
        this.clientHandler = new ClientHandler(socket);
        out = new PrintStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        System.out.println("Waiting for client...");
        clients.add(this);
    }


    @Override
    public void run() {

    }
}
