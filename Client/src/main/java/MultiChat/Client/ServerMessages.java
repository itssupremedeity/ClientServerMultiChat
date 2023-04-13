package MultiChat.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerMessages implements Runnable {

    public static final int PORT = 3000;
    private final BufferedReader in;
    private final Socket socket;


    public ServerMessages(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
    }

    @Override
    public void run() {

        String msgFromServer;

        while (socket.isConnected()){
            try {
                msgFromServer = in.readLine();
                System.out.println(msgFromServer);
            }
            catch (IOException e){
                try {
                    in.close();
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
