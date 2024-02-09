import java.util.*;

public class twoStackAlgebraAlg {
    public static void main(String[] args) {

        // input and remove spaces
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a math expression: ");
        String expression = keyboard.nextLine();
        expression = expression.replaceAll("\\s", "");
        keyboard.close();
        // declare two stack containters

        Stack<Float> valueStack = new Stack<Float>();
        Stack<Character> operatorStack = new Stack<Character>();

        // main algorithm
        // iterate through the expression and check if the character is a digit
        // or an operator or a parenthesis

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // if it is a digit then check if the next character is also a digit or
            // a dot or an operator to end the number

            if (Character.isDigit(c)) {
                String number = "";
                number += c;
                while (i + 1 < expression.length()
                        && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    number += expression.charAt(i + 1);
                    i++;
                }
                valueStack.push(Float.parseFloat(number));

                // if it is an operator then push it to the operator stack

            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                operatorStack.push(c);

                // if it is a parenthesis then push it to the operator stack

            } else if (c == '(') {
                operatorStack.push(c);

                // if it is a closing parenthesis then pop the operator stack until we
                // find the opening parenthesis and calculate the result
                // push the result to the value stack
                // continue until the end of the expression

            } else if (c == ')') {
                while (operatorStack.peek() != '(') {
                    Float value1 = valueStack.pop();
                    Float value2 = valueStack.pop();
                    char operator = operatorStack.pop();
                    Float result = (float) 0;

                    if (operator == '+') {
                        result = value2 + value1;
                    } else if (operator == '-') {
                        result = value2 - value1;
                    } else if (operator == '*') {
                        result = value2 * value1;
                    } else if (operator == '/') {
                        result = value2 / value1;
                    }
                    valueStack.push(result);
                }

                // finally pop the operator stack and calculate the result and push it
                // to the value stack
                // the last value in the value stack will be the result of the expression

                operatorStack.pop();
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        // if there are no parenthesis then pop the operator stack and calculate
        // the result and push it to the value stack

        while (!operatorStack.isEmpty()) {
            Float value1 = valueStack.pop();
            Float value2 = valueStack.pop();
            char operator = operatorStack.pop();
            Float result = (float) 0;

            if (operator == '+') {
                result = value2 + value1;
            } else if (operator == '-') {
                result = value2 - value1;
            } else if (operator == '*') {
                result = value2 * value1;
            } else if (operator == '/') {
                result = value2 / value1;
            }
            valueStack.push(result);
        }

        // output
        System.out.println("The result is: " + valueStack.pop());
    }
}
