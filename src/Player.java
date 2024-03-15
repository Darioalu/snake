import java.awt.Color;

public class Player {
    
    private String name;
    private int position;
    private int playerScore;
    private Color playerColor;

    public Player(int no) {
        name = "Player " + no;
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int posn) {
        position = posn;
    }

    public void incPosition(int posn) {
        position += posn;
    }

    public void setPlayerColor(Color c) {
        playerColor = c;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public void incPlayerScore(int a) {
        playerScore += a;
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
