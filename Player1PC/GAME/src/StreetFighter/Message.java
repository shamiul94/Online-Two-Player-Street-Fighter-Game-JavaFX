package StreetFighter;

import java.util.StringTokenizer;

/**
 *
 * @author RummanBUET
 */
public class Message {

    byte[] Byte = new byte[1024];
    String msg, TotalMessage;
    String UserName, ClientPort, InputIP, ServerName, ReceiverName;
    String ID, PicsNum, StrX, StrY;
    String Key ;
    String message;
    double X, Y;
    int PicNo, SenderPort;
    int OpID;

    Message(String username, String clientport, String iip, String Srvname) {
        UserName = username;
        ClientPort = clientport;
        InputIP = iip;
        ServerName = Srvname;
        ID = "1";

        message = ID + "," + UserName + "," + ClientPort + "," + InputIP + "," + ServerName + ",";
        Byte = message.getBytes();
    }

    Message(String username, String key) {
        
        ID = "2";
        ReceiverName = username;
        
        Key = key ;
        message = ID + "," + ReceiverName + "," + Key + ",";
        Byte = message.getBytes();
    }

    Message(byte[] arr, int length) {
        Byte = arr;
        TotalMessage = new String(Byte);

        ID = TotalMessage.substring(0, 1);
        StringTokenizer Token = new StringTokenizer(TotalMessage, ",", false);

        //System.out.println(TotalMessage);
        if (ID.equals("1")) {

            ID = Token.nextToken();
            UserName = Token.nextToken();
            ClientPort = Token.nextToken();
            InputIP = Token.nextToken();
            ServerName = Token.nextToken();
            //System.out.println(UserName);
            System.out.println("Server Received from Rumman: ");
            System.out.println("ID: " + ID);
            System.out.println("UserName: " + UserName);
            System.out.println("Port: " + ClientPort);
            System.out.println("IP: " + InputIP);
            System.out.println("ServerName: " + ServerName);

            SenderPort = Integer.parseInt(ClientPort);

            OpID = Integer.parseInt(ID);
        } else if (ID.equals("2")) {
            ID = Token.nextToken();
            UserName = Token.nextToken();//ReceiverName.
            Key = Token.nextToken();
            
            OpID = Integer.parseInt(ID);

            System.out.println("Server Received From Samin: ");
            System.out.println("ID: " + ID);
            System.out.println("UserName: " + UserName);
  
        }
    }

}
