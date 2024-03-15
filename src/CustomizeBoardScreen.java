import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomizeBoardScreen extends JPanel {
    private JButton go;
    private JButton quit;
    private MainWindow mw;

    public void quitButtonActionListener() {
        mw.showCard("Two");
    }

    public void goButtonActionListener() {
        mw.showCard("Four");
    }

    public CustomizeBoardScreen(MainWindow mw) {
        this.mw = mw;

        JLabel mess = new JLabel("Customize Board [Under Construction] ");
        add(mess);

        JLabel uc = new JLabel("Default: 8x8 board with 8 randomly generated snakes/ladders");
        add(uc);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        go = new JButton("Start Game");
        quit = new JButton("Back");

        go.addActionListener(event -> goButtonActionListener());

        quit.addActionListener(event -> quitButtonActionListener());

        add(go);
        add(quit);
    }
}
