
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private double firstNumber, secondNumber, result;
    private String operation;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sin", "cos", "tan", "sqrt"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]+") || command.equals(".")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstNumber = Double.parseDouble(textField.getText());
            operation = command;
            textField.setText("");
        } else if (command.equals("=")) {
            secondNumber = Double.parseDouble(textField.getText());
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) {
                        textField.setText("Error: Division by zero");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;
            }
            textField.setText(String.valueOf(result));
        } else if (command.equals("sin") || command.equals("cos") || command.equals("tan") || command.equals("sqrt")) {
            double number = Double.parseDouble(textField.getText());
            switch (command) {
                case "sin":
                    result = Math.sin(Math.toRadians(number));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(number));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(number));
                    break;
                case "sqrt":
                    result = Math.sqrt(number);
                    break;
            }
            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}