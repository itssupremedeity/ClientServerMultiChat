package MultiChat.Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    static Scanner scanner;
    static ServerMessages server;

    static PrintStream out;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        System.out.println("Hello Enter Your Name To Enter The Chat: ");
        String userName = scanner.nextLine();

        try {
            Socket socket = new Socket("localhost", ServerMessages.PORT);
            out = new PrintStream(socket.getOutputStream());
            server = new ServerMessages(socket);
            Thread r = new Thread(server);
            r.start();

            out.println(userName);
            out.flush();

            while (socket.isConnected()){
                System.out.println("Enter Message: ");
                out.println(scanner.nextLine());
                out.flush();
            }

        } catch (UnknownHostException e) {
            out.close();
            throw new RuntimeException(e);
        } catch (IOException e) {
            out.close();
            throw new RuntimeException(e);
        }
    }
}