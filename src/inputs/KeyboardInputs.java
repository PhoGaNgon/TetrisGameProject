package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestates;

public class KeyboardInputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                Gamestates.gamestate = Gamestates.PLAYING;
                System.out.println("ESCAPED");

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
