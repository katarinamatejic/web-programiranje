package rs.raf;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static final int PORT = 9001;

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("localhost", 9001);
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            Thread receiverThread = new Thread(new ReceiverThread(inputFromServer));
            Thread senderThread = new Thread(new SenderThread(outputToServer));
            receiverThread.start();
            senderThread.start();
            receiverThread.join();
            senderThread.join();
            socket.close();
            inputFromServer.close();
            outputToServer.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
