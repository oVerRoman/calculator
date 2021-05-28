package com.github.calculator.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    private BigDecimal num1;
    private BigDecimal num2;
    private String result;

    public Calculator() {
    }

    public Calculator(String num1, String num2) {
        if (num1.equals("")) {
            this.num1 = BigDecimal.ZERO;
        } else {
            this.num1 = BigDecimal.valueOf(Double.parseDouble(num1));
        }
        if (num2.equals("")) {
            this.num2 = BigDecimal.ZERO;
        } else {
            this.num2 = BigDecimal.valueOf(Double.parseDouble(num2));
        }
    }

    public String calculate(char operator) {
        if (operator == '+') {
            result = num1.add(num2).setScale(12, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        } else if (operator == '-') {
            result = num1.subtract(num2).setScale(12, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        } else if (operator == '*') {
            result = num1.multiply(num2).setScale(12, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        } else if (operator == '/') {
            if (num2.compareTo(BigDecimal.ZERO) == 0 && num1.equals(num1.abs())) {
                result = "Infinity";
            } else if (num2.compareTo(BigDecimal.ZERO) == 0 && !num1.equals(num1.abs())) {
                result = "-Infinity";
            } else {
                result = num1.divide(num2, 12, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
            }
        } else {
            result = num1.add(num2).setScale(12, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        }
        return result;
    }

    public String negate(String textField) {
        return BigDecimal.valueOf(Double.parseDouble(textField)).negate().stripTrailingZeros().toPlainString();
    }
}