import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
//Creating class QuizScreen that extends JFrame class and implement ActionListener interface
public class QuizScreen extends JFrame implements ActionListener {
    //Initializing elements in the JFrame class
    JTextField textfield1 = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JLabel goBackLabel = new JLabel("Go Back");
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    //static variable
    static int seconds = 30;
    static int result;
    static int correct_guesses = 0;

    static private int currentQuestionNumber = 0;
    //instance variables
    private Category category;
    private ArrayList<Question> questions;
    private Question currentQuestion;
    private int numOfQuestions;
    private String name;

    //initialize timer that updates the second left
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if (seconds <= 0) {
                displayAnswer();
            }
        }
    });

    //Constructor of QuizScreen
    public QuizScreen(Category category, String name) {
        //Setting up JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        getContentPane().setBackground(new Color(192, 192, 192));
        setLayout(null);
        setResizable(false);

        //Field initialization
        this.category = category;
        this.name = name;

        //get questions and number of questions
        questions = JDBC.getQuestions(category);
        numOfQuestions = questions.size();

        //Setting the answers
        for (Question question : questions) {
            ArrayList<Answer> answers = JDBC.getAnswers(question);
            question.setAnswers(answers);
        }


        //Adding elements to the JFrame
        textfield1.setBounds(0, 0, 700, 50);
        textfield1.setBackground(new Color(105, 105, 105));
        textfield1.setForeground(new Color(0, 0, 0));
        textfield1.setFont(new Font("Arial", Font.BOLD, 30));
        textfield1.setBorder(BorderFactory.createBevelBorder(1));
        textfield1.setHorizontalAlignment(JTextField.CENTER);
        textfield1.setEditable(false);
        textfield1.setFocusable(false);

        textarea.setBounds(0, 50, 700, 60);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(105, 105, 105));
        textarea.setForeground(new Color(0, 0, 0));
        textarea.setFont(new Font("Arial", Font.BOLD, 18));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);
        textarea.setFocusable(false);

        buttonA.setBounds(0, 110, 100, 100);
        buttonA.setFont(new Font("Arial", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 210, 100, 100);
        buttonB.setFont(new Font("Arial", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 310, 100, 100);
        buttonC.setFont(new Font("Arial", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 410, 100, 100);
        buttonD.setFont(new Font("Arial", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(0, 0, 0));
        answer_labelA.setFont(new Font("Arial", Font.PLAIN, 35));

        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(0, 0, 0));
        answer_labelB.setFont(new Font("Arial", Font.PLAIN, 35));

        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(0, 0, 0));
        answer_labelC.setFont(new Font("Arial", Font.PLAIN, 35));

        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(0, 0, 0));
        answer_labelD.setFont(new Font("Arial", Font.PLAIN, 35));

        seconds_left.setBounds(565, 540, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Arial", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(565, 505, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(255, 0, 0));
        time_label.setFont(new Font("Arial", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer");

        goBackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        goBackLabel.setBounds(20, 630, 262, 20);
        goBackLabel.setForeground(new Color(0, 0, 0));
        //Moving to options screen page when goBackLabel is pressed
        goBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OptionsScreen titleScreenGui = new OptionsScreen(name);
                titleScreenGui.setLocationRelativeTo(QuizScreen.this);
                QuizScreen.this.dispose();
                titleScreenGui.setVisible(true);
            }
        });
        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Arial", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Arial", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        // adding elements
        add(goBackLabel);
        add(textfield1);
        add(textarea);
        add(buttonA);
        add(buttonB);
        add(buttonC);
        add(buttonD);
        add(answer_labelA);
        add(answer_labelB);
        add(answer_labelC);
        add(answer_labelD);
        add(time_label);
        add(seconds_left);

        //Calling nextQuestion method
        nextQuestion();
    }

    //action performed when A,B,C, or D buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        //disabling buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //marking the correctAnswer for the current question
        Answer correctAnswer = null;
        for (Answer answer : currentQuestion.getAnswers()) {
            if (answer.isCorrect()) {
                correctAnswer = answer;
                break;
            }
        }

        //Increasing the number of correct guesses if user chooses the correct answer
        if (e.getSource() == buttonA && answer_labelA.getText().equals(correctAnswer.getAnswerText())) {
            correct_guesses++;
        } else if (e.getSource() == buttonB && answer_labelB.getText().equals(correctAnswer.getAnswerText())) {
            correct_guesses++;
        } else if (e.getSource() == buttonC && answer_labelC.getText().equals(correctAnswer.getAnswerText())) {
            correct_guesses++;
        } else if (e.getSource() == buttonD && answer_labelD.getText().equals(correctAnswer.getAnswerText())) {
            correct_guesses++;
        }

        //calling displayAnswer method
        displayAnswer();
    }

    //nextQuestion method that display the next question
    public void nextQuestion() {

        //display results if there is no more questions
        if (currentQuestionNumber >= numOfQuestions) {
            results();

        } else {

            //moving on to the next question and updating the question number, question text, and possible answers
            currentQuestion = questions.get(currentQuestionNumber);
            currentQuestionNumber++;
            textfield1.setText("Question " + (currentQuestionNumber));
            textarea.setText(currentQuestion.getQuestionText());
            answer_labelA.setText(currentQuestion.getAnswers().get(0).getAnswerText());
            answer_labelB.setText(currentQuestion.getAnswers().get(1).getAnswerText());
            answer_labelC.setText(currentQuestion.getAnswers().get(2).getAnswerText());
            answer_labelD.setText(currentQuestion.getAnswers().get(3).getAnswerText());
            //starting the count-down timer
            timer.start();
        }
    }


    //display answer method that displays the answer
    public void displayAnswer() {
        //stopping the count-down timer
        timer.stop();
        // disabling buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //marking the correct answer
        Answer correctAnswer = null;
        for (Answer answer : currentQuestion.getAnswers()) {
            if (answer.isCorrect()) {
                correctAnswer = answer;
                break;
            }
        }

        //changing the answer label to red if the answer is incorrect and green if the answer is correct
        if (!answer_labelA.getText().equals(correctAnswer.getAnswerText())) {
            answer_labelA.setForeground(new Color(255, 0, 0));
        }
        else {
            answer_labelA.setForeground(new Color(25, 255, 0));
        }
        if (!answer_labelB.getText().equals(correctAnswer.getAnswerText())) {
            answer_labelB.setForeground(new Color(255, 0, 0));
        }
        else {
            answer_labelB.setForeground(new Color(25, 255, 0));
        }
        if (!answer_labelC.getText().equals(correctAnswer.getAnswerText())) {
            answer_labelC.setForeground(new Color(255, 0, 0));
        }
        else {
            answer_labelC.setForeground(new Color(25, 255, 0));
        }
        if (!answer_labelD.getText().equals(correctAnswer.getAnswerText())) {
            answer_labelD.setForeground(new Color(255, 0, 0));
        }
        else {
            answer_labelD.setForeground(new Color(25, 255, 0));
        }



        // pause timer that pauses for 2 seconds
        Timer pause = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //resetting back the color of the answer labels
                answer_labelA.setForeground(new Color(0, 0, 0));
                answer_labelB.setForeground(new Color(0, 0, 0));
                answer_labelC.setForeground(new Color(0, 0, 0));
                answer_labelD.setForeground(new Color(0, 0, 0));

                //resetting back the number of seconds variable
                seconds = 30;
                //resetting the seconds_left text to the original amount of seconds
                seconds_left.setText(String.valueOf(seconds));
                //re-enabling the buttons
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                //calling the nextQuestion methods
                nextQuestion();
            }
        });
        //setting the pause timer so it does not repeat
        pause.setRepeats(false);
        //starting the pause timer
        pause.start();
    }

    public void results() {
        //disabling the buttons
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        //calculating the results
        result = (int) ((correct_guesses / (double) numOfQuestions) * 100);

        //showing the results in the screen
        textfield1.setText("RESULTS");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("(" + correct_guesses + "/" + numOfQuestions + ")");
        percentage.setText(result + "%");

        //Setting up JButton that saves the score
        JButton score = new JButton("Save score");
        score.setFont(new Font("Arial", Font.BOLD, 16));
        score.setBounds(200, 450, 250, 45);
        score.setForeground(new Color(0, 0, 0));
        score.setBackground(new Color(255, 255, 255));
        //saves score and shows pop-up when score button is pressed
        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //saving score to database and displaying pop-up if it is successfull and then moving back to the title screen
                if (JDBC.saveScoreToDatabase(name, category, result)) {
                    // Add your code here for what happens when the input is valid
                    JOptionPane.showMessageDialog(null, "Score saved!");
                    TitleScreen title = new TitleScreen();
                    title.setLocationRelativeTo(QuizScreen.this);

                    // dispose of this screen
                    QuizScreen.this.dispose();

                    // display title screen
                    title.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Score cannot be saved!");
                }
                }

            });

        //adding the elements
        add(score);
        add(number_right);
        add(percentage);
    }
}
