package controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.swing.text.IconView;
import javax.swing.text.html.ImageView;
import java.awt.*;

public class Menu {

    private final static int SHOW = 1;
    private final static int HIDE = 0;



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

    public void initialize(){
        hideCircles();

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

        userBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(userBut,userCir,SHOW));
        userBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(userBut,userCir,HIDE));

        cashBut.addEventHandler(MouseEvent.MOUSE_ENTERED,mouseAnimation(cashBut,cashCir,SHOW));
        cashBut.addEventHandler(MouseEvent.MOUSE_EXITED,mouseAnimation(cashBut,cashCir,HIDE));

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
    public void menu(){


    }

    //Cash page button in menu
    @FXML
    public void cash(){

    }

    //User page button in menu
    @FXML
    public void user(){

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


}
