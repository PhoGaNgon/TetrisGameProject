package main;

import javax.swing.*;
import static util.Constants.GameConstants.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {
        setSize(GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
