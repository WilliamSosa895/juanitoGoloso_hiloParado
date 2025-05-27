/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SNAKE;

/**
 *
 * @author william
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GamePanel extends JPanel {
    private final int size, cells, cellSize;
    private final List<List<Point>> snakes = new ArrayList<>();
    private final List<String> direction = new ArrayList<>();
    private final List<String> nextDirection = new ArrayList<>();
    private final List<Color> snakeColors = List.of(Color.RED, Color.BLUE);
    private Point food;

    public GamePanel(int size, int cells) {
        this.size = size;
        this.cells = cells;
        this.cellSize = size / cells;
        setPreferredSize(new Dimension(size, size));

        for (int i = 0; i < 2; i++) {
            List<Point> s = new ArrayList<>();
            if (i == 0) {
                s.add(new Point(cells/4 - 1, cells/2));
                s.add(new Point(cells/4,     cells/2));
            } else {
                s.add(new Point(3 * cells/4 - 1, cells/2));
                s.add(new Point(3 * cells/4,     cells/2));
            }
            snakes.add(s);
            direction.add("de");
            nextDirection.add("de");
        }

        spawnFood();
        new Thread(new Caminante(this)).start();
    }

    public void setNextDirection(int player, String dir) {
        String cur = direction.get(player);
        if (((cur.equals("de") || cur.equals("iz")) && (dir.equals("ar") || dir.equals("ab")))
         || ((cur.equals("ar") || cur.equals("ab")) && (dir.equals("de") || dir.equals("iz")))) {
            nextDirection.set(player, dir);
        }
    }

    private void spawnFood() {
        Random r = new Random();
        int x, y;
        boolean collision;
        do {
            x = r.nextInt(cells);
            y = r.nextInt(cells);
            collision = false;
            for (List<Point> snake : snakes) {
                for (Point seg : snake) {
                    if (seg.x == x && seg.y == y) {
                        collision = true;
                        break;
                    }
                }
                if (collision) break;
            }
        } while (collision);
        food = new Point(x, y);
    }

    public void advanceAll() {
        for (int i = 0; i < snakes.size(); i++) {
            advanceSnake(i);
        }
    }

    private void advanceSnake(int idx) {
        List<Point> s = snakes.get(idx);
        direction.set(idx, nextDirection.get(idx));
        Point head = s.get(s.size() - 1);
        Point delta;
        switch (direction.get(idx)) {
            case "de": delta = new Point(1, 0); break;
            case "iz": delta = new Point(-1, 0); break;
            case "ar": delta = new Point(0, -1); break;
            default:    delta = new Point(0, 1); break; 
        }

        Point newHead = new Point(
            Math.floorMod(head.x + delta.x, cells),
            Math.floorMod(head.y + delta.y, cells)
        );

        for (List<Point> snake : snakes) {
            for (Point seg : snake) {
                if (seg.equals(newHead)) {
                    JOptionPane.showMessageDialog(this, "¡Jugador " + (idx + 1) + " ha perdido!");
                    System.exit(0);
                }
            }
        }

        if (newHead.equals(food)) {
            s.add(newHead);
            spawnFood();
        } else {
            s.add(newHead);
            s.remove(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        for (int x = 0; x < cells; x++) {
            for (int y = 0; y < cells; y++) {
                g.fillRect(x * cellSize, y * cellSize, cellSize - 1, cellSize - 1);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(food.x * cellSize, food.y * cellSize, cellSize - 1, cellSize - 1);
        for (int i = 0; i < snakes.size(); i++) {
            g.setColor(snakeColors.get(i));
            for (Point p : snakes.get(i)) {
                g.fillRect(p.x * cellSize, p.y * cellSize, cellSize - 1, cellSize - 1);
            }
        }
    }
}