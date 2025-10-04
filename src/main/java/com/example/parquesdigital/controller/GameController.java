package com.example.parquesdigital.controller;

import com.example.parquesdigital.db.GameRecordDAO;
import com.example.parquesdigital.model.Board;
import com.example.parquesdigital.model.Dice;
import com.example.parquesdigital.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    @FXML private Button rollButton;
    @FXML private Label resultLabel;
    @FXML private Label turnLabel;

    private final Dice dice = new Dice();
    private final Board board = new Board();
    private final List<Player> players = new ArrayList<>();
    private int currentTurn = 0;
    private int moveCounter = 0;

    private final GameRecordDAO dao = new GameRecordDAO();

    @FXML
    public void initialize() {
        players.add(new Player("Jugador 1"));
        players.add(new Player("Jugador 2"));
        turnLabel.setText("Turno de: " + players.get(currentTurn).getName());
        rollButton.setOnAction(e -> rollDice());
    }

    private void rollDice() {
        int value  = dice.roll();
        Player current = players.get(currentTurn);

        resultLabel.setText(current.getName() + " sacó: " + value);

        int from = current.getPosition();
        int to = board.move(current, value);

        moveCounter++;
        dao.saveMove(1, current.getName(), moveCounter, from, to, value);

        currentTurn = (currentTurn + 1) % players.size();
        turnLabel.setText("Turno de: " + players.get(currentTurn).getName());
    }
}
