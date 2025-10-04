package com.example.parquesdigital.model;

import java.util.Random;
public class Dice {
    private Random random = new Random();

    public int roll() {
        //probabilidad uniforme 1-6
        return random.nextInt(6) + 1;
    }
}
