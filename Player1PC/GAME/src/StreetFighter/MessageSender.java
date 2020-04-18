/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author RummanBUET
 */
public class MessageSender{
    Thread th ;
    DatagramSocket ClientSendingSocket ;
    Message message ;
    DatagramPacket DataPack ;
    InetAddress IP ;
    int ServerPort ;
    byte[] Arr ;
// Sender's job is to send dattapack FROM CLIENT to "ONLY" SEREVER.
// NOTHING ELSE. REMEMBER CAREFULLY.  
    public MessageSender(DatagramSocket sock , Message msg) throws UnknownHostException {

        ClientSendingSocket = sock ;
        message = msg ;
        ServerPort = 4444 ;
      
        IP = InetAddress.getByName("172.20.53.66"); // Server's IP.
         Arr = message.Byte ;
            DataPack = new DatagramPacket(Arr, Arr.length, IP , ServerPort);
            try {
                ClientSendingSocket.send(DataPack);
            } catch (IOException ex) {
            } 
            System.out.println("Rumman is Sending this: "+ message.message); 
      
    }

}
