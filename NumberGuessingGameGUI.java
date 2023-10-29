import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGameGUI extends JFrame {
    private int randomNumber;
    private int numberOfGuesses;

    private JTextField guessTextField;
    private JTextArea resultTextArea;
    private JButton guessButton;

    public NumberGuessingGameGUI() {
        super("Number Guessing Game");

        randomNumber = generateRandomNumber();
        numberOfGuesses = 0;

        guessTextField = new JTextField(10);
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        setLayout(new FlowLayout());

        add(new JLabel("Enter your guess:"));
        add(guessTextField);
        add(guessButton);
        add(new JScrollPane(resultTextArea));

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void handleGuess() {
        int guessedNumber;
        try {
            guessedNumber = Integer.parseInt(guessTextField.getText());
        } catch (NumberFormatException e) {
            resultTextArea.append("Invalid input. Please enter a valid number.\n");
            guessTextField.setText("");
            return;
        }

        numberOfGuesses++;

        if (guessedNumber == randomNumber) {
            resultTextArea.append("Congratulations! You guessed the number " + randomNumber + " in " + numberOfGuesses + " guesses.\n");
            guessButton.setEnabled(false);
        } else if (guessedNumber < randomNumber) {
            resultTextArea.append("Try a higher number.\n");
        } else {
            resultTextArea.append("Try a lower number.\n");
        }

        guessTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGameGUI();
            }
        });
    }
}