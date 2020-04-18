/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StreetFighter;

import static StreetFighter.MyPlayerControl.pl1life;
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
public class Player1Body {

    Rectangle r1 ;
    double dx;
    double dy;
    Image iv;
    Image[] imgarr ;
    ImageView Body;

    public Player1Body(Group root, double x, double y, double w, double h) {
        
        r1 = new Rectangle(20, 20, 250, 25);
        r1.setFill(Color.GREEN);
        
        
        iv = new Image("file:r1.gif");
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
        
        if(pl1life <= 50 && pl1life >= 25){
            r1.setFill(Color.YELLOW);
        }
        if(pl1life < 25 && pl1life>= 0){
            r1.setFill(Color.RED);
        }
        
        
        root.getChildren().add(Body);
        root.getChildren().add(r1);
        root.getChildren().add(box);
    }

    public void UpdatePosition(XYComponent chng) {
        dx = chng.dx;
        dy = chng.dy;

        Body.setX(Body.getX() + dx);
        Body.setY(Body.getY() + dy);

    }
    
   
    public double dedy=1;
    public void setFall(){
        dedy=1;
    }
    
    public void manageGravity(){
        if(Body.getY()<310){
            double nDy=Body.getY()+dedy;
            dedy=1.25*dedy;
            Body.setY(nDy);
        }
    }
}
