package com.mech.game.view.przeszkody;

import com.mech.game.view.Board;
import com.mech.game.view.Drawing;

import java.awt.*;

public class Obstacle extends Point implements Drawing {

    public Obstacle() {
    }

    public Obstacle(Point p) {
        super(p);
    }

    public Obstacle(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x * Board.SIZE,y * Board.SIZE, Board.SIZE,Board.SIZE);
    }
}
