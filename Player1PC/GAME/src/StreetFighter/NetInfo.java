/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import java.net.InetAddress;

/**
 *
 * @author RummanBUET
 */
public class NetInfo {
    InetAddress IP ;
    int ClientPort ;

    public NetInfo(InetAddress ip , int port) {
        IP = ip ;
        ClientPort = port ;
    }
}
