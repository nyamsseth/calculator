package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame {
    private JTextField inputField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, sinButton, cosButton, equalButton;
    private double currentValue = 0.0; // For storing intermediate values
    private String currentOperation = "";

    public AdvancedCalculator() {
        setTitle("Advanced Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);

        // Initialize components
        inputField = new JTextField(15);
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        equalButton = new JButton("=");

        // Set layout
        setLayout(new GridLayout(4, 3));

        // Add components to the frame
        add(inputField);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(sinButton);
        add(cosButton);
        add(equalButton);

        // Set up action listeners for buttons

        // Operation buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("+");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("-");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("*");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation("/");
            }
        });

        // Sine button
        sinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the current value from the text field
                    double currentValue = Double.parseDouble(inputField.getText());

                    // Calculate the sine value of the current value
                    double result = Math.sin(currentValue);

                    // Update the input field with the result
                    inputField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid number
                    inputField.setText("Invalid input");
                }
            }
        });

        // Cosine button
        cosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the current value from the text field
                    double currentValue = Double.parseDouble(inputField.getText());

                    // Calculate the cosine value of the current value
                    double result = Math.cos(currentValue);

                    // Update the input field with the result
                    inputField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid number
                    inputField.setText("Invalid input");
                }
            }
        });

        // Equal button
        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value2 = Double.parseDouble(inputField.getText());
                    double result = 0;
                    switch (currentOperation) {
                        case "+":
                            result = currentValue + value2;
                            break;
                        case "-":
                            result = currentValue - value2;
                            break;
                        case "*":
                            result = currentValue * value2;
                            break;
                        case "/":
                            if (value2 != 0) {
                                result = currentValue / value2;
                            } else {
                                inputField.setText("Division by zero error");
                                return;
                            }
                            break;
                    }
                    inputField.setText(String.valueOf(result));
                    currentValue = result;
                } catch (NumberFormatException ex) {
                    inputField.setText("Invalid input");
                }
            }
        });

        // Set the frame visible
        setVisible(true);
    }

    // Method to perform arithmetic operations
    private void performOperation(String operation) {
        try {
            currentValue = Double.parseDouble(inputField.getText());
            currentOperation = operation;
            inputField.setText("");
        } catch (NumberFormatException ex) {
            inputField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdvancedCalculator();
            }
        });
    }
}
