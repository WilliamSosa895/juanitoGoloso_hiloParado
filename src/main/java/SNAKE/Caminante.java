/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SNAKE;

/**
 *
 * @author william
 */
public class Caminante implements Runnable {
    private final GamePanel panel;
    private static final long INTERVAL = 300; 

    public Caminante(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.advanceAll();
            panel.repaint();
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}