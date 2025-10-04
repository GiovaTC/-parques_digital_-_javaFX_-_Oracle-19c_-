package com.example.parquesdigital.model;

public class Player {
    private String name;
    private int position = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public int getPosition() { return position; }

    public void setPosition(int pos) { this.position = pos; }
}
