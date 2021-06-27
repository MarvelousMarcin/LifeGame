package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ItemShop {

    @FXML
    private AnchorPane itemShopPane;

    @FXML
    private Button potionBut;

    @FXML
    private Button sword1But;

    @FXML
    private Button sword2But;

    @FXML
    private Button wandBut;

    private Player player;

    private Menu menu;

    public void initialize(){
        EventHandler<ActionEvent> potionBuy = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(player.getMoney() >= 500){
                    player.takeMoney(500);
                    player.addHealth(20);
                    menu.updateUserStats(player);
                }
            }
        };


        EventHandler<ActionEvent> sword1Buy = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(player.getMoney() >= 10000){
                    player.takeMoney(10000);
                    player.addDmg(200);
                    menu.updateUserStats(player);
                }
            }
        };

        EventHandler<ActionEvent> sword2Buy = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(player.getMoney() >= 20000){
                    player.takeMoney(20000);
                    player.addDmg(300);
                    menu.updateUserStats(player);
                }
            }
        };

        EventHandler<ActionEvent> wandBuy = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(player.getMoney() >= 100000){
                    player.takeMoney(100000);
                    player.addDmg(400);
                    menu.updateUserStats(player);
                }
            }
        };

        potionBut.addEventHandler(ActionEvent.ACTION, potionBuy);
        sword1But.addEventHandler(ActionEvent.ACTION, sword1Buy);
        sword2But.addEventHandler(ActionEvent.ACTION, sword2Buy);
        wandBut.addEventHandler(ActionEvent.ACTION, wandBuy);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public AnchorPane getItemShopPane(){
        return itemShopPane;
    }
}
