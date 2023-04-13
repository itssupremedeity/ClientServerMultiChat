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
    private ClientHandler clientHandler;
    private String clientName;

    public Server(Socket socket) throws IOException {
        String clientMachine = socket.getInetAddress().getHostName();
        System.out.println("Connection from " + clientMachine);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        clientName = in.readLine();
        this.clientHandler = new ClientHandler(socket,clientName);
        clients.add(this);
    }

    @Override
    public void run() {

        serverBroadcast("SERVER: " + clientHandler.getClientName() + " Has Entered The Chat...");

        String msgFromClient;

        while (clientHandler.getSocket().isConnected()){
            try {
                msgFromClient = in.readLine();
                serverBroadcast(clientHandler.getClientName() + ": " + msgFromClient);
            }
            catch (IOException e){
                closeEverything();
            }
        }
    }

    private void serverBroadcast(String msg){
        for(Server client : clients) {
            if(!client.clientHandler.getClientName().equals(clientName)){
                client.clientHandler.sendMsg(msg);
            }
        }
    }

    private void closeEverything(){
        try {
            in.close();
            clientHandler.getSocket().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
