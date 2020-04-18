package StreetFighter;

import static StreetFighter.MyPlayerControl.pl1life;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

/**
 *
 * @author RummanBUET
 */
public class MyPlayerControl extends Application {

    public static int pl1life = 100, pl2life = 100;

    Group root;

    DatagramSocket SendingtoServerSocket;
    DatagramSocket SendtoOpponentSocket;
    DatagramSocket ReceivingSocket;
    int ReceivingPort;

    public String UserName;
    public String ClientPort;
    public String IP;
    public String ServerName;

    public static double InitialX1 = 50, InitialX2 = 750;
    public static double InitialY1 = 310, InitialY2 = 340;
    public static double ChangeX = 60, ChangeY = 120;

    double PlayerWidth = 150, PlayerHeight = 200;
    long lastFrame = 0;
    long frameCount = 0;

    XYComponent ChangeofPosition = new XYComponent(0, 10);
    Text fps = new Text(0, 30, "Y");

    public static Player1Body player1;
    public static Player2Body player2;

    private void updateGameState(long now) throws UnknownHostException {

        if (pl1life <= 0 || pl2life <= 0) {

            if (pl1life <= 0) {//ryu lose.ken win
                Image im = new Image("kenwin.jpg");
                ImageView iv = new ImageView(im);
                iv.setX(0);
                iv.setY(0);
                iv.setFitHeight(600);
                iv.setFitWidth(1200);
                root.getChildren().add(iv);
            } else {
                Image im = new Image("ryuwin.gif");
                ImageView iv = new ImageView(im);
                iv.setX(0);
                iv.setY(0);
                iv.setFitHeight(600);
                iv.setFitWidth(1200);
                root.getChildren().add(iv);
            }
        }
        player1.manageGravity();
        player2.manageGravity();

    }

    public void TextFieldSetter(Group root, TextField tf, int ID, String str, String label) {
        double YofFirstBox = 80, diff = 100;
        tf.setLayoutX(370);
        tf.setLayoutY(YofFirstBox + (ID - 1) * diff);
        tf.setPrefSize(335, 35);
        tf.setPromptText(str);
        Label lbl = new Label(str);
        lbl.setFont(new Font("Arial", 20));
        lbl.setLayoutX(100);
        lbl.setLayoutY(tf.getLayoutY() - tf.getLength() / 2);
        root.getChildren().add(lbl);
        root.getChildren().add(tf);

    }

    public void game() throws SocketException, UnknownHostException {
        root = new Group();
        Stage primaryStage = new Stage();

        Scene scene = new Scene(root, 1200, 600);
        Image back = new Image("file:Dream.gif");

        AudioClip plonkSound = new AudioClip("file:Stairwaytoheaven.m4a");
        plonkSound.setVolume(100);
        plonkSound.play();

        ImageView iv = new ImageView(back);
        iv.setX(0);
        iv.setY(0);
        iv.setFitWidth(1200);
        iv.setFitHeight(600);
        iv.rotationAxisProperty();

        iv.setCache(true);

        root.getChildren().add(iv);

        player1 = new Player1Body(root, InitialX1, InitialY1, PlayerWidth, PlayerHeight);
        player2 = new Player2Body(root, InitialX2, InitialY2, PlayerWidth, PlayerHeight);

        SendtoOpponentSocket = new DatagramSocket();

        ReceivingPort = Integer.parseInt(ClientPort);
        ReceivingSocket = new DatagramSocket(ReceivingPort);

        MessageReceiver Receiver = new MessageReceiver(ReceivingSocket);

        Player1Move Move1 = new Player1Move(scene, SendtoOpponentSocket, ChangeX, ChangeY, InitialX1, InitialY1);
        //player2Move Move2 = new player2Control(scene,ReceivingSocket, ChangeX, ChangeY, InitialX2, InitialY2);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    updateGameState(now);
                } catch (UnknownHostException ex) {
                }
            }
        }.start();

        primaryStage.setTitle("Street Fighter");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();

        TextField UserNameField = new TextField();
        TextFieldSetter(root, UserNameField, 1, "User Name", "User Name");

        TextField ClientPortField = new TextField();
        TextFieldSetter(root, ClientPortField, 2, "Port", "Client Port");

        TextField ServerIPField = new TextField();
        TextFieldSetter(root, ServerIPField, 3, "IP", "Server IP");

        TextField ServerNameField = new TextField();
        TextFieldSetter(root, ServerNameField, 4, "Server", "Server Name");

        Button btn = new Button("Connect");
        btn.setStyle("-fx-font: 22 arial; -fx-base: #FFFFFF;");

        btn.setLayoutX(320);
        btn.setLayoutY(480);
        btn.setPrefSize(140, 55);
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 800, 600);
        final Label label = new Label("Log In Successful");

        btn.setOnAction(e -> {

            UserName = UserNameField.getText();
            System.out.println("UserName: " + UserName);
            ClientPort = ClientPortField.getText();
            System.out.println("Port: " + ClientPort);
            IP = ServerIPField.getText();
            System.out.println("IP: " + IP);
            ServerName = ServerNameField.getText();
            System.out.println("ServerName: " + ServerName);

            if (ServerName.equals("StreetServer") && ServerName.length() != 0 && UserName.length() != 0 && ClientPort.length() != 0 && IP.length() != 0) {
                label.setText("Log In Successful");
                label.setFont(new Font("Arial", 20));
                label.setLayoutX(330);
                label.setLayoutY(20);
                root.getChildren().add(label);

                try {
                    SendingtoServerSocket = new DatagramSocket();
                    Message msg = new Message(UserName, ClientPort, IP, ServerName);
                    //System.out.println(msg.ServerName);
                    MessageSender Sender = new MessageSender(SendingtoServerSocket, msg);

                    Stage stg = new Stage();
                    Group grp = new Group();
                    Scene scn = new Scene(grp, 600, 600);

                    Image ig = new Image("file:cover.jpg");
                    ImageView igv = new ImageView(ig);
                    
                    igv.setX(0);
                    igv.setY(0);
                    
                    igv.setFitWidth(600);
                    igv.setFitHeight(600);
                    
                    grp.getChildren().add(igv);
                    
                    Button play = new Button("Play");

                    play.setStyle("-fx-font: 22 arial; -fx-base: #FFFFFF;");
//1505038.sh@ugrad.cse.buet.ac.bd
                    
                    play.setLayoutX(220);
                    play.setLayoutY(250);
                    play.setPrefSize(140, 55);
                    grp.getChildren().add(play);

                    play = new Button("Multiplayer");
                    play.setStyle("-fx-font: 22 arial; -fx-base: #FFFFFF;");

                    play.setLayoutX(220);
                    play.setLayoutY(350);
                    play.setPrefSize(140, 55);
                    grp.getChildren().add(play);

                    
                    
                    play.setOnAction(f-> {
                        try {
                            game();
                        } catch (SocketException ex) {
                        } catch (UnknownHostException ex) {
                        }
                    });
                    stg.setScene(scn);
                    
                    stg.show();

                    

                } catch (SocketException ex) {
                } catch (UnknownHostException ex) {
                }

            } else {
                label.setText("Log In Denied");
                label.setFont(new Font("Arial", 20));
                label.setLayoutX(330);
                label.setLayoutY(20);
                root.getChildren().add(label);
            }
        }
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Connection Setup");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
