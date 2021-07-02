package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Stats {

    @FXML
    private AnchorPane statsPane;

    @FXML
    private Label monstersKilledLabel;

    @FXML
    private Label expGainedLabel;

    @FXML
    private Label damageDealtLabel;

    @FXML
    private Label moneyGainedLabel;

    @FXML
    private Label healthLostLabel;

    @FXML
    private Label clickMadeLabel;

    private StatsSaver statsSaver;

    public void updateStats(){
        damageDealtLabel.setText(statsSaver.getDmgDealt()+"");
        clickMadeLabel.setText(statsSaver.getClickMade()+"");
        moneyGainedLabel.setText(statsSaver.getMoneyGained()+"");
        healthLostLabel.setText(statsSaver.getHealthLost()+"");
        expGainedLabel.setText(statsSaver.getExpGained()+"");
        monstersKilledLabel.setText(statsSaver.getMonstersKilled()+"");
    }

    public AnchorPane getStatsPane(){
        return statsPane;
    }

    public void setStatsSaver(StatsSaver statsSaver){
        this.statsSaver = statsSaver;
    }

}
