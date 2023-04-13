package MultiChat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static void main(String[] args) throws IOException {

        ServerSocket s = new ServerSocket(Server.PORT);

        //System.out.println("Server running & waiting for client connections.");
        while(!s.isClosed()) {
            try {
                System.out.println("Server running & waiting for client connections.");
                Socket socket = s.accept();
                System.out.println("Connection: " + socket);
                Runnable r = new Server(socket);
                Thread task = new Thread(r);
                task.start();
            } catch(IOException ex) {
                s.close();
                System.out.println("[SERVER]: Shutting down...");
            }
        }
    }
}