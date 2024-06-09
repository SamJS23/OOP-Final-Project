import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Objects;
import javax.swing.*;


public class OptionsScreen extends JFrame {

    JTextField textfield2 = new JTextField();
    JButton option1 = new JButton();
    JButton option2 = new JButton();
    JButton option3 = new JButton();
    JButton create = new JButton();
    JButton viewQuestions = new JButton();
    private String name;

    public OptionsScreen(String name){
        this.name = name;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        getContentPane().setBackground(new Color(192, 192,192 ));
        setLayout(null);
        setResizable(false);

        textfield2.setBounds(0, 0, 700, 50);
        textfield2.setBackground(new Color(105, 105, 105));
        textfield2.setForeground(new Color(0, 0, 0));
        textfield2.setFont(new Font("Arial", Font.BOLD, 30));
        textfield2.setBorder(BorderFactory.createBevelBorder(1));
        textfield2.setHorizontalAlignment(JTextField.CENTER);
        textfield2.setEditable(false);
        textfield2.setFocusable(false);
        textfield2.setText("Choose your quiz");

        option1.setBounds(200, 100, 300, 100);
        option1.setFont(new Font("Arial", Font.BOLD, 35));
        option1.setFocusable(false);
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = JDBC.getCategory(option1.getText());
                if(category == null) return;
                QuizScreen quizScreenGui = new QuizScreen(category, name);
                quizScreenGui.setLocationRelativeTo(OptionsScreen.this);

                // dispose of this screen
                OptionsScreen.this.dispose();

                // display quiz screen
                quizScreenGui.setVisible(true);
            }

    });

        option1.setText("Math");

        option2.setBounds(200, 250, 300, 100);
        option2.setFont(new Font("Arial", Font.BOLD, 35));
        option2.setFocusable(false);
        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = JDBC.getCategory(option2.getText());
                if(category == null) return;
                QuizScreen quizScreenGui = new QuizScreen(category, name);
                quizScreenGui.setLocationRelativeTo(OptionsScreen.this);

                // dispose of this screen
                OptionsScreen.this.dispose();

                // display quiz screen
                quizScreenGui.setVisible(true);
            }

        });

        option2.setText("Science");

        option3.setBounds(200, 400, 300, 100);
        option3.setFont(new Font("Arial", Font.BOLD, 35));
        option3.setFocusable(false);
        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = JDBC.getCategory(option3.getText());
                if(category == null) return;
                QuizScreen quizScreenGui = new QuizScreen(category, name);
                quizScreenGui.setLocationRelativeTo(OptionsScreen.this);

                // dispose of this screen
                OptionsScreen.this.dispose();

                // display quiz screen
                quizScreenGui.setVisible(true);
            }

        });


        option3.setText("History");

        create.setBounds(148, 530, 400, 40 );
        create.setFont(new Font("Arial", Font.BOLD, 20));
        create.setFocusable(false);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateQuestion createQuestion = new CreateQuestion(name);
                createQuestion.setLocationRelativeTo(OptionsScreen.this);

                // dispose of this screen
                OptionsScreen.this.dispose();

                // display quiz screen
                createQuestion.setVisible(true);
            }

        });
        create.setText("Create your own questions");

        viewQuestions.setBounds(148, 590, 400, 40 );
        viewQuestions.setFont(new Font("Arial", Font.BOLD, 20));
        viewQuestions.setFocusable(false);
        viewQuestions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            ViewQuestions viewquestions = new ViewQuestions(name);
            viewquestions.setLocationRelativeTo(OptionsScreen.this);

            // dispose of this screen
            OptionsScreen.this.dispose();

            // display quiz screen
                viewquestions.setVisible(true);
        }

    });
        viewQuestions.setText("View questions");


        add(textfield2);
        add(option1);
        add(option2);
        add(option3);
        add(create);
        add(viewQuestions);
        setVisible(true);


    }



}

