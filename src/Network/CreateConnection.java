//package Network;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//
////public class CreateConnection {
////
////    public static void CreateConnection(String name, String address, int port, EndPoint endPoint) {
////
////        ConnectionManager manger = ConnectionManager.getConnectionManger();
////        if (manger.getSocket(name) == null) {
////            manger.addSocket(name, address, port);
////        }
////        Socket socket = manger.getSocket(name);
////        try {
////            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
////            DataInputStream in = new DataInputStream(socket.getInputStream());
////            System.out.println("here is the number that have been sent" + in.readUTF());
////            out.writeUTF(endPoint.onCommand());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////    }
//}
