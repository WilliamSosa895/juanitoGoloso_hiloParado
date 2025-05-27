/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SNAKE;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author william
 */
public class Vista extends JFrame {
    private final GamePanel gamePanel;

    public Vista() {
        setTitle("Snake 2 Jugadores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePanel = new GamePanel(800, 30);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    // Jugador 1: flechas
                    case KeyEvent.VK_LEFT:  gamePanel.setNextDirection(0, "iz"); break;
                    case KeyEvent.VK_RIGHT: gamePanel.setNextDirection(0, "de"); break;
                    case KeyEvent.VK_UP:    gamePanel.setNextDirection(0, "ar"); break;
                    case KeyEvent.VK_DOWN:  gamePanel.setNextDirection(0, "ab"); break;
                    // Jugador 2: WASD
                    case KeyEvent.VK_A:     gamePanel.setNextDirection(1, "iz"); break;
                    case KeyEvent.VK_D:     gamePanel.setNextDirection(1, "de"); break;
                    case KeyEvent.VK_W:     gamePanel.setNextDirection(1, "ar"); break;
                    case KeyEvent.VK_S:     gamePanel.setNextDirection(1, "ab"); break;
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Vista::new);
    }
}