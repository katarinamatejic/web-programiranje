package rs.raf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Scanner;

public class SenderThread implements Runnable{
    private PrintWriter outputFromServer;
    private  Scanner scanner = new Scanner(System.in);


    public SenderThread(PrintWriter outputFromServer) {
        this.outputFromServer = outputFromServer;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) break;
            String message = scanner.nextLine();
            outputFromServer.println(message);
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
