package MultiChat.Server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private String clientName;

    private final PrintStream out;


    public ClientHandler(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.clientName = name;
        out = new PrintStream(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public String getClientName() {
        return clientName;
    }

    public void sendMsg(String msg){
        out.println(msg);
        out.flush();
    }
}
