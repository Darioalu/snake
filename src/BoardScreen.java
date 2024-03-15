import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardScreen extends JPanel {

    public int player = 0;
    public BoardDrawing bd;
    public JPanel stats;
    public JLabel dieResults;
    public JLabel whichPlayer;
    public JLabel extraInfo;
    protected int maxPlayers = 1; // Cambiado a protected
    public int currPlayer = 0;
    public ArrayList<Portal> portals;
    public ArrayList<Player> players;
    public int x;
    public int y;
    public JLabel success;
    public JButton roll;

    private MainWindow mw;

    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    public void goButtonActionListener() {
        mw.showCard("Two");
        mw.resetAll();
    }

    public void setMaxPlayers(int m) {
        maxPlayers = m;
    }

    public int returnMaxPlayers() {
        return maxPlayers;
    }

    public void setUpPlayers() {
        players = new ArrayList<Player>();
        for (int i = 0; i < returnMaxPlayers(); i++) {
            players.add(new Player(i));
        }
        // Obtener y agregar nombre(s) de jugador(es)

        // Entrada de color manual - automatizar más adelante
        if (0 < returnMaxPlayers()) {
            players.get(0).setPlayerColor(Color.green);
        }
        if (1 < returnMaxPlayers()) {
            players.get(1).setPlayerColor(Color.blue);
        }
        if (2 < returnMaxPlayers()) {
            players.get(2).setPlayerColor(Color.red);
        }
    }

    public BoardScreen(MainWindow mw) {
        this.mw = mw;

        currPlayer = 0;

        JButton go = new JButton("New Game");
        JButton quit = new JButton("Quit");

        go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                goButtonActionListener();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                quitButtonActionListener();
            }
        });

        players = new ArrayList<Player>();
        players.add(new Player(currPlayer));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        x = y = 8;

        bd = new BoardDrawing(x, y, this);
        bd.setVisible(true);

        int sw = getSize().width;
        int sh = getSize().height;
        int a = (int) (0.75 * ((sw > sh) ? sh : sw));

        bd.setSize(a, a);

        add(bd);

        stats = new JPanel();
        stats.setLayout(new BoxLayout(stats, BoxLayout.X_AXIS));
        add(stats);

        stats.add(go);
        stats.add(quit);

        whichPlayer = new JLabel();
        whichPlayer.setText(players.get(currPlayer).getName());
        stats.add(whichPlayer);

        extraInfo = new JLabel();

        success = new JLabel("");

        roll = new JButton("Roll the die!");
        roll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random die = new Random();
                int a = die.nextInt(6) + 1;
                dieResults.setText("You rolled a " + a);
                player += a;
                bd.setPlayer(a, currPlayer);
                extraInfo.setText(bd.ensurePlayerPosition(currPlayer));
                bd.repaint();

                players.get(currPlayer).incPlayerScore(1);

                for (Player p : players) {
                    if (p.getPosition() >= x * y - 1) {
                        success.setText("And the winner is: " + p.getName() + "\nYour score: " + p.getPlayerScore());
                        roll.setVisible(false);
                    }
                }

                if (currPlayer == maxPlayers - 1) {
                    currPlayer = 0;
                } else {
                    currPlayer += 1;
                }

                whichPlayer.setText(players.get(currPlayer).getName());

            }
        });
        roll.setVisible(true);

        stats.add(roll);

        dieResults = new JLabel();
        stats.add(dieResults);

        stats.add(extraInfo);
        stats.add(success);
    }
}
