package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler implements Runnable{

    private Socket socket;
    private EndPoint endPoint;
    public SocketHandler(Socket socket,EndPoint endPoint){
        this.socket=socket;
        this.endPoint=endPoint;

    }
    @Override
    public void run() {
        System.out.println("the request is being procssed");

        try {

            DataInputStream in= new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            //System.out.println("here is the number that have been sent"+in.readUTF());
            endPoint.onCommand(in.readUTF());
            out.writeUTF("hi from the other instance of the application");


        } catch (IOException e) {
            System.out.println("something goes wrong");
            e.printStackTrace();
        }


    }
}
