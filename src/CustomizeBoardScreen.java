import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomizeBoardScreen extends JPanel {
    
    JButton go;
    JButton quit;
    MainWindow mw;

    public void quitButtonActionListener() {
        mw.showCard("Two");
    }

    public void goButtonActionListener() {
        mw.showCard("Four");
    }

    public CustomizeBoardScreen(MainWindow mw) {
        this.mw = mw;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        go = new JButton("Start Game");
        quit = new JButton("Back");

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

        add(go);
        add(quit);
    }

}

