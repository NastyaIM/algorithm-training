package stack.queue.deque;

import java.io.*;
import java.util.*;

public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nh = reader.readLine().split(" ");
        int n = Integer.parseInt(nh[0]);
        int h = Integer.parseInt(nh[1]);

        String[] inputHeights = reader.readLine().split(" ");
        String[] inputWidths = reader.readLine().split(" ");

        List<Chair> chairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(inputHeights[i]);
            int width = Integer.parseInt(inputWidths[i]);
            chairs.add(new Chair(height, width));
        }

        chairs.sort(Comparator.comparingInt(Chair::height)
                .thenComparingInt(Chair::width));

        int minDiff = getMinDiff(chairs, n, h);
        writer.write(minDiff == Integer.MAX_VALUE ? 0 : minDiff);

        writer.close();
        reader.close();
    }

    private static int getMinDiff(List<Chair> chairs, int n, int h) {
        int currentWidthSum = chairs.get(0).width();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        int minDiff = Integer.MAX_VALUE;


        int j = 0;
        for (int i = 1; i < n; ) {
            while (i < n && currentWidthSum < h) {
                currentWidthSum += chairs.get(i).width();
                int currentDiff = Math.abs(chairs.get(i).height() -
                        chairs.get(i - 1).height());
                while (!deque.isEmpty() && deque.getLast() < currentDiff) {
                    deque.removeLast();
                }
                deque.addLast(currentDiff);
                i++;
            }
            minDiff = Math.min(minDiff, deque.getFirst());
            while (currentWidthSum >= h && j + 1 < n) {
                currentWidthSum -= chairs.get(j).width();

                int diff = Math.abs(chairs.get(j + 1).height() - chairs.get(j).height());
                if (!deque.isEmpty() && deque.getFirst() == diff) {
                    deque.removeFirst();
                }
                if (currentWidthSum == h) {
                    if (!deque.isEmpty()) {
                        minDiff = Math.min(minDiff, deque.getFirst());
                    } else {
                        minDiff = 0;
                    }
                }
                j++;
            }

        }
        return minDiff;
    }

    record Chair(int height, int width) {
    }
}