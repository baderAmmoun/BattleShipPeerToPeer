package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class CreateShot {



    public CreateShot() {

        try {
            Socket socket = new Socket("localhost", 889);
            DataOutputStream out= new DataOutputStream(socket.getOutputStream());
            out.writeUTF("hallow world");
            DataInputStream in= new DataInputStream(socket.getInputStream());

            System.out.println("here is the number that have been sent"+in.readUTF());


        }
        catch (Exception e){}


    }
}
