import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CreateQuestion extends JFrame {

    // Instance Variables
    private JTextArea questionTextArea;
    private JTextField[] answerTextFields;
    private ButtonGroup buttonGroup;
    private JRadioButton[] answerRadioButtons;
    private String name;

    // Constructor for the CreateQuestion class
    public CreateQuestion(String name) {
        setSize(851, 565);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(192, 192, 192));

        answerRadioButtons = new JRadioButton[4];
        answerTextFields = new JTextField[4];
        buttonGroup = new ButtonGroup();


        addGuiComponents();
    }

    private void addGuiComponents() {
        // title label
        JLabel titleLabel = new JLabel("Create your own Question");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(50, 15, 310, 29);
        titleLabel.setForeground(new Color(0, 0, 0));
        add(titleLabel);

        // question label
        JLabel questionLabel = new JLabel("Question: ");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBounds(50, 60, 93, 20);
        questionLabel.setForeground(new Color(0, 0, 0));
        add(questionLabel);

        // question text area
        questionTextArea = new JTextArea();
        questionTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        questionTextArea.setBounds(50, 90, 310, 110);
        questionTextArea.setForeground(new Color(0, 0, 0));
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        add(questionTextArea);

        // category label
        JLabel categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        categoryLabel.setBounds(50, 250, 93, 20);
        categoryLabel.setForeground(new Color(0, 0, 0));
        add(categoryLabel);

        // category text input field
        String[] items = {"Math","Science","History"};
        JComboBox<String> categoriesMenu = new JComboBox<>(items);
        categoriesMenu.setBounds(50, 280, 337, 45);
        categoriesMenu.setForeground(new Color(0, 0, 0));
        add(categoriesMenu);

        addAnswerComponents();

        // submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBounds(300, 450, 262, 45);
        submitButton.setForeground(new Color(0, 0, 0));
        submitButton.setBackground(new Color(255, 255, 255));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    String question = questionTextArea.getText();
                    String category = categoriesMenu.getSelectedItem().toString();
                    String[] answers = new String[answerTextFields.length];
                    int correctIndex = 0;
                    for (int i = 0; i < answerTextFields.length; i++) {
                        answers[i] = answerTextFields[i].getText();
                        if (answerRadioButtons[i].isSelected()) {
                            correctIndex = i;
                        }
                    }


                    // update database
                    if (JDBC.saveQuestionCategoryAndAnswersToDatabase(question, category,
                            answers, correctIndex)) {
                        // update successful
                        JOptionPane.showMessageDialog(CreateQuestion.this,
                                "Successfully Added Question!");

                        // reset fields
                        resetFields();
                    } else {
                        // update failed
                        JOptionPane.showMessageDialog(CreateQuestion.this,
                                "Failed to Add Question...");
                    }
                } else {
                    // invalid input
                    JOptionPane.showMessageDialog(CreateQuestion.this,
                            "Error: Invalid Input");
                }
            }
        });
        add(submitButton);

        // go back label
        JLabel goBackLabel = new JLabel("Go Back");
        goBackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        goBackLabel.setBounds(300, 500, 262, 20);
        goBackLabel.setForeground(new Color(0, 0, 0));
        goBackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        goBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // display title screen
                OptionsScreen titleScreenGui = new OptionsScreen(name);
                titleScreenGui.setLocationRelativeTo(CreateQuestion.this);

                // dispose of this screen
                CreateQuestion.this.dispose();

                // make title screen visible
                titleScreenGui.setVisible(true);
            }
        });
        add(goBackLabel);
    }

    private void addAnswerComponents() {
        // vertical spacing between each answer component
        int verticalSpacing = 100;

        // create 4 answer labels, 4 radio buttons, and 4 text input fields
        for (int i = 0; i < 4; i++) {
            // answer label
            JLabel answerLabel = new JLabel("Answer #" + (i + 1));
            answerLabel.setFont(new Font("Arial", Font.BOLD, 16));
            answerLabel.setBounds(470, 60 + (i * verticalSpacing), 93, 20);
            answerLabel.setForeground(new Color(0, 0, 0));
            add(answerLabel);

            // radio button
            answerRadioButtons[i] = new JRadioButton();
            answerRadioButtons[i].setBounds(440, 100 + (i * verticalSpacing), 21, 21);
            answerRadioButtons[i].setBackground(null);
            buttonGroup.add(answerRadioButtons[i]);
            add(answerRadioButtons[i]);

            // answer text input field
            answerTextFields[i] = new JTextField();
            answerTextFields[i].setBounds(470, 90 + (i * verticalSpacing), 310, 36);
            answerTextFields[i].setFont(new Font("Arial", Font.PLAIN, 16));
            answerTextFields[i].setForeground(new Color(0, 0, 0));
            add(answerTextFields[i]);
        }

        // give a default value to the first radio button
        answerRadioButtons[0].setSelected(true);
    }

    // true - valid input
    // false - invalid input
    public boolean validateInput() {
        // make sure that question field is not empty
        if (questionTextArea.getText().replaceAll(" ", "").length() <= 0) return false;


        // make sure all answer fields are not empty
        for (int i = 0; i < answerTextFields.length; i++) {
            if (answerTextFields[i].getText().replaceAll(" ", "").length() <= 0)
                return false;
        }

        return true;
    }

    private void resetFields() {
        questionTextArea.setText("");
        for (int i = 0; i < answerTextFields.length; i++) {
            answerTextFields[i].setText("");
        }

    }
}












