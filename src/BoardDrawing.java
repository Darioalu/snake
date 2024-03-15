import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BoardDrawing extends JPanel {
    private int row;
    private int col;
    private ArrayList<Rectangle> cells;
    private int[] cellnos;
    private BoardScreen bs;

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
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / col;
        int cellHeight = height / row;
        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Rectangle cell = new Rectangle(
                        xOffset + (j * cellWidth),
                        yOffset + (i * cellHeight),
                        cellWidth,
                        cellHeight);
                cells.add(cell);
            }
        }
    }

    private void initializePortals() {
        int noPorts = 8;
        bs.portals = new ArrayList<>(noPorts);
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row * col);
            bs.portals.add(temp);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawCells(g2d);
        drawPlayerPositions(g2d);
        drawPortals(g2d);
    }

    private void drawCells(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }
    }

    private void drawPlayerPositions(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        int i = 0;
        for (Rectangle cell : cells) {
            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            for (int pl = 0; pl < bs.maxPlayers; pl++) {
                if (bs.players.get(pl).returnPosition() == cellnos[i]) {
                    g2d.setColor(bs.players.get(pl).returnPlayerColor());
                    g2d.fillRect(cell.getLocation().x + pl * cell.getWidth() / 4, cell.getLocation().y, cell.getWidth() / 4, cell.getHeight() / 4);
                    g2d.setColor(Color.BLUE);
                }
            }
            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.maxPlayers; pl++) {
                    if (bs.players.get(pl).returnPosition() >= cellnos[i]) {
                        g2d.setColor(bs.players.get(pl).returnPlayerColor());
                        g2d.fillRect(cell.getLocation().x + pl * cell.getWidth() / 4, cell.getLocation().y, cell.getWidth() / 4, cell.getHeight() / 4);
                        g2d.setColor(Color.BLUE);
                    }
                }
            }
            i++;
        }
    }

    private void drawPortals(Graphics2D g2d) {
        for (Portal port : bs.portals) {
            if (port.returnNature() == -1)
                g2d.setColor(Color.RED);
            else
                g2d.setColor(Color.GREEN);

            int ind;
            int s = port.returnStart();
            for (ind = 0; ind < row * col; ind++) {
                if (cellnos[ind] == s)
                    break;
            }

            int j;
            int e = port.returnEnd();
            for (j = 0; j < row * col; j++) {
                if (cellnos[j] == e)
                    break;
            }

            g2d.drawLine((int) cells.get(ind).getCenterX(), (int) cells.get(ind).getCenterY(), (int) cells.get(j).getCenterX(), (int) cells.get(j).getCenterY());
        }
    }

    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : bs.portals) {
            if (bs.players.get(pnos).returnPosition() == port.returnStart()) {
                bs.players.get(pnos).setPosition(port.returnEnd());
                if (port.returnNature() == 1)
                    message += "You climbed a ladder to position " + port.returnEnd();
                else if (port.returnNature() == -1)
                    message += "You were bitten by a snake at " + port.returnEnd();
            }
        }
        return message;
    }

    public void setPlayer(int a, int pnos) {
        bs.players.get(pnos).incPosition(a);
    }
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BoardDrawing extends JPanel {
    private int row;
    private int col;
    private ArrayList<Rectangle> cells;
    private int[] cellnos;
    private BoardScreen bs;

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
        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / col;
        int cellHeight = height / row;
        int xOffset = (width - (col * cellWidth)) / 2;
        int yOffset = (height - (row * cellHeight)) / 2;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Rectangle cell = new Rectangle(
                        xOffset + (j * cellWidth),
                        yOffset + (i * cellHeight),
                        cellWidth,
                        cellHeight);
                cells.add(cell);
            }
        }
    }

    private void initializePortals() {
        int noPorts = 8;
        bs.setPortals(new ArrayList<>(noPorts));
        for (int i = 0; i < noPorts; i++) {
            Portal temp = new Portal(row * col);
            bs.getPortals().add(temp);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawCells(g2d);
        drawPlayerPositions(g2d);
        drawPortals(g2d);
    }

    private void drawCells(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (Rectangle cell : cells) {
            g2d.fill(cell);
        }
        g2d.setColor(Color.BLUE);
        for (Rectangle cell : cells) {
            g2d.draw(cell);
        }
    }

    private void drawPlayerPositions(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        int i = 0;
        for (Rectangle cell : cells) {
            String message = "" + cellnos[i];
            g2d.drawString(message, (int) cell.getCenterX(), (int) cell.getCenterY());
            for (int pl = 0; pl < bs.getMaxPlayers(); pl++) {
                if (bs.getPlayers().get(pl).getPosition() == cellnos[i]) {
                    g2d.setColor(bs.getPlayers().get(pl).getPlayerColor());
                    g2d.fillRect(cell.getLocation().x + pl * cell.getWidth() / 4, cell.getLocation().y, cell.getWidth() / 4, cell.getHeight() / 4);
                    g2d.setColor(Color.BLUE);
                }
            }
            if (cellnos[i] == row * col - 1) {
                for (int pl = 0; pl < bs.getMaxPlayers(); pl++) {
                    if (bs.getPlayers().get(pl).getPosition() >= cellnos[i]) {
                        g2d.setColor(bs.getPlayers().get(pl).getPlayerColor());
                        g2d.fillRect(cell.getLocation().x + pl * cell.getWidth() / 4, cell.getLocation().y, cell.getWidth() / 4, cell.getHeight() / 4);
                        g2d.setColor(Color.BLUE);
                    }
                }
            }
            i++;
        }
    }

    private void drawPortals(Graphics2D g2d) {
        for (Portal port : bs.getPortals()) {
            if (port.returnNature() == -1)
                g2d.setColor(Color.RED);
            else
                g2d.setColor(Color.GREEN);

            int ind;
            int s = port.returnStart();
            for (ind = 0; ind < row * col; ind++) {
                if (cellnos[ind] == s)
                    break;
            }

            int j;
            int e = port.returnEnd();
            for (j = 0; j < row * col; j++) {
                if (cellnos[j] == e)
                    break;
            }

            g2d.drawLine((int) cells.get(ind).getCenterX(), (int) cells.get(ind).getCenterY(), (int) cells.get(j).getCenterX(), (int) cells.get(j).getCenterY());
        }
    }

    public String ensurePlayerPosition(int pnos) {
        String message = "";
        for (Portal port : bs.getPortals()) {
            if (bs.getPlayers().get(pnos).getPosition() == port.returnStart()) {
                bs.getPlayers().get(pnos).setPosition(port.returnEnd());
                if (port.returnNature() == 1)
                    message += "You climbed a ladder to position " + port.returnEnd();
                else if (port.returnNature() == -1)
                    message += "You were bitten by a snake at " + port.returnEnd();
            }
        }
        return message;
    }

    public void setPlayer(int a, int pnos) {
        bs.getPlayers().get(pnos).incPosition(a);
    }
}
