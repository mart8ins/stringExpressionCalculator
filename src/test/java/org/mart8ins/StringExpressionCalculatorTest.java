package org.mart8ins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringExpressionCalculatorTest {

    @Test
    void calculate_TWO_Operands() {
        int resADD =  StringExpressionCalculator.getResult("2+3");
        int resSUB =  StringExpressionCalculator.getResult("100-2");
        int resMUl =  StringExpressionCalculator.getResult("10 * 5");
        int resDIV =  StringExpressionCalculator.getResult("15/3");
        Assertions.assertEquals(5, resADD);
        Assertions.assertEquals(98, resSUB);
        Assertions.assertEquals(50, resMUl);
        Assertions.assertEquals(5, resDIV);
    }

    @Test
    void calculate_THREE_Operands() {
        int resADD =  StringExpressionCalculator.getResult("2 + 3 + 6");
        int resSUB =  StringExpressionCalculator.getResult("8 -2 -4");
        int resMUL =  StringExpressionCalculator.getResult("2 * 3 * 6");
        int resDIV =  StringExpressionCalculator.getResult("100 / 2 / 2");
        Assertions.assertEquals(11, resADD);
        Assertions.assertEquals(2, resSUB);
        Assertions.assertEquals(36, resMUL);
        Assertions.assertEquals(25, resDIV);
    }

    @Test
    void calculate_THREE_Operands_PRIORITY_PARANTHESES_ONE_LEVEL() {
        int res1 =  StringExpressionCalculator.getResult("2 + (3 * 6)");
        int res2 =  StringExpressionCalculator.getResult("2 * (10 - 5)");
        int res3 =  StringExpressionCalculator.getResult("(6 + 6) * 2");
        int res4 =  StringExpressionCalculator.getResult("100 / (2 / 2)");
        Assertions.assertEquals(20, res1);
        Assertions.assertEquals(10, res2);
        Assertions.assertEquals(24, res3);
        Assertions.assertEquals(100, res4);
    }

    @Test
    void calculate_THREE_Operands_PRIORITY_OPERATIONS() {
        int res1 =  StringExpressionCalculator.getResult("2 + 2 * 6");
        int res2 =  StringExpressionCalculator.getResult("2 + 10 / 5");
        int res3 =  StringExpressionCalculator.getResult("100 / 10 * 5");
        Assertions.assertEquals(14, res1);
        Assertions.assertEquals(4, res2);
        Assertions.assertEquals(2, res3);
    }

    @Test
    void calculate_FOUR_Operands_PRIORITY_OPERATIONS() {
        int res1 =  StringExpressionCalculator.getResult("2 + 2 * 6 + 5");
        int res2 =  StringExpressionCalculator.getResult("2 + 50 / 5 * 5");
        int res3 =  StringExpressionCalculator.getResult("100 / 10 * 5 + 10");
        Assertions.assertEquals(19, res1);
        Assertions.assertEquals(4, res2);
        Assertions.assertEquals(12, res3);
    }

    @Test
    void calculate_FOUR_Operands_PRIORITY_PARANTHESES_AND_OPERATIONS() {
        int res1 =  StringExpressionCalculator.getResult("(2 + 2) * 2 * (2 + 2)");
        int res2 =  StringExpressionCalculator.getResult("(10-2) / 2 * (2 + 2)");
        Assertions.assertEquals(32, res1);
        Assertions.assertEquals(1, res2);
    }
}