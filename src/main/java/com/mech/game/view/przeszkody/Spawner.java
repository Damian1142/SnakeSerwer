package com.mech.game.view.przeszkody;

import com.mech.game.view.Board;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Spawner {
    private static int x = Board.FIELD_X,y = Board.FIELD_Y;

    private static final Timer timer = new Timer(5000, e -> spawn(SpawnType.OBSTACLE));
    private static final Timer timer2 = new Timer(15000, e -> spawn(SpawnType.FOOD));
    public static ArrayList<Obstacle> przeszkody = new ArrayList<>();
    public static ArrayList<Food> foods = new ArrayList<>();


    public static void spawn(SpawnType type){
        switch (type){
            case OBSTACLE: przeszkody.add(new Obstacle(new Random().nextInt(Board.FIELD_X),new Random().nextInt(Board.FIELD_Y)));
                break;
            case FOOD: {
                foods.clear();
                for (int i = 0; i < 10; i++) {
                    foods.add(new Food(new Random().nextInt(Board.FIELD_X),new Random().nextInt(Board.FIELD_Y)));
                }
                break;
            }
        }

    }

    public static void start() {
        timer.start();
        timer2.start();
    }

    public static void stop() {
        timer.stop();
        timer2.stop();
    }
    static {
        spawn(SpawnType.FOOD);
    }
}
