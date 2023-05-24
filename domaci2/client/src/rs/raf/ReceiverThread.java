package rs.raf;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiverThread implements Runnable {
    private BufferedReader inputFromServer;

    public ReceiverThread(BufferedReader inputFromServer){
        this.inputFromServer=inputFromServer;
    }


    @Override
    public void run() {
        while (true) {
            try {
                String message = inputFromServer.readLine();
                if (message.equals("Status code: Exit") || Thread.currentThread().isInterrupted()) {
                    break;
                }
                else {
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
