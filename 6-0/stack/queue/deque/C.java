package stack.queue.deque;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        String[] nums = reader.readLine().split(" ");
        int[] numsI = new int[n];
        for (int i = 0; i < n; i++) {
            numsI[i] = Integer.parseInt(nums[i]);
        }
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (i >= k && deque.getFirst() == numsI[i - k]) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && numsI[i] < deque.getLast()) {
                deque.removeLast();
            }
            deque.addLast(numsI[i]);
            if (i >= k - 1) {
                writer.write(deque.getFirst() + "\n");
            }
        }

        writer.close();
        reader.close();
    }
}