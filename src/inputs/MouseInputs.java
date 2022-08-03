package inputs;

import gamestates.Gamestates;
import main.GamePanel;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseInputListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestates.gamestate) {
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
        }
    }
}
