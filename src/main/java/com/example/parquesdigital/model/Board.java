package com.example.parquesdigital.model;

public class Board {
    private static final int SIZE = 50; // casillas simplificadas :.

    public int move(Player player, int steps) {
        int newPos = (player.getPosition() + steps) % SIZE;
        player.setPosition(newPos);
        return newPos;
    }
}
