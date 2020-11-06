package Network;

import Configuration.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private int port;
    public Server(){
        this.port= Integer.parseInt(Config.getConfig("localConf.properties").getValue("port"));
    }
    public void run() {
        try {

            System.out.println("the server has been created on port"+port);
            serverSocket = new ServerSocket(port);
            executorService = Executors.newCachedThreadPool(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable runnable) {
                    Thread t = Executors.defaultThreadFactory().newThread(runnable);
                    t.setDaemon(true);
                    return t;
                }
            });
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
