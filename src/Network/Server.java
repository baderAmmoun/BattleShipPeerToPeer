package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    public void run() {
        try {
            int port=889;
            System.out.println("the server has been created on port"+port);
            serverSocket = new ServerSocket(port);
            executorService = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                System.out.println("the server is waiting for client.......");
                Socket client = serverSocket.accept();
                executorService.execute(new SocketHandler(client));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
