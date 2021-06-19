package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private Player player;

    private ArrayList<Image> enemyPictures = new ArrayList<>();

    @FXML
    private AnchorPane gameContent;

    @FXML
    private Button enemyBut;

    @FXML
    private ImageView enemyP;

    @FXML
    private ProgressBar enemyHealthBar;

    public void initialize(){
        Image imageApple = new Image("/img/apple.png");
        Image imageLinux = new Image("/img/linux.png");
        Image imageChrome = new Image("/img/chrome.png");
        enemyPictures.add(imageApple);
        enemyPictures.add(imageLinux);
        enemyPictures.add(imageChrome);


        Enemy enemy = generateEnemy();
        enemyP.setImage(enemy.getEnemyPic());

        EventHandler<ActionEvent> playerAttack = actionEvent -> {
            int playerDmg = player.getDmg();
            enemy.getDmg(playerDmg);
            if(enemy.getHealth() <= 0){
                //TODO Delete last enemy and create new one
            }
            enemyHealthBar.setProgress(enemy.getHealthBar().getProgress());


        };

        enemyBut.addEventHandler(ActionEvent.ACTION, playerAttack);

    }

    private Enemy generateEnemy(){
        Random random = new Random();
        return new Enemy(random.nextInt(50),random.nextInt(200),enemyPictures.get(random.nextInt(enemyPictures.size())),100,100);
    }


    public AnchorPane getGameContent(){
        return gameContent;
    }

    public void setPlayer(Player player){
        this.player = player;
    }


}
