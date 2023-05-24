package pomocni.http;

import com.google.gson.Gson;
import glavni.http.HttpMethod;
import pomocni.storage.QuoteStorage;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.StringTokenizer;

public class ServerThread implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson = new Gson();

    public ServerThread(Socket socket) {
        try{
            this.socket = socket;
            in = new BufferedReader( new InputStreamReader( socket.getInputStream()));
            out = new PrintWriter( new OutputStreamWriter( socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String request = in.readLine();
            StringTokenizer tokenizer = new StringTokenizer(request); //allows an application to break a string into tokens.

            String method = tokenizer.nextToken();
            String path = tokenizer.nextToken();


            System.out.println("\n HTTP ZAHTEV KLIJENTA: \n");
            int counter = 0;
            do{
                System.out.println(request);
                System.out.println(counter++);
                request = in.readLine();
            } while (!request.trim().equals(""));
            Random helper = new Random();
            if (method.equals(HttpMethod.GET.toString()) && path.equals("/")){
                StringBuilder quoteForToday = new StringBuilder();
                int random = helper.nextInt(8); //velicina liste u quoteStorage je 8
                quoteForToday.append("HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n");
                quoteForToday.append(gson.toJson(QuoteStorage.getInstance().getQuotes().get(random)));  //prevodi

                System.out.println("\n QUOTE FOR TODAY: \n");
                System.out.println(quoteForToday.toString());

                out.println(quoteForToday.toString());
            } else { //za slucaj da je poslat pogresan zahtev
                StringBuilder response = new StringBuilder();
                response.append("HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\n\r\n");
                out.println(response.toString());
            }

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
