package stack.queue.deque;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class A {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] brackets = reader.readLine().toCharArray();

        if (checkPSP(brackets)) {
            writer.write("yes");
        } else {
            writer.write("no");
        }

        writer.close();
        reader.close();
    }

    private static boolean checkPSP(char[] brackets) {
        Stack<Character> bracketsSt = new Stack<>();
        Map<Character, Character> bracketsMap = new HashMap<>();
        bracketsMap.put(')', '(');
        bracketsMap.put('}', '{');
        bracketsMap.put(']', '[');

        for (char bracket : brackets) {
            if (bracketsMap.containsKey(bracket)) {
                if (bracketsSt.isEmpty() || bracketsMap.get(bracket) != bracketsSt.pop()) {
                    return false;
                }
            } else {
                bracketsSt.push(bracket);
            }
        }
        return bracketsSt.isEmpty();
    }
}