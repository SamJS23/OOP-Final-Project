import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ViewQuestions extends JFrame {
    private String name;

    public ViewQuestions(String name) {
        // Frame settings
        setTitle("JTable from Multiple Tables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null); // Use null layout for absolute positioning

        // Create JTable with the model
        JTable table = new JTable(JDBC.viewQuestions());

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 800, 500); // Manually set bounds for the JScrollPane
        add(scrollPane);

        // Create a Go Back label
        JLabel goBackLabel = new JLabel("Go Back");
        goBackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        goBackLabel.setForeground(Color.BLACK);
        goBackLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand pointer

        // Add MouseListener to handle click events
        goBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Display title screen
                OptionsScreen titleScreenGui = new OptionsScreen(name);
                titleScreenGui.setLocationRelativeTo(ViewQuestions.this);

                // Dispose of this screen
                ViewQuestions.this.dispose();

                // Make title screen visible
                titleScreenGui.setVisible(true);
            }
        });

        // Manually set bounds for the Go Back label
        goBackLabel.setBounds(10, 530, 100, 30); // x, y, width, height
        add(goBackLabel);
    }

}
