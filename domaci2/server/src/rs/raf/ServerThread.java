package rs.raf;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerThread implements Runnable {

    private Socket socket;
    public static CopyOnWriteArrayList<ServerThread> connections=new CopyOnWriteArrayList<>();
    private BufferedReader in ;
    private PrintWriter out ;


    public ServerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            String username;
            while (true) {
                out.println("Enter username");
                username=in.readLine();
                if(Main.userDB.contains(username)){
                    System.out.println("Username already exists, client rejected");
                    out.println("Username already exists");
                }else {
                    Main.userDB.add(username);
                    System.out.println(username + " has joined the chat");
                    out.println("Hey " + username + ", welcome!");
                    connections.add(this);
                    for (ServerThread c : connections) {
                        if (!c.equals(this)) {
                            c.out.println(username + " has joined the chat");
                        }
                    }
                    for (Message m : Main.messages) {
                        this.out.println(m.toString());
                    }
                }
                break;
            }
            while(true) {
                String message = in.readLine();
                if (message.equals("list messages")) {
                    out.println(Main.messages.toString());
                } else {
                    Message mess = new Message(message, username);
                    Main.messages.add(mess);
                    if(Main.messages.size()>100) Main.messages.remove(0);
                    System.out.println(mess.toString());
                    for (ServerThread con : connections) {//za svaku konekciju
                        con.out.println(mess.toString());
                    }

                }
            }



        } catch (IOException e) {
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

            if (this.socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }





}
