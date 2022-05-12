package com.mech.server.packets;

import com.mech.game.snake.Snake;
import com.mech.game.view.Drawing;
import com.mech.game.view.przeszkody.Food;
import com.mech.game.view.przeszkody.Obstacle;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.util.ArrayList;

@Data
@Builder
public class Map implements Drawing {

    private ArrayList<Obstacle> obstacles;
    private ArrayList<Food> foods;
    private ArrayList<Snake> snakes;

    @Override
    public void draw(Graphics g) {
        obstacles.forEach(o -> o.draw(g));
        foods.forEach(f -> f.draw(g));
        snakes.forEach(s -> s.draw(g));

    }
}
