package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        MouseInputs mouseInputs = new MouseInputs(this);
        setLayout(null);
        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

}
