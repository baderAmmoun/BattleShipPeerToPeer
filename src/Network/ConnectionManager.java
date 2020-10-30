package Network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
    private static ConnectionManager connectionManager;
    private final Map<String, Socket> sockets;

    private ConnectionManager() {

        System.out.println("the connection manger have been iniated one");
        this.sockets = new HashMap<>();
    }

    public static ConnectionManager getConnectionManger() {

        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public void addSocket(String name, String address, int port) {
        Socket socket = null;
        try {
            socket = new Socket("address", port);
        } catch (IOException e) {

        }
        this.sockets.put(name, socket);

    }

    public Socket getSocket(String name) {
        return this.sockets.get(name);
    }

    public void endSocket(String name) {
        Socket socket = this.sockets.remove(name);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Request request, int port) {
        Socket socket = sockets.get(request.getSenderPlayer());
        System.out.println("liten I will send the messahe right now but first i have to check if the socjet is here");
        System.out.println(sockets.get(request.getSenderPlayer()));
        try {
            if (socket == null) {

                System.out.println("the request have been send to the port" + port);
                socket = new Socket("localhost", port);
                sockets.put(request.getSenderPlayer(), socket);
            }
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendRespond(Respond respond){
        Socket socket = sockets.get(respond.getReceiverPlayer());
        System.out.println("I will send respond to the "+respond.getReceiverPlayer());
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(respond);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addSocket(Socket socket) {
        sockets.put("bader", socket);
    }

    public void receiveMessage(Socket socket) {

        System.out.println("the new thread have been started so you do not have to waite");
        while (true) {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                System.out.println("wait for a new connection");
                Request request = (Request) in.readObject();
                if(!this.sockets.containsKey(request.getSenderPlayer()))
                 sockets.put(request.getSenderPlayer(),socket);
                System.out.println(request.getSenderPlayer());

                Thread thread = new Thread(new CallBack(request));
                thread.start();

            } catch (Exception w) {
            }
        }
    }

}
