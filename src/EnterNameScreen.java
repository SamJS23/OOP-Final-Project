import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNameScreen extends JFrame {
    // Making nameText
    JTextField nameText = new JTextField();

    // constructor for EnterNameScreen class
    public EnterNameScreen() {

        // Setting up the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        getContentPane().setBackground(new Color(192, 192, 192));
        setLayout(null);
        setResizable(false);

        // Editing the nameText JTextEdit and adding it to the JFrame
        nameText.setBounds(190, 150, 310, 36);
        nameText.setFont(new Font("Arial", Font.PLAIN, 16));
        nameText.setForeground(new Color(0, 0, 0));
        add(nameText);

        // Creating new JLabel for entering name, editing the JLabel, and adding it to the JFrame
        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(265, 100, 200, 50);
        nameLabel.setForeground(new Color(0, 0, 0));
        add(nameLabel);

        // Creating new JButton for submitting, editing the JButton, and adding it to the JFrame
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBounds(225, 230, 250, 45);
        submitButton.setForeground(new Color(0, 0, 0));
        submitButton.setBackground(new Color(255, 255, 255));

        // Adding action listener to the submitButton
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Validating input
                if (validateInput()) {
                    // Getting the name the user inputted
                    String name = nameText.getText();

                    // Saving the name to the database and display a pop up message
                    if (JDBC.saveNameToDatabase(name)) {
                        // Pop up message when name is saved to the database
                        JOptionPane.showMessageDialog(null, "Name submitted: " + nameText.getText());
                        OptionsScreen options = new OptionsScreen(name);
                        options.setLocationRelativeTo(EnterNameScreen.this);

                        // dispose of this screen
                        EnterNameScreen.this.dispose();

                        // make options screen visible
                        options.setVisible(true);

                    } else {
                        // error messsage
                        JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                    }

                }
            }
        });
        add(submitButton);
    }

    //validating input of the nameText JTextField
    public boolean validateInput() {
        // make sure that name field is not empty
        if (nameText.getText().replaceAll(" ", "").length() <= 0) return false;
        return true;
    }
}
