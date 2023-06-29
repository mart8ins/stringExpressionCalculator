package org.mart8ins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringExpressionCalculator {

    public static int getResult(String expression) {
        List<String> cleaned = cleanExpression(expression);
        List<String> parenthesesCalculated = calculateParantheses(cleaned);
        List<String> multiplyCalculated = calculatePriorityOperation(parenthesesCalculated, "*");
        List<String> divideCalculated = calculatePriorityOperation(multiplyCalculated, "/");
        return calculateExpression(divideCalculated);
    }

    private static  List<String> cleanExpression(String expression){
        return Arrays.stream(expression.replaceAll("[^0-9()+/*-]", "").split("((?=-|\\+|/|\\*)|(?<=-|\\+|/|\\*))")).toList();
    }

    private static List<String> calculateParantheses(List<String> cleanedStringArray){
        List<String> simplifiedWithCalculatedParantheses = new ArrayList<>();

        String[] temp = new String[3];
        boolean parantheses = false;
        for(int i = 0; i < cleanedStringArray.size(); i++) {
            if(cleanedStringArray.get(i).contains("(")) {
                parantheses = true;
                String operand1 = cleanedStringArray.get(i).substring(1);
                temp[0] = operand1;
            } else if (cleanedStringArray.get(i).contains(")")) {
                String operand2 = cleanedStringArray.get(i).substring(0,cleanedStringArray.get(i).length() - 1);
                temp[2] = operand2;
                simplifiedWithCalculatedParantheses.add(String.valueOf(calculateExpression(Arrays.stream(temp).toList())));
                temp = new String[3];
                parantheses = false;
            } else {
                temp[1] = cleanedStringArray.get(i);
            }
            if(!parantheses && !cleanedStringArray.get(i).contains(")")) {
                simplifiedWithCalculatedParantheses.add(cleanedStringArray.get(i));
            }
        }
        return simplifiedWithCalculatedParantheses;
    }

    private static List<String> calculatePriorityOperation(List<String> simplifiedAfterParenthesisCalculated, String operationToConduct){
        List<String> simplifiedExpression = simplifiedAfterParenthesisCalculated;
        List<String> tempList = new ArrayList<>();

        while(simplifiedExpression.contains(operationToConduct)) {
            int indexOfOperation = simplifiedExpression.indexOf(operationToConduct);
            int indexOfFirstOperand = indexOfOperation - 1;
            int indexOfSecondOperand = indexOfOperation + 1;

            String operation = simplifiedExpression.get(indexOfOperation);
            String firstOperand = simplifiedExpression.get(indexOfFirstOperand);
            String secondOperand = simplifiedExpression.get(indexOfSecondOperand);

            tempList.add(firstOperand);
            tempList.add(operation);
            tempList.add(secondOperand);

            String result = String.valueOf(calculateExpression(tempList));

            simplifiedExpression.remove(indexOfSecondOperand);
            simplifiedExpression.remove(indexOfOperation);
            simplifiedExpression.set(indexOfFirstOperand, result);

            tempList.clear();
        }
        return simplifiedExpression;
    }

    private static int calculateExpression(List<String> expression){
        int expresionFinalResult = -1;
        String lastOperand = "";
        int tempNumber;
        for(int i = 0; i <  expression.size(); i++) {
            boolean isNummeric = expression.get(i).chars().allMatch(Character::isDigit);
            if(isNummeric) {
                tempNumber = Integer.valueOf(expression.get(i));
                if(expresionFinalResult == -1) {
                    expresionFinalResult = tempNumber;
                } else {
                    switch(lastOperand) {
                        case "+" -> expresionFinalResult = expresionFinalResult + tempNumber;
                        case "-" -> expresionFinalResult = expresionFinalResult - tempNumber;
                        case "*" -> expresionFinalResult = expresionFinalResult * tempNumber;
                        case "/" -> expresionFinalResult = expresionFinalResult / tempNumber;
                        default -> {
                            return 0;
                        }
                    }
                }
            } else {
                lastOperand = expression.get(i);
            }
        }
        return expresionFinalResult;
    }
}
