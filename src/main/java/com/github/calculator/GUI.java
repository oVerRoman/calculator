package com.github.calculator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.calculator.math.Calculator;

public class GUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[9];
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton decimalButton;
    private JButton equalButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton negativeButton;
    private JPanel panel;
    private Font font = new Font("Courier", Font.PLAIN, 30);
    private String number1 = "0";
    private String number2 = "0";
    private String result = "0";
    private char operator;

    public GUI() {
        super("Calculator");
        setupFrame();
        setupTextField();
        setupButtons();
        setupPanel();
        addElementsToFrame();
    }

    public void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 550);
        setLayout(null); // no one scheme is used
        setResizable(false);
    }

    public void setupTextField() {
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false);
    }

    public void setupButtons() {
        addButton = new JButton("+");
        addButton.setName("plus");
        subtractButton = new JButton("-");
        subtractButton.setName("minus");
        multiplyButton = new JButton("*");
        multiplyButton.setName("times");
        divideButton = new JButton("/");
        divideButton.setName("obelus");
        decimalButton = new JButton(".");
        decimalButton.setName("decimal");
        equalButton = new JButton("=");
        equalButton.setName("equal");
        deleteButton = new JButton("<<");
        deleteButton.setName("delete");
        clearButton = new JButton("C");
        clearButton.setName("clear");
        negativeButton = new JButton("+/-");
        negativeButton.setName("negative");
        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;
        functionButtons[4] = decimalButton;
        functionButtons[5] = equalButton;
        functionButtons[6] = deleteButton;
        functionButtons[7] = clearButton;
        functionButtons[8] = negativeButton;
        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(font);
            functionButtons[i].setFocusable(false);// to avoid line around symbol
        }
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setName(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);// to avoid line around symbol
        }
        deleteButton.setBounds(50, 430, 100, 50);
        clearButton.setBounds(150, 430, 100, 50);
        negativeButton.setBounds(250, 430, 100, 50);
    }

    public void setupPanel() {
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(decimalButton);
        panel.add(numberButtons[0]);
        panel.add(equalButton);
        panel.add(divideButton);
    }

    public void addElementsToFrame() {
        add(panel);
        add(deleteButton);
        add(clearButton);
        add(negativeButton);
        add(textField);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (textField.getText().contains("Infinity") || textField.getText().equals("0")) {
                    textField.setText("");
                }
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decimalButton && !textField.getText().contains(".")) {
            if (textField.getText().equals("") || textField.getText().contains("Infinity")) {
                textField.setText("0.");
            } else if (textField.getText().equals("-")) {
                textField.setText("-0.");
            } else {
                textField.setText(textField.getText().concat("."));
            }
        }
        if (e.getSource() == addButton) {
            number1 = textField.getText();
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subtractButton) {
            number1 = textField.getText();
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == multiplyButton) {
            number1 = textField.getText();
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divideButton) {
            number1 = textField.getText();
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equalButton) {
            number2 = textField.getText();
            if (number1.equals("Infinity") || number2.equals("Infinity")) {
                result = "Infinity";
            } else if (number1.equals("-Infinity") || number2.equals("-Infinity")) {
                result = "-Infinity";
            } else {
                Calculator calculator = new Calculator(number1, number2);
                result = calculator.calculate(operator);
            }
            textField.setText(result);
            number1 = result;
        }
        if (e.getSource() == clearButton) {
            textField.setText("");
            number1 = "0";
            number2 = "0";
            result = "0";
        }
        if (e.getSource() == deleteButton) {
            if (textField.getText().contains("Infinity")) {
                textField.setText("");
            }
            String currentText = textField.getText();
            textField.setText("");
            for (int i = 0; i < currentText.length() - 1; i++) {
                textField.setText(textField.getText().concat(String.valueOf(currentText.charAt(i))));
            }
            if (textField.getText().equals("-")) {
                textField.setText("");
            }
        }
        if (e.getSource() == negativeButton) {
            if (textField.getText().equals("")) {
                textField.setText("0");
            } else if (textField.getText().equals("Infinity")) {
                textField.setText("-Infinity");
            } else if (textField.getText().equals("-Infinity")) {
                textField.setText("Infinity");
            } else {
                Calculator calculator = new Calculator();
                result = calculator.negate(textField.getText());
                textField.setText(result);
            }
        }
    }
}