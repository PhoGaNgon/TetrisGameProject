package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static util.Constants.GameConstants.*;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        MouseInputs mouseInputs = new MouseInputs(this);
        setLayout(null);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_SIZE_WIDTH + " : " + GAME_SIZE_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }

    public Game getGame() {
        return game;
    }

}
