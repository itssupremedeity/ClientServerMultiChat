package MultiChat.Server;

import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private String clientName;


    public ClientHandler(Socket socket, String name) {
        this.socket = socket;
        this.clientName = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getClientName() {
        return clientName;
    }
}
