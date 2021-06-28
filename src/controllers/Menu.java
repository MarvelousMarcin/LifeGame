package controllers;

import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;

public class Menu {

    private final static int SHOW = 1;
    private final static int HIDE = 0;

    private Player player;

    @FXML
    private Button mainBut;

    @FXML
    private Button exitBut;

    @FXML
    private Circle settCir;

    @FXML
    private Circle mainCir;


    @FXML
    private Circle cashCir;

    @FXML
    private Circle userCir;


    @FXML
    private Button cashBut;


    @FXML
    private Button userBut;

    @FXML
    private Button settBut;

    @FXML
    private Button earthPic;

    @FXML
    private Button fbBut;

    @FXML
    private Button igBut;

    @FXML
    private Button redBut;

    @FXML
    private ProgressBar progressBarLabel;

    @FXML
    private Label playerHp;

    @FXML
    private Label playerMoney;

    @FXML
    private Pane gamePane;

    @FXML
    private Label levelLabel;

    @FXML
    private Label describeLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private ImageView pic3;

    @FXML
    private Label dmgLabel;

    @FXML
    private ImageView pic4;

    private final FXMLLoader loaderCash = new FXMLLoader(getClass().getResource("/fxml/Shop.fxml"));;

    private boolean fistTime = false;



    public void initialize(){
        hideCircles();

        pic1Anim();
        pic2Anim();

        RotateTransition rt = new RotateTransition();
        rt.setNode(earthPic);
        rt.setDuration(Duration.seconds(10));
        rt.setToAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();


        fbBut.addEventHandler(MouseEvent.MOUSE_ENTERED, socialEvent(fbBut, SHOW));
        fbBut.addEventHandler(MouseEvent.MOUSE_EXITED, socialEvent(fbBut, HIDE));

        igBut.addEventHandler(MouseEvent.MOUSE_ENTERED, socialEvent(igBut, SHOW));
        igBut.addEventHandler(MouseEvent.MOUSE_EXITED, socialEvent(igBut, HIDE));

        redBut.addEventHandler(MouseEvent.MOUSE_ENTERED, socialEvent( redBut, SHOW));
        redBut.addEventHandler(MouseEvent.MOUSE_EXITED, socialEvent( redBut, HIDE));

        EventHandler<ActionEvent> exitAction = actionEvent -> System.exit(0);

        settBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(settBut,settCir,SHOW));
        settBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(settBut,settCir,HIDE));


        mainBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(mainBut,mainCir,SHOW));
        mainBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(mainBut,mainCir,HIDE));
        mainBut.setOnAction(e->menu());

        userBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(userBut,userCir,SHOW));
        userBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(userBut,userCir,HIDE));
        userBut.setOnAction(e-> user());

        cashBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(cashBut,cashCir,SHOW));
        cashBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(cashBut,cashCir,HIDE));
        cashBut.setOnAction(e-> cash());


        EventHandler<MouseEvent> exitAnim = mouseEvent -> {
            FadeTransition fd = new FadeTransition();
            fd.setNode(exitBut);
            fd.setDuration(Duration.millis(200));
            fd.setCycleCount(1);

            fd.setFromValue(.6);
            fd.setToValue(1);

            fd.play();

        };

        EventHandler<MouseEvent> exitAnimExit = mouseEvent -> {
            FadeTransition fd = new FadeTransition();
            fd.setNode(exitBut);
            fd.setDuration(Duration.millis(200));
            fd.setCycleCount(1);

            fd.setFromValue(1);
            fd.setToValue(.6);

            fd.play();

        };


        exitBut.addEventHandler(MouseEvent.MOUSE_ENTERED, exitAnim);
        exitBut.addEventHandler(MouseEvent.MOUSE_EXITED, exitAnimExit);
        exitBut.addEventHandler(ActionEvent.ACTION, exitAction);

    }

    //Main page button in menu
    @FXML
    public void menu() {

        hidePictures();
        hideHelper();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Game)loader.getController()).setPlayer(player);
        ((Game)loader.getController()).setMenu(this);
        gamePane.getChildren().clear();
        gamePane.getChildren().add(((Game)loader.getController()).getGameContent());
    }

    //Cash page button in menu
    @FXML
    public void cash(){
        hidePictures();
        //When we open the shop for the first time we have to load it, then we just open existing source
        if(!fistTime) {
            try {
                loaderCash.load();
                fistTime = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((Shop)loaderCash.getController()).setPlayer(player);
        ((Shop)loaderCash.getController()).setMenu(this);

        gamePane.getChildren().clear();
        gamePane.getChildren().add(((Shop)loaderCash.getController()).getShopPane());

    }

    //User page button in menu
    @FXML
    public void user(){
        hidePictures();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemShop.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((ItemShop)loader.getController()).setPlayer(player);
        ((ItemShop)loader.getController()).setMenu(this);


        gamePane.getChildren().clear();
        gamePane.getChildren().add(((ItemShop)loader.getController()).getItemShopPane());

    }


    @FXML
    public void exit(){
        System.exit(0);
    }

    //Menu button animations, sliding circle, changing the opacity
    private EventHandler<MouseEvent> mouseAnimation(Node nodeBut, Node nodeCir, int show){

            EventHandler<MouseEvent> showCirExit = mouseEvent -> {
                FadeTransition fd = new FadeTransition();
                fd.setNode(nodeBut);
                fd.setDuration(Duration.millis(200));
                fd.setCycleCount(1);

                TranslateTransition tt = new TranslateTransition();
                tt.setNode(nodeCir);
                tt.setDuration(Duration.millis(300));
                tt.setCycleCount(1);

                if(show == SHOW){
                    fd.setFromValue(.6);
                    fd.setToValue(1);
                    fd.play();

                    tt.setToX(0);

                }else{
                    fd.setFromValue(1);
                    fd.setToValue(.6);
                    fd.play();

                    tt.setToX(-20);
                }
                tt.play();
            };
        return showCirExit;
    }


    public void hideCircles(){
        settCir.setTranslateX(-20);
        userCir.setTranslateX(-20);
        cashCir.setTranslateX(-20);
        mainCir.setTranslateX(-20);
    }

    public EventHandler<MouseEvent> socialEvent(Button node, int mode){
        EventHandler<MouseEvent> event = mouseEvent -> {
            ScaleTransition st = new ScaleTransition();
            st.setNode(node);
            st.setCycleCount(1);
            st.setDuration(Duration.millis(200));
            if(mode == SHOW) {
                st.setByX(.2);
                st.setByY(.2);

            }else{
                st.setToX(1);
                st.setToY(1);
            }
            st.play();
        };
        return event;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void updateUserStats(Player player){

        dmgLabel.setText(player.getDmg()+"");
        playerMoney.setText(player.getMoney()+"");
        playerHp.setText(player.getHealth()+"");
        levelLabel.setText("Level: "+player.getLevel());

        this.progressBarLabel.setProgress(player.levelBar().getProgress());
    }

    public void pic1Anim(){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(pic1);
        tt.setDuration(Duration.seconds(2));
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setByY(-10);
        tt.setInterpolator(Interpolator.LINEAR);
        tt.setAutoReverse(true);
        tt.play();
    }

    public void pic2Anim(){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(pic2);
        tt.setDuration(Duration.seconds(2));
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setByY(20);
        tt.setByX(20);
        tt.setInterpolator(Interpolator.LINEAR);
        tt.setAutoReverse(true);

        RotateTransition rt = new RotateTransition();
        rt.setNode(pic2);
        rt.setDuration(Duration.seconds(4));
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setByAngle(30);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setAutoReverse(true);



        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(tt,rt);
        pt.play();
    }

    private void hideHelper(){
        describeLabel.setVisible(false);
        welcomeLabel.setVisible(false);
    }

    private void hidePictures(){
        pic1.setVisible(false);
        pic2.setVisible(false);
        pic3.setVisible(false);
        pic4.setVisible(false);
    }
}
