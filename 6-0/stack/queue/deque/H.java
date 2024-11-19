package stack.queue.deque;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Stack<Integer> stack = new Stack<>();
        List<Long> sum = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = reader.readLine();
            if (str.startsWith("+")) {
                stack.push(Integer.parseInt(str.substring(1)));
                if (!sum.isEmpty()) {
                    sum.add(stack.peek() + sum.get(sum.size() - 1));
                } else {
                    sum.add((long)stack.peek());
                }
            } else if (str.equals("-")) {
                writer.write(stack.pop() + "\n");
                sum.remove(sum.size() - 1);
            } else {
                int count = Integer.parseInt(str.substring(1));
                long a = sum.get(sum.size() - 1);
                long b = sum.size() - count - 1 >= 0 ? sum.get(sum.size() - count - 1) : 0;
                writer.write(a - b + "\n");
            }
        }

        writer.close();
        reader.close();
    }
}