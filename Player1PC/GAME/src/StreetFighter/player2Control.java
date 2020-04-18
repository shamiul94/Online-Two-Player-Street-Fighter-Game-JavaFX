/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import static StreetFighter.MyPlayerControl.pl1life;
import static StreetFighter.MyPlayerControl.pl2life;
import static StreetFighter.Player1Move.kicked;
import static java.lang.Math.abs;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author RummanBUET
 */
public class player2Control{
    
    Rectangle r1 ;
    
    XYComponent ChangeofPosition ;
    
     public static   boolean punched2=false;
     public static   boolean kicked2=false;

    boolean Detect(Player1Body p1, Player2Body p2) {
        boolean t = false;

        if (abs(p2.Body.getX() - p1.Body.getX()) < 120) {
            t = true;
        }
        return t;
    }

    public player2Control(Scene scene, Player1Body p1, Player2Body p2, double ChangeX , double ChangeY , double InitialX1 , double InitialY1 ) {
        

        
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            double x, y;
            AudioClip plonkSound ;
            
            switch (e.getCode()) {
                case A:

                    System.out.println("left");

                    if (p2.Body.getX() - ChangeX > -20) {
                        x = (-1) * ChangeX;
                    } else {
                        x = 0;
                    }
                    y = 0;

                    ChangeofPosition = new XYComponent(x, y);
                    break;

                case D:

                    if (p2.Body.getX() + ChangeX < 1200 && Detect(p1, p2) == false) {
                        x = ChangeX;
                    } else {
                        x = 0;
                    }
                    y = 0;

                    ChangeofPosition = new XYComponent(x, y);
                    break;

                case W:

                    x = 0;
                    y = 0;
                    if (p2.Body.getY() > 180) {
                        y = -1 * ChangeY;

                    }
                    p2.setFall();
                    ChangeofPosition = new XYComponent(x, y);
                    break;

//                case S:
//                    p2.Body.setY(InitialY1);
//                    break;
//                    
                case Z:
                        Image punch = new Image("ken_16.jpg");
                        
                        p2.Body.setImage(punch);
                       punched2=true;
                       if(punched2 && abs(p2.Body.getX()-p1.Body.getX())<= 120){
                         sound.punch();
                         p2.Body.setImage(new Image("Ryu Attacked.png"));
                         pl1life= pl1life-5 ;
                         pl2life= pl2life+5 ;
                         
                         p2.r2.setWidth(pl2life*2.5);
                         p1.r1.setWidth(pl1life*2.5);
                    }
                        System.out.println(punched2);
                        break;
                  /*  case J:
                        Image jab = new Image("Ryu_jab.jpg");
                        player1.Body.setImage(jab);
                        break;
                */
                    case X:
                        Image kick = new Image("ken_45.jpg");
                        
                        p2.Body.setImage(kick);
                        kicked2=true;
                        if(kicked2 && abs(p2.Body.getX()-p1.Body.getX())<= 120){
                         sound.punch();
                         p2.Body.setImage(new Image("Ryu Attacked.png"));
                         pl2life= pl2life+10 ;
                         pl1life= pl1life-10 ;
                         
                         p2.r2.setWidth(pl2life*2.5);
                         p1.r1.setWidth(pl1life*2.5);
                    }
                        System.out.println(kicked2);
                        break;
                    case C:
                        Image super_punch = new Image("ken_04.jpg");
                        punched2 = true ;
                        if(punched2 && abs(p2.Body.getX()-p1.Body.getX())<= 120){
                         sound.punch();
                         p2.Body.setImage(new Image("Ryu Attacked.png"));
                         pl2life= pl2life+15 ;
                         pl1life= pl1life-15 ;
                         p2.r2.setWidth(pl2life*2.5);
                         p1.r1.setWidth(pl1life*2.5);
                    }
                        p2.Body.setImage(super_punch);
                        break;
                        
//                    case V:
//                        Image tornado_kick = new Image("Ryukick.png");
//                        sound.punch();
//                        //sound so4 = new sound();
//                        p2.Body.setImage(tornado_kick);
//                        break;
                    case B:
                        Image hadoken = new Image("g3w9O.gif");
                        //plonkSound = new AudioClip("file:2BH.wav");
                        punched2 = true ;
                        if(punched2 && abs(p2.Body.getX()-p1.Body.getX())<= 120){
                         sound.punch();
                         p2.Body.setImage(new Image("Ryu Attacked.png"));
                         pl2life= pl2life+20 ;
                         pl1life= pl1life-20;
                         p2.r2.setWidth(pl2life*2.5);
                         p1.r1.setWidth(pl1life*2.5);
                    }
                        p2.Body.setImage(hadoken);
                        break;
//                    case Z:
//                        Image had = new Image("ken_45.jpg");
//                        //plonkSound = new AudioClip("file:2BH.wav");
//                        sound.punch();
//                        p1.Body.setImage(had);
//                        break;

                default:

                    x = 0;
                    y = 0;
                    ChangeofPosition = new XYComponent(x, y);
                    break;
            }

            p2.UpdatePosition(ChangeofPosition);

        });
        
                scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            switch (e.getCode()) {
                default:
                    Image ryuidle = new Image("Ken.gif");
                    p2.Body.setImage(ryuidle);
                    //Image kenidle=new Image("file:pl2.gif");
                    punched2=false;
                    kicked2=false;
                    break;
            }
        });
        
            
        
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent f) {
//                double x, y;
//                int picNo ;
//                switch (f.getCode()) {
//                    case A:
//                        p1.Body.setImage(new Image("file:r2.png"));
//                        //picNo = 2 ;
//                        break;
//                        
//                    case W:
//                        p1.Body.setImage(new Image("file:r3.png"));
//                        //picNo = 3 ;
//                        break;
//                        
//                    case D:
//                        p1.Body.setImage(new Image("file:r4.png"));
//                        //picNo = 4 ;
//                        break;
//                        
//                    case X:
//                        p1.Body.setImage(new Image("file:r5.png"));
//                        //picNo = 5 ;
//                        break;
//                        
//                    default:
//                        p1.Body.setImage(new Image("file:r1.png"));
//                        //picNo = 1 ;
//                        break;
//                }   //p1.updatepic(picNo);
//            }
//        });

    }

}
