package com.mech.game;

import com.mech.game.snake.Snake;
import com.mech.game.view.przeszkody.Spawner;

import javax.swing.*;
import java.util.ArrayList;

public class MainGame {
    public static ArrayList<Snake> snakes = new ArrayList<>();
    public static Timer timer;
    static {
        timer = new Timer(100,e -> snakes.forEach(Snake::move));
        Spawner.start();
        timer.start();
    }
}
