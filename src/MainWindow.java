import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
    private JPanel cards;
    private WelcomeScreen ws;
    private BoardScreen bs;
    private CustomizeBoardScreen cbs;
    
    public void resetAll(){
        bs = new BoardScreen(this);
        cards.add(bs, "Three");
        bs.setUpPlayers();
        bs.setVisible(true);
        ((CardLayout)cards.getLayout()).show(cards,"Three");
    }
    
    public void showCard(String n){
        ((CardLayout)cards.getLayout()).show(cards, n);
    }
    
    public MainWindow(){
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cards = new JPanel(new CardLayout());
        
        ws = new WelcomeScreen(this);
        cards.add(ws, "One");
        
        bs = new BoardScreen(this);
        cards.add(bs, "Three");
        
        cbs = new CustomizeBoardScreen(this);
        cards.add(cbs, "Four");
        
        add(cards);
        ((CardLayout)cards.getLayout()).show(cards, "One");
        
        setVisible(true);
    }
}
