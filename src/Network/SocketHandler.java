package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler implements Runnable{

    private Socket socket;
    public SocketHandler(Socket socket){
        this.socket=socket;

    }
    @Override
    public void run() {
        System.out.println("the request is being procssed");

        try {

            DataInputStream in= new DataInputStream(socket.getInputStream());
            System.out.println("something goes wrong");
            //OutputStreamWriter out=new OutputStreamWriter(socket.getOutputStream());
            System.out.println("here is the number that have been sent"+in.readUTF());
        } catch (IOException e) {
            System.out.println("something goes wrong");
            e.printStackTrace();
        }


    }
}
