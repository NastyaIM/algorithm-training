package stack.queue.deque;

import java.io.*;
import java.util.Stack;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        String[] cities = reader.readLine().split(" ");
        int[] citiesInt = new int[n];
        for (int i = 0; i < n; i++) {
            citiesInt[i] = Integer.parseInt(cities[i]);
        }

        int[] minimums = findNearestMinimum(n, citiesInt);
        for (Integer min : minimums) {
            writer.write(min + " ");
        }

        writer.close();
        reader.close();
    }

    private static int[] findNearestMinimum(int n, int[] citiesInt) {
        Stack<Integer> minCitiesSt = new Stack<>();
        int[] minimums = new int[n];

        for (int i = 0; i < n; i++) {
            minimums[i] = -1;
            while (!minCitiesSt.isEmpty() && citiesInt[minCitiesSt.peek()] > citiesInt[i]) {
                minimums[minCitiesSt.pop()] = i;
            }
            minCitiesSt.push(i);
        }
        return minimums;
    }
}