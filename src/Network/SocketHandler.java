package Network;

import java.net.Socket;

public class SocketHandler implements Runnable {

    private Socket socket;


    public SocketHandler(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        System.out.println("thats why the new manger instence created");
        ConnectionManager manager = ConnectionManager.getConnectionManger();
        System.out.println("I will start a new thread to serve this message");
        manager.receiveMessage(socket);

    }
}
