package stack.queue.deque;

import java.io.*;
import java.util.Stack;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] str = reader.readLine().trim().toCharArray();

        String postfix = doPostfix(str);
        String[] chars = postfix.split(" ");
        String result;

        try {
            result = evalPostfix(chars);
        } catch (Exception e) {
            result = "WRONG";
        }

        writer.write(result);
        writer.close();
        reader.close();
    }

    private static String doPostfix(char[] str) {
        Stack<Character> stackForSigns = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if (Character.isDigit(str[i])) {
                while (i != str.length && Character.isDigit(str[i])) {
                    sb.append(str[i++]);
                }
                i--;
                sb.append(" ");
            } else if (str[i] == '(') {
                stackForSigns.push(str[i]);
            } else if (str[i] == ')') {
                while (!stackForSigns.isEmpty() && stackForSigns.peek() != '(') {
                    sb.append(stackForSigns.pop()).append(" ");
                }
                if (!stackForSigns.isEmpty()) {
                    stackForSigns.pop();
                } else {
                    return "WRONG";
                }
            } else if (str[i] == '+' || str[i] == '-' || str[i] == '*') {
                if (i == 0 || (i > 1 &&
                        (str[i - 1] == '+' || str[i - 1] == '-' || str[i - 1] == '*' || str[i - 1] == '('))) {
                    return "WRONG";
                } else if (str[i] == '+' || str[i] == '-') {
                    while (!stackForSigns.isEmpty() && stackForSigns.peek() != '(') {
                        sb.append(stackForSigns.pop()).append(" ");
                    }
                    stackForSigns.push(str[i]);
                } else {
                    if (!stackForSigns.isEmpty() && stackForSigns.peek() == str[i]) {
                        sb.append(stackForSigns.pop()).append(" ");
                    }
                    stackForSigns.push(str[i]);
                }
            } else if (str[i] == ' ') {
                if (i != 0 && i != str.length - 1 && Character.isDigit(str[i - 1]) && Character.isDigit(str[i + 1])) {
                    return "WRONG";
                }
            } else {
                return "WRONG";
            }
        }
        while (!stackForSigns.isEmpty()) {
            sb.append(stackForSigns.pop()).append(" ");
        }
        return sb.toString();
    }

    private static String evalPostfix(String[] postfix) {
        Stack<Integer> stack = new Stack<>();
        for (String s : postfix) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    stack.push(stack.pop() - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        return stack.isEmpty() ? "" : stack.pop().toString();
    }
}