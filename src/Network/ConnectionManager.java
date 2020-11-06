package Network;


import Configuration.Config;

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
        System.out.println("the port is "+port);
        Socket socket = sockets.get(request.getReceiverPlayer());
        System.out.println("listen I will send the message right now but first i have to check if the socket is here");
        System.out.println(sockets.get(request.getReceiverPlayer()));
        try {
            if (socket == null) {

                System.out.println("the request have been send to the port" + port);
                socket = new Socket(Config.getConfig().getValue("serverName"), port);
                sockets.put(request.getReceiverPlayer(), socket);
            }
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(request);

        } catch (Exception e) {
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
                ExchangeableMessage request = (ExchangeableMessage) in.readObject();
                if(!this.sockets.containsKey(request.getSenderPlayer())) {
                    Socket clientSocket = new Socket(Config.getConfig().getValue("serverName"), Integer.parseInt(Config.getConfig().getValue("remotePort")));
                    sockets.put(request.getSenderPlayer(), clientSocket);
                }
                Thread thread = new Thread(new CallBack(request));
                thread.setDaemon(true);
                thread.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
