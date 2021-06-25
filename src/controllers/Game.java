package controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Player player;

    private ArrayList<Image> enemyPictures = new ArrayList<>();

    private Enemy enemy;

    private Menu menu;

    @FXML
    private AnchorPane gameContent;

    @FXML
    private Button enemyBut;

    @FXML
    private ImageView enemyP;

    @FXML
    private ProgressBar enemyHealthBar;

    @FXML
    private Label enemyAttackLabel;

    public void initialize(){
        enemyAnim();
        Image imageApple = new Image("/img/apple.png");
        Image imageLinux = new Image("/img/linux.png");
        Image imageChrome = new Image("/img/chrome.png");
        Image imageAndroid = new Image("/img/android.png");
        Image imageGoogle = new Image("/img/google.png");
        Image imageOneNote = new Image("/img/onenote.png");
        Image imagePayPal = new Image("/img/paypal.png");
        enemyPictures.add(imageApple);
        enemyPictures.add(imageLinux);
        enemyPictures.add(imageChrome);
        enemyPictures.add(imageAndroid);
        enemyPictures.add(imageOneNote);
        enemyPictures.add(imagePayPal);
        enemyPictures.add(imageGoogle);



        enemy = generateEnemy();
        enemyP.setImage(enemy.getEnemyPic());

        EventHandler<ActionEvent> playerAttack = actionEvent -> {
            Random random = new Random();
            int randVal = random.nextInt(20);
            if(randVal == 0){
                enemyBut.setDisable(true);

                enemyAttackLabel.setText("-"+enemy.getEnemyDmg());

                FadeTransition fadeTransition = new FadeTransition();
                fadeTransition.setNode(enemyAttackLabel);
                fadeTransition.setDuration(Duration.millis(300));
                fadeTransition.setCycleCount(2);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.setAutoReverse(true);
                fadeTransition.play();


                TranslateTransition tt = new TranslateTransition();
                tt.setNode(enemyP);
                tt.setDuration(Duration.millis(200));
                tt.setAutoReverse(true);
                tt.setInterpolator(Interpolator.LINEAR);
                tt.setCycleCount(2);
                tt.setFromY(enemyP.getY());
                tt.setToY(200);
                tt.play();

                player.getHit(enemy.getEnemyDmg());
                menu.updateUserStats(player);

            }
            enemyBut.setDisable(false);

            int playerDmg = player.getDmg();
            enemy.getDmg(playerDmg);
            if(enemy.getHealth() <= 0){
                player.addExp(enemy.getExp());
                player.addMoney(enemy.getLoot());
                if(player.checkIfNextLevel()){
                    player.levelUp();
                }
                enemy = generateEnemy();
                loadPlayerStats();
                loadEnemy();

            }else {
                enemyHealthBar.setProgress(enemy.getHealthBar().getProgress());
            }
        };


        enemyBut.addEventHandler(ActionEvent.ACTION, playerAttack);

    }

    private Enemy generateEnemy(){
        Random random = new Random();
        return new Enemy(10,300,enemyPictures.get(random.nextInt(enemyPictures.size())),300,1000);
    }

    private void loadEnemy(){
        enemyP.setImage(enemy.getEnemyPic());
        enemyHealthBar.setProgress(enemy.getHealthBar().getProgress());
    }

    private void loadPlayerStats(){
        menu.updateUserStats(player);
    }

    public AnchorPane getGameContent(){
        return gameContent;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public void enemyAnim(){
        RotateTransition rt = new RotateTransition();
        rt.setNode(enemyP);
        rt.setDuration(Duration.seconds(.3));
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setAutoReverse(true);
        rt.setToAngle(15);
        rt.play();
    }

}
