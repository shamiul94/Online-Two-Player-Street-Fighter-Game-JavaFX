/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import static StreetFighter.MyPlayerControl.player2;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author RummanBUET
 */
public class MessageReceiver implements Runnable {

    DatagramSocket ClientReceivingSocket;
    DatagramPacket DataPack;
    Message message;
    Player2Body p ;
    Thread th;

    MessageReceiver(DatagramSocket sock){
        ClientReceivingSocket = sock;
        th = new Thread(this);
        th.start();
    }


    @Override
    public void run() {
        while (true) {
            byte[] Byte = new byte[1024];
            DataPack = new DatagramPacket(Byte, Byte.length);

            try {
                ClientReceivingSocket.receive(DataPack);
            } catch (IOException ex) {
                   //System.out.println("Receive error");
            }
            message = new Message(DataPack.getData(), DataPack.getLength());
            System.out.println("Rumman is Receiving this: "+ message.TotalMessage );

            player2.move(message.Key);
            
            //ClientReceivingSocket.close();
        }
    }

    public Message getMessage() {
        return message;
    }
}

