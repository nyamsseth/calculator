import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calculator extends JFrame {
    private JTextField operationTextField;
    private JRadioButton convertToPostfixRadioButton;
    private JButton calculateButton;

    public Calculator() {
        setTitle("Infix and Postfix Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));

        operationTextField = new JTextField("Enter the operation", 20);
        convertToPostfixRadioButton = new JRadioButton("Convert to Postfix");
        calculateButton = new JButton("Calculate");

        Font font = new Font("Arial", Font.BOLD, 20);
        operationTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        convertToPostfixRadioButton.setFont(font);
        calculateButton.setFont(font);
        operationTextField.setBackground(new Color(240, 240, 240));
        convertToPostfixRadioButton.setBackground(new Color(240, 240, 240));
        calculateButton.setBackground(Color.CYAN);

        convertToPostfixRadioButton.addActionListener(e -> {
            // Action for radio button selection (if needed)
        });

        calculateButton.addActionListener(e -> calculateExpression());

        add(operationTextField);
        add(convertToPostfixRadioButton);
        add(calculateButton);

        setSize(500, 300);
        setVisible(true);
    }

    private void calculateExpression() {
        String exp = operationTextField.getText();
        String postfixExpression = infixToPostfix(exp);

        try {
            if (convertToPostfixRadioButton.isSelected()) {
                double result = evaluatePostfix(postfixExpression);
                operationTextField.setText("Infix Expression: " + exp +
                        "\n Postfix Expression: " + postfixExpression +
                        "\n Result: " + result);
            }
        } catch (Exception ex) {
            operationTextField.setText("Error: " + ex.getMessage());
            // ex.printStackTrace();
        }
    }

    private static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return 0;
        }
    }

    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char token = infix.charAt(i);

            if (Character.isDigit(token)) {
                postfix.append(token);
            } else if (token == '+' || token == '-' || token == '*' || token == '/') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' &&
                        precedence(operatorStack.peek()) >= precedence(token)) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(token);
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    public static double evaluatePostfix(String postfixExpression) {
        Stack<Double> operandStack = new Stack<>();

        for (char c : postfixExpression.toCharArray()) {
            if (Character.isDigit(c)) {
                operandStack.push((double) (c - '0'));
            } else {
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();

                switch (c) {
                    case '+':
                        operandStack.push(operand1 + operand2);
                        break;
                    case '-':
                        operandStack.push(operand1 - operand2);
                        break;
                    case '*':
                        operandStack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 != 0) {
                            operandStack.push(operand1 / operand2);
                        } else {
                            throw new ArithmeticException("Error: Division by zero");
                        }
                        break;
                }
            }
        }

        if (!operandStack.isEmpty()) {
            return operandStack.pop();
        } else {
            throw new IllegalArgumentException("Error: Empty operand stack");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PostfixStandardFix::new);
    }
}
