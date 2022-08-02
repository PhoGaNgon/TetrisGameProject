package main;

import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        addKeyListener(new KeyboardInputs());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

}
