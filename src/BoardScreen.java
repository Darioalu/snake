public class BoardScreen extends JPanel {
    
    int player = 0;
    BoardDrawing bd;
    JPanel stats;
    JLabel dieResults;
    JLabel whichPlayer;
    JLabel extraInfo;
    int maxPlayers = 1;
    int currPlayer = 0;
    MainWindow mw;
    JButton go;
    JButton quit;

    public void quitButtonActionListener() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION)
            System.exit(0);
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
        // Implementation not shown
    }

    public BoardScreen(MainWindow mw) {
        this.mw = mw;

        currPlayer = 0;

        go = new JButton("New Game");
        quit = new JButton("Quit");

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
        whichPlayer.setText(players.get(currPlayer).returnName());
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
                    if (p.returnPosition() >= x * y - 1) {
                        success.setText("And the winner is: " + p.returnName() + "\nYour score: " + p.returnPlayerScore());
                        roll.setVisible(false);
                    }
                }

                if (currPlayer == maxPlayers - 1)
                    currPlayer = 0;
                else
                    currPlayer += 1;

                whichPlayer.setText(players.get(currPlayer).returnName());

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

