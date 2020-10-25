package Network;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CreateShot {



    public CreateShot() {

        try {
            Socket socket = new Socket("localhost", 889);
            DataOutputStream out= new DataOutputStream(socket.getOutputStream());
            out.writeUTF("hellow world");

        }
        catch (Exception e){}


    }
}
