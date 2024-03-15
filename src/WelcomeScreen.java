import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeScreen extends JPanel {
    private JButton startButton;
    private JButton quitButton;
    private MainWindow mainWindow;

    public WelcomeScreen(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        JLabel welcomeLabel = new JLabel("Welcome to Snakes and Ladders!");
        JLabel messageLabel = new JLabel("Click Start to begin or Quit to exit.");

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startButtonActionListener());

        quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> quitButtonActionListener());

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(welcomeLabel);
        add(messageLabel);
        add(startButton);
        add(quitButton);
    }

    private void startButtonActionListener() {
        mainWindow.showCard("Three");
    }

    private void quitButtonActionListener() {
        mainWindow.showCard("Four");
    }
}
