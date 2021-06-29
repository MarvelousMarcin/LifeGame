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

    private final ArrayList<Image> enemyPictures = new ArrayList<>();

    private Enemy enemy;

    private Menu menu;
    private boolean bossKilled;

    public static void setExpEnemy(int expEnemy) {
        Game.expEnemy = expEnemy;
    }

    public static void setLootEnemy(int lootEnemy) {
        Game.lootEnemy = lootEnemy;
    }

    public static int getExpEnemy() {
        return expEnemy;
    }

    public static int getLootEnemy() {
        return lootEnemy;
    }

    private static int expEnemy = 5000;

    private static int lootEnemy = 100;

    private Boss boss;

    private int enemyHealth = 300;
    private int enemyDmg = 20;

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

    @FXML
    private Label enemyHealthLabel;

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

        enemyHealthLabel.setText(enemy.getHealth()+"");

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
                    if(player.getLevel()>=5 && player.getLevel()%5 ==0){
                        enemyHealth = player.getLevel() * 100;
                        enemyDmg += player.getLevel() * 2;
                    }
                }
                if(player.getLevel()>=5 && player.getLevel() < 30 && player.getLevel()%5 == 0){
                    bossKilled = false;
                    BossList bossList = new BossList(player.getLevel());
                    boss = bossList.getBoss();
                    enemy = new Enemy(boss.getBossDmg(),boss.getBossHp(),boss.getBossImage(), player.getNextLevelExpNeeded()-player.getPlayerExp(),10000);
                }else{
                    enemy = generateEnemy();
                    enemyHealthLabel.setText(enemy.getHealth()+"");
                }
                loadPlayerStats();
                loadEnemy();


            }else {
                enemyHealthBar.setProgress(enemy.getHealthBar().getProgress());
                enemyHealthLabel.setText(enemy.getHealth()+"");
            }
        };


        enemyBut.addEventHandler(ActionEvent.ACTION, playerAttack);

    }

    private Enemy generateEnemy(){
        Random random = new Random();
        return new Enemy(enemyDmg,enemyHealth,enemyPictures.get(random.nextInt(enemyPictures.size())),expEnemy,lootEnemy);
    }

    private void loadEnemy(){
        enemyP.setImage(enemy.getEnemyPic());
        enemyHealthBar.setProgress(enemy.getHealthBar().getProgress());
    }

    private void loadBoss(){

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
