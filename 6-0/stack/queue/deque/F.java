package stack.queue.deque;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        char[] order = reader.readLine().toCharArray();
        char[] start = reader.readLine().toCharArray();

        Map<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put(']', '[');

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char c : start) {
            if (!stack.isEmpty() && stack.peek() == brackets.get(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
            result.append(c);
        }

        for (int i = start.length; i < n - stack.size(); i++) {
            Character bracket = stack.isEmpty() ? null : stack.peek();
            for (char c : order) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                    result.append(c);
                    break;
                }
                if (bracket != null && (bracket == '(' && c == ')' || bracket == '[' && c == ']')) {
                    stack.pop();
                    result.append(c);
                    break;
                }
            }

        }

        while (!stack.isEmpty()) {
            if (stack.pop() == '(') {
                result.append(')');
            } else {
                result.append(']');
            }
        }

        writer.write(result.toString());
        writer.close();
        reader.close();
    }
}