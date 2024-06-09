import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame {
    //Initializing the elements of the JFrame class
    JButton start = new JButton();
    JButton exit = new JButton();
    JButton view = new JButton();

    //TitleScreen constructor
public TitleScreen(){
    //Setting up JFrame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700, 700);
    getContentPane().setBackground(new Color(192, 192, 192));
    setLayout(null);
    setResizable(false);

    //Setting up title label, start button, view score button, and exit button
    JLabel titleLabel = new JLabel("Game Quiz");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
    titleLabel.setBounds(250, 100, 200, 50);
    titleLabel.setForeground(new Color(0, 0, 0));
    //button to the JFrame
    add(titleLabel);

    start.setBounds(190, 200, 300, 100);
    start.setFont(new Font("Arial", Font.BOLD, 35));
    start.setFocusable(false);
    //Moving to EnterNameScreen when start button is clicked
    start.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EnterNameScreen enter = new EnterNameScreen();
            enter.setLocationRelativeTo(TitleScreen.this);

            // dispose of this screen
            TitleScreen.this.dispose();

            // display quiz screen
            enter.setVisible(true);
        }

    });

    start.setText("Start");
    add(start);

    exit.setBounds(190, 500, 300, 100);
    exit.setFont(new Font("Arial", Font.BOLD, 35));
    exit.setFocusable(false);
    //Exiting out of the application when exit button is pressed
    exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // dispose of this screen
            TitleScreen.this.dispose();
        }

    });

    exit.setText("Exit");
    add(exit);

    view.setBounds(190, 350, 300, 100);
    view.setFont(new Font("Arial", Font.BOLD, 35));
    view.setFocusable(false);
    //Moving to the ViewScore screen when view buttonis pressed
    view.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Creating an instance of ViewScores class
            ViewScores viewscores = new ViewScores();
            viewscores.setLocationRelativeTo(TitleScreen.this);

            // dispose of this screen
            TitleScreen.this.dispose();

            // display quiz screen
            viewscores.setVisible(true);
        }


    });

    view.setText("View Scores");
    add(view);

}
}
