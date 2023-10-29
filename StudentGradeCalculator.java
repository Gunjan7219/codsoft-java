import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    JLabel[] labels;
    JTextField[] textFields;
    JButton calculateButton;
    JTextArea resultTextArea;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setLayout(null);

        labels = new JLabel[5];
        textFields = new JTextField[5];

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5"};

        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel(subjects[i]);
            labels[i].setBounds(50, 30 + i * 50, 100, 30);
            add(labels[i]);

            textFields[i] = new JTextField();
            textFields[i].setBounds(150, 30 + i * 50, 50, 30);
            add(textFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(100, 300, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        resultTextArea = new JTextArea();
        resultTextArea.setBounds(50, 350, 200, 100);
        resultTextArea.setEditable(false);
        add(resultTextArea);

        setSize(300, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = 0;

            for (int i = 0; i < 5; i++) {
                try {
                    int marks = Integer.parseInt(textFields[i].getText());
                    totalMarks += marks;
                    numSubjects++;
                } catch (NumberFormatException ex) {
                    // Handle invalid input (non-integer)
                    resultTextArea.setText("Invalid input. Please enter numbers only.");
                    return;
                }
            }

            double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
            String grade = calculateGrade(averagePercentage);

            resultTextArea.setText("Total Marks: " + totalMarks + "\nAverage Percentage: " + averagePercentage + "\nGrade: " + grade);
        }
    }

    public String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) return "A+";
        else if (averagePercentage >= 80) return "A";
        else if (averagePercentage >= 70) return "B";
        else if (averagePercentage >= 60) return "C";
        else if (averagePercentage >= 50) return "D";
        else return "F";
    }

    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}