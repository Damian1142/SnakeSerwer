package com.mech.game.view.przeszkody;

import com.mech.game.view.Board;
import com.mech.game.view.Drawing;

import java.awt.*;
import javax.swing.Timer;

public class Food extends Point implements Drawing {
    public Food() {
    }

    public Food(Point p) {
        super(p);
    }

    public Food(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * Board.SIZE,y * Board.SIZE,Board.SIZE,Board.SIZE);



    }
}
