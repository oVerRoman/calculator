package com.github.calculator.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calculator;
    String number1;
    String number2;
    String result;
    String textField;
    char operator;

    @Test
    void calculator_returnSumOfTwoNumbersWhenOperatorIsPlus() {
        operator = '+';
        number1 = "44.8";
        number2 = "55.2";
        calculator = new Calculator(number1, number2);
        String expected = "100";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnDifferenceBetweenTwoNumbersWhenOperatorIsMinus() {
        operator = '-';
        number1 = "44.8";
        number2 = "55.2";
        calculator = new Calculator(number1, number2);
        String expected = "-10.4";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnProductOfTwoNumbersWhenOperatorIsTimes() {
        operator = '*';
        number1 = "7";
        number2 = "3.45";
        calculator = new Calculator(number1, number2);
        String expected = "24.15";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnQuotientOfTwoNumbersWhenOperatorIsObelus() {
        operator = '/';
        number1 = "44.8";
        number2 = "400";
        calculator = new Calculator(number1, number2);
        String expected = "0.112";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnSumOfTwoNumbersWhenOperatorIsNotDetermined() {
        number1 = "44.8";
        number2 = "400";
        calculator = new Calculator(number1, number2);
        String expected = "444.8";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnZeroWhenNumbersWasNotInput() {
        operator = '+';
        number1 = "";
        number2 = "";
        calculator = new Calculator(number1, number2);
        String expected = "0";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnInfinityWhenDivisionByZero() {
        operator = '/';
        number1 = "44.8";
        number2 = "0";
        calculator = new Calculator(number1, number2);
        String expected = "Infinity";
        assertEquals(expected, calculator.calculate(operator));
        number1 = "-44.8";
        calculator = new Calculator(number1, number2);
        expected = "-Infinity";
        assertEquals(expected, calculator.calculate(operator));
    }

    @Test
    void calculator_returnNegativeNumberWhenNegate() {
        textField = "34.6";
        calculator = new Calculator();
        String expected = "-34.6";
        assertEquals(expected, calculator.negate(textField));
    }
}
