package main;

import gamestates.Gamestates;
import gamestates.Menu;
import gamestates.Playing;

import java.awt.*;

public class Game implements Runnable {

    public GameWindow gameWindow;
    public GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_CAP = 120; // Max number of frames per second
    private final int UPS_CAP = 200; // Max number of updates per second

    private Menu menu;
    private Playing playing;

    public Game() {
        gamePanel = new GamePanel(this);
        initClasses();
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gameWindow = new GameWindow();
        gameWindow.add(gamePanel);
        startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing();
    }

    public void update() {

    }

    public void render(Graphics g) {
        switch (Gamestates.gamestate) {
            case MENU -> menu.render(g);
            case PLAYING -> playing.render(g);
        }
    }

    @Override
    public void run() {

        double timeBetweenFrames = 1000000000.0 / FPS_CAP;
        double timeBetweenUpdates = 1000000000.0 / UPS_CAP;
        long currentTime = System.nanoTime();
        long previousTime = currentTime;
        double deltaFPS = 0, deltaUPS = 0;
        int fpsCount = 0, upsCount = 0;

        double currentCountCheck = System.currentTimeMillis();
        double lastCountCheck = currentCountCheck;

        while (true) {

            currentCountCheck = System.currentTimeMillis();
            currentTime = System.nanoTime();
            deltaFPS += (currentTime - previousTime) / timeBetweenFrames;
            deltaUPS += (currentTime - previousTime) / timeBetweenUpdates;
            previousTime = currentTime;

            if (deltaFPS >= 1) {
                deltaFPS--;
                fpsCount++;
                gamePanel.repaint();
            }

            if (deltaUPS >= 1) {
                deltaUPS--;
                upsCount++;
            }

            if (currentCountCheck - lastCountCheck >= 1000) {
                System.out.println("FPS: " + fpsCount + " | UPS: " + upsCount);
                lastCountCheck = System.currentTimeMillis();
                fpsCount = 0;
                upsCount = 0;
            }

        }
    }

}
