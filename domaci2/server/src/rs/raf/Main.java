package rs.raf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static final int PORT = 9001;
    public static CopyOnWriteArrayList<Message> messages=new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<String> userDB=new CopyOnWriteArrayList<>();

    public static ConcurrentHashMap<String, String > forbiddenWords = new ConcurrentHashMap<>();
    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(Main.PORT);
            System.out.println("Server is up");
            while (true) {
                //u ovom momentu smo primili konekciju novog klijenta
                Socket socket = serverSocket.accept();
                Thread serverThread = new Thread(new ServerThread(socket));
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
