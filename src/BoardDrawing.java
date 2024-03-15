import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BoardDrawing extends JPanel {
    
    int row;
    int col;
    ArrayList<Rectangle> cells;
    int[] cellnos;
    BoardScreen bs;

    public BoardDrawing(int row, int col, BoardScreen bs) {
        this.row = row;
        this.col = col;
        this.bs = bs;
        cells = new ArrayList<>();
        cellnos = new int[row * col];
        initializeCells();
        initializePortals();
    }

    private void initializeCells() {
        // Implementation not shown
    }

    private void initializePortals() {
        // Implementation not shown
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawCells(g2d);
        drawPlayerPositions(g2d);
        drawPortals(g2d);
    }

    private void drawCells(Graphics2D g2d) {
        // Implementation not shown
    }

    private void drawPlayerPositions(Graphics2D g2d) {
        // Implementation not shown
    }

    private void drawPortals(Graphics2D g2d) {
        // Implementation not shown
    }

    public String ensurePlayerPosition(int pnos) {
        // Implementation not shown
    }

    public void setPlayer(int a, int pnos) {
        // Implementation not shown
    }
}

