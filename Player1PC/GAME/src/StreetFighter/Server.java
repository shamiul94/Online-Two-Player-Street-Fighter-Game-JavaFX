package StreetFighter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Hashtable;

/**
 *
 * @author RummanBUET
 */
public class Server implements Runnable {

    Hashtable<String, NetInfo> Map;

    Thread th;

    String ServerName;
    Message message;
    int ServerPort;

    DatagramSocket InSocket;
    DatagramSocket OutSocket;

    DatagramPacket DataPack;

    Server() throws SocketException, UnknownHostException {
        Map = new Hashtable();
        ServerName = "StreetServer";
        ServerPort = 4444;

        OutSocket = new DatagramSocket();
        InSocket = new DatagramSocket(ServerPort);

        th = new Thread(this);
        th.start();
    }

    private void SendAndReceive() throws UnknownHostException {
        byte[] Byte = new byte[1024];
        DataPack = new DatagramPacket(Byte, Byte.length);

        try {
            InSocket.receive(DataPack);
           
        } catch (Exception ex) {
        }

        message = new Message(DataPack.getData(), DataPack.getLength());
        // System.out.println("hi server");
        System.out.println("Server is receiving: "+ message.TotalMessage);
        if (message.OpID == 1) {
            //System.out.println(message.OpID);
            if (!((message.ServerName).equals(this.ServerName))) {
                //System.out.println(message.ServerName + " " + this.ServerName);
                System.out.println("Server Name Mismatch. LogIn Denied.");
            } else {
                Map.put(message.UserName, new NetInfo(InetAddress.getByName(message.InputIP), message.SenderPort));
                System.out.println("New Connection");
                System.out.println(Map.get(message.UserName));
                //Map.put(message.UserName, new NetInfo(DataPack.getAddress(), message.SenderPort));
            }
        } else if (message.OpID == 2) {
            NetInfo Info;
            Info = Map.get(message.UserName);

            if (Info == null) {
                System.out.println("Other Player Is Not OnLine Right Now. Try Later.");
            } else {
                DataPack.setAddress(Info.IP);
                DataPack.setPort(Info.ClientPort);

                try {
                    OutSocket.send(DataPack);
                } catch (IOException ex) {
                }
                System.out.println("Server Sending");
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                SendAndReceive();
            } catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args)  {
        try {
            new Server();
        } catch (SocketException ex) {
        } catch (UnknownHostException ex) {
        }
    }
}
