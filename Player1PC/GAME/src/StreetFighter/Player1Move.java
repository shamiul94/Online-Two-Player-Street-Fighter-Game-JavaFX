/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import static StreetFighter.MyPlayerControl.pl1life;
import static StreetFighter.MyPlayerControl.pl2life;
import static StreetFighter.MyPlayerControl.player1;
import static StreetFighter.MyPlayerControl.player2;
import static StreetFighter.Player2Body.kicked2;
import static StreetFighter.Player2Body.punched2;
import static java.lang.Math.abs;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author RummanBUET
 */
public class Player1Move {

    XYComponent ChangeofPosition;

    DatagramSocket SendingKeySocket;

    public static boolean punched = false;
    public static boolean kicked = false;

    String Key;

    boolean Detect(Player1Body player1, Player2Body player2) {
        boolean t = false;

        if (abs(player2.Body.getX() - player1.Body.getX()) < 120) {
            t = true;
        }
        return t;
    }

    Player1Move(Scene scene, DatagramSocket sock, double ChangeX, double ChangeY, double InitialX1, double InitialY1) {

        SendingKeySocket = sock;

        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            double x, y;

            switch (e.getCode()) {
                case LEFT:

                    System.out.println("left");

                    Key = "L";
                    if (player1.Body.getX() - ChangeX > -20) {
                        x = (-1) * ChangeX;
                    } else {
                        x = 0;
                    }
                    y = 0;

                    ChangeofPosition = new XYComponent(x, y);
                    break;

                case RIGHT:

                    Key = "R";
                    if (player1.Body.getX() + ChangeX < 1200 && Detect(player1, player2) == false) {
                        x = ChangeX;
                    } else {
                        x = 0;
                    }
                    y = 0;

                    ChangeofPosition = new XYComponent(x, y);
                    break;

                case UP:

                    Key = "U";
                    x = 0;
                    y = 0;
                    if (player1.Body.getY() > 180) {
                        y = -1 * ChangeY;

                    }
                    player1.setFall();
                    ChangeofPosition = new XYComponent(x, y);
                    break;

                case P:

                    Key = "P";
                    Image punch = new Image("ryupunchsimple.png");

                    player1.Body.setImage(punch);
                    punched = true;
                    if (punched && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                        sound.punch();
                        player2.Body.setImage(new Image("ken_46.jpg"));
                        pl2life = pl2life - 5;
                        pl1life = pl1life + 5;
                        player2.r2.setWidth(pl2life * 2.5);
                        player1.r1.setWidth(pl1life * 2.5);
                    }
                    System.out.println(punched);
                    break;

                case K:
                    Key = "K";
                    Image kick = new Image("Ryu normal kick.png");
                    sound.punch();
                    player1.Body.setImage(kick);
                    kicked = true;
                    if (kicked && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                        sound.punch();
                        player2.Body.setImage(new Image("ken_46.jpg"));
                        pl2life = pl2life - 10;
                        pl1life = pl1life + 10;
                        player2.r2.setWidth(pl2life * 2.5);
                        player1.r1.setWidth(pl1life * 2.5);
                    }
                    System.out.println(kicked);
                    break;

                case S:
                    Key = "S";
                    Image super_punch = new Image("RYupunch.png");
                    punched = true;
                    player1.Body.setImage(super_punch);
                    if (punched && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                        sound.punch();
                        player2.Body.setImage(new Image("ken_46.jpg"));
                        pl2life = pl2life - 15;
                        pl1life = pl1life + 15;
                        player2.r2.setWidth(pl2life * 2.5);
                        player1.r1.setWidth(pl1life * 2.5);

                    }
                    break;

                case T:
                    Key = "T";
                    Image tornado_kick = new Image("Ryukick.png");
                    kicked = true;
                    player1.Body.setImage(tornado_kick);
                    if (kicked && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                        sound.punch();
                        player2.Body.setImage(new Image("ken_46.jpg"));
                        pl2life = pl2life - 20;
                        pl1life = pl1life + 20;
                        player2.r2.setWidth(pl2life * 2.5);
                        player1.r1.setWidth(pl1life * 2.5);
                    }
                    //sound so4 = new sound();

                    break;

                case M:
                    Key = "M";
                    Image hadoken = new Image("g3w9O.gif");
                    //plonkSound = new AudioClip("file:2BH.wav");
                    kicked = true;
                    player1.Body.setImage(hadoken);
                    if (kicked && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                        sound.punch();
                        player2.Body.setImage(new Image("ken_46.jpg"));
                        pl2life = pl2life - 25;
                        pl1life = pl1life + 25;
                        player2.r2.setWidth(pl2life * 2.5);
                        player1.r1.setWidth(pl1life * 2.5);
                    }
                    break;

                default:

                    x = 0;
                    y = 0;
                    ChangeofPosition = new XYComponent(x, y);
                    break;
            }

            Message msg;
            msg = new Message("Samin", Key);
            try {
                MessageSender Sender = new MessageSender(SendingKeySocket, msg);
            } catch (UnknownHostException ex) {
            }

            player1.UpdatePosition(ChangeofPosition);

        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            switch (e.getCode()) {
                default:
                    Image ryuidle = new Image("RYU.gif");
                    Image kenidle = new Image("file:pl2.gif");
                    player1.Body.setImage(ryuidle);
                    player2.Body.setImage(kenidle);
                    punched = false;
                    kicked = false;
                    punched2 = false;
                    kicked2 = false;
                    break;
            }
        });

    }

}
