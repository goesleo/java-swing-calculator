import javax.swing.*;
import java.awt.*;

public class CalculatorUI {
    private JFrame frame;
    private JTextField display;
    private JPanel buttonPanel;
    private CalculatorService calculatorService = new CalculatorService();
    private double firstNumber;
    private double secondNumber;
    private String operator;

    public CalculatorUI() {
        frame = new JFrame("Calculadora Profissional");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JTextField();
        frame.add(display, BorderLayout.NORTH);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] labels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "="};
        for (String label : labels) {
            JButton btn = new JButton(label);
            buttonPanel.add(btn);
            btn.addActionListener(event -> {
                if (label.equals("+") || label.equals("-") || label.equals("*") || label.equals("/")) {
                    firstNumber = Double.parseDouble(display.getText());
                    operator = label;
                    display.setText("");
                } else if (label.equals("=")) {
                    secondNumber = Double.parseDouble(display.getText());
                    double result = switch (operator) {
                        case "+"-> calculatorService.sum(firstNumber, secondNumber);
                        case "-"-> calculatorService.subtract(firstNumber, secondNumber);
                        case "*"-> calculatorService.multiply(firstNumber, secondNumber);
                        case "/"-> calculatorService.divide(firstNumber, secondNumber);
                        default -> 0;

                    };

                    display.setText(String.valueOf(result));


                } else {
                    display.setText(display.getText() + label);
                }
            });
        }
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorUI();

    }

}
