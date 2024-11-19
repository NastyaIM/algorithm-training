package stack.queue.deque;

import java.io.*;
import java.util.Stack;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] postfix = reader.readLine().trim().split(" ");

        writer.write(evalPostfix(postfix));
        writer.close();
        reader.close();
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