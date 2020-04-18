/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import static StreetFighter.MyPlayerControl.ChangeX;
import static StreetFighter.MyPlayerControl.ChangeY;
import static StreetFighter.MyPlayerControl.pl1life;
import static StreetFighter.MyPlayerControl.pl2life;
import static StreetFighter.MyPlayerControl.player1;
import static StreetFighter.MyPlayerControl.player2;
import static StreetFighter.Player1Move.punched;
import static StreetFighter.Player1Move.kicked;
import static java.lang.Math.abs;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RummanBUET
 */
public class Player2Body {

    Rectangle r2;

    double dx;
    double dy;
    String Key;
    Image iv;
    Image[] imgarr;
    ImageView Body;

    public static boolean punched2 = false, kicked2 = false;

    public Player2Body(Group root, double x, double y, double w, double h) {

        r2 = new Rectangle(800, 20, 250, 25);
        r2.setFill(Color.GREEN);

        iv = new Image("file:pl2.gif");
        Body = new ImageView();
        Body.setImage(iv);

        Body.setX(x);
        Body.setY(y);
        Body.setFitWidth(150);
        Body.setFitHeight(600);

        Body.setPreserveRatio(true);
        Body.setSmooth(true);
        Body.setCache(true);
        HBox box = new HBox();
        if (pl2life <= 50 && pl2life >= 25) {
            r2.setFill(Color.YELLOW);
        }
        if (pl1life < 25 && pl1life >= 0) {
            r2.setFill(Color.RED);
        }

        root.getChildren().add(r2);

        root.getChildren().add(Body);
        root.getChildren().add(box);
    }

    public void UpdatePosition(double x, double y, int picNo) {
        Body.setX(x);
        Body.setY(y);

        if (picNo == 1) {
            Body.setImage(new Image("file:k1.jpg"));
        } else if (picNo == 2) {
            Body.setImage(new Image("file:k2.jpg"));
        } else if (picNo == 3) {
            Body.setImage(new Image("file:k3.jpg"));
        } else if (picNo == 4) {
            Body.setImage(new Image("file:k4.jpg"));
        } else if (picNo == 5) {
            Body.setImage(new Image("file:k5.jpg"));
        } else {
            Body.setImage(new Image("file:k1.jpg"));
        }

    }

    public void UpdatePosition(XYComponent chng) {
        dx = chng.dx;
        dy = chng.dy;

        Body.setX(Body.getX() + dx);
        Body.setY(Body.getY() + dy);

    }

    public double dedy = 1;

    public void setFall() {
        dedy = 1;
    }

    public void manageGravity() {
        if (Body.getY() < 310) {
            double nDy = Body.getY() + dedy;
            dedy = 1.25 * dedy;
            Body.setY(nDy);
        }
    }

    boolean Detect(Player1Body player1, Player2Body player2) {
        boolean t = false;

        if (abs(player2.Body.getX() - player1.Body.getX()) < 120) {
            t = true;
        }
        return t;
    }

    public void move(String key) {

        XYComponent ChangeofPosition = new XYComponent(0, 0);

        Key = key;

        double x, y;

        if (Key.equals("L")) {

            System.out.println("left");

            //Key = "L";
            if (player2.Body.getX() - ChangeX > -20 && Detect(player1, player2) == false) {
                x = (-1) * ChangeX;
            } else {
                x = 0;
            }
            y = 0;

            ChangeofPosition = new XYComponent(x, y);

        } else if (Key.equals("R")) {

            //Key = "R";
            if (player2.Body.getX() + ChangeX < 1200) {
                x = ChangeX;
            } else {
                x = 0;
            }
            y = 0;

            ChangeofPosition = new XYComponent(x, y);

        } else if (Key.equals("U")) {

            x = 0;
            y = 0;
            if (player2.Body.getY() > 180) {
                y = -1 * ChangeY;

            }
            player2.setFall();
            ChangeofPosition = new XYComponent(x, y);

        } else if (Key.equals("P")) {

            Image punch = new Image("ken_16.jpg");

            player2.Body.setImage(punch);
            punched2 = true;
            if (punched2 && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                sound.punch();
                player1.Body.setImage(new Image("ryuattacked.gif"));
                pl2life = pl2life + 5;
                pl1life = pl1life - 5;
                player2.r2.setWidth(pl2life * 2.5);
                player1.r1.setWidth(pl1life * 2.5);
            }
            System.out.println(punched2);

        } else if (Key.equals("K")) {
      
            Image kick = new Image("ken_45.jpg");
            sound.punch();
            player2.Body.setImage(kick);
            kicked2 = true;
            if (kicked2 && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                sound.punch();
                player1.Body.setImage(new Image("ryuattacked.gif"));
                pl2life = pl2life+ 10;
                pl1life = pl1life - 10;
                player2.r2.setWidth(pl2life * 2.5);
                player1.r1.setWidth(pl1life * 2.5);
            }
            System.out.println(kicked2);

        } else if (Key.equals("S")) {
        
            Image super_punch = new Image("ken_04.jpg");
            punched2 = true;
            player2.Body.setImage(super_punch);
            if (punched2 && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                sound.punch();
                player1.Body.setImage(new Image("ryuattacked.gif"));
                pl2life = pl2life + 15;
                pl1life = pl1life - 15;
                player2.r2.setWidth(pl2life * 2.5);
                player1.r1.setWidth(pl1life * 2.5);

            }
        } else if (Key.equals("T")) {
      
            Image tornado_kick = new Image("ken_45.jpg");
            kicked2 = true;
            player2.Body.setImage(tornado_kick);
            if (kicked2 && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                sound.punch();
                player1.Body.setImage(new Image("ryuattacked.gif"));
                pl2life = pl2life + 20;
                pl1life = pl1life - 20;
                player2.r2.setWidth(pl2life * 2.5);
                player1.r1.setWidth(pl1life * 2.5);
            }

        } else if (Key.equals("M")) {
            Image hadoken = new Image("g3w9O.gif");
            //plonkSound = new AudioClip("file:2BH.wav");
            kicked2 = true;
            player2.Body.setImage(hadoken);
            if (kicked2 && abs(player2.Body.getX() - player1.Body.getX()) <= 120) {
                sound.punch();
                player1.Body.setImage(new Image("ryuattacked.gif"));
                pl2life = pl2life + 25;
                pl1life = pl1life - 25;
                player2.r2.setWidth(pl2life * 2.5);
                player1.r1.setWidth(pl1life * 2.5);
            }
        } else {

            x = 0;
            y = 0;
            ChangeofPosition = new XYComponent(x, y);
        }

        UpdatePosition(ChangeofPosition);
        ChangeofPosition.Reset();

        Image kenidle = new Image("Ken.gif");
        player2.Body.setImage(kenidle);
        Image ryuidle = new Image("file:RYU.gif");
        player1.Body.setImage(ryuidle);
        punched = false ;
        kicked = false ;
        punched2 = false;
        kicked2 = false;
    }
}
