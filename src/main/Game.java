package main;

import gamestates.Gamestates;
import gamestates.Menu;
import gamestates.Options;
import gamestates.Playing;

import java.awt.*;

import static util.Constants.*;

public class Game implements Runnable {

    public GameWindow gameWindow;
    public GamePanel gamePanel;
    private Thread gameThread;

    private Menu menu;
    private Playing playing;
    private Options options;

    public Game() {
        gamePanel = new GamePanel(this);
        initClasses();
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gameWindow = new GameWindow(gamePanel);
        startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing();
        options = new Options();
    }

    public void update() {
        switch (Gamestates.gamestate) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS -> options.update();
        }
    }

    public void draw(Graphics g) {
        switch (Gamestates.gamestate) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
            case OPTIONS -> options.draw(g);
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
                update();
            }

            if (currentCountCheck - lastCountCheck >= 1000) {
                System.out.println("FPS: " + fpsCount + " | UPS: " + upsCount);
                lastCountCheck = System.currentTimeMillis();
                fpsCount = 0;
                upsCount = 0;
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

}
