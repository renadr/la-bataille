package upmc.UI;

import upmc.game.Bataille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

/**
 * Created by Adrien on 10/12/2017.
 */
public class GUI extends JFrame {

    protected static final String windowName = "Jeu de la bataille";
    protected int widthWindow;
    protected int heightWindow;
    protected Bataille bataille;
    protected SelectPlayersUI panel1;
    protected GameUI panel2;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        super(windowName);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.widthWindow = screenSize.width;
        this.heightWindow = screenSize.height;
        this.settings();
    }

    public void settings() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.bataille = new Bataille(5);

        this.selectPlayersPanel();
    }

    public void selectPlayersPanel() {
        this.panel1 = new SelectPlayersUI();
        this.add(panel1);
        this.setVisible(true);
        panel1.getButtonAccept().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String player1value = panel1.getTextFieldPlayer1().getText();
                String player2value;
                if(panel1.getState() == ItemEvent.SELECTED) {
                    player2value = panel1.getTextFieldPlayer2().getText();
                } else {
                    player2value = "IA";
                }
                bataille.setPlayers(player1value,player2value);
                getContentPane().removeAll();
                GamePanel();
            }
        });
    }

    public void GamePanel() {
        this.panel2 = new GameUI(this.bataille);
        add(panel2);
//        repaint();
        this.setVisible(true);
    }

}
