package stack.queue.deque;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nb = reader.readLine().split(" ");
        int n = Integer.parseInt(nb[0]);
        int b = Integer.parseInt(nb[1]);
        String[] clients = reader.readLine().split(" ");

        Queue<int[]> queue = new LinkedList<>();
        int currentTime = 1;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int c = Integer.parseInt(clients[i]);
            queue.add(new int[]{c, i + 1});
            int servedCount = 0;

            while (!queue.isEmpty() && servedCount < b) {
                int[] clientsCount = queue.poll();
                int toServe = Math.min(clientsCount[0], b - servedCount);
                servedCount += toServe;
                sum += (long) (currentTime + 1 - clientsCount[1]) * toServe;
                if (clientsCount[0] > toServe) {
                    queue.add(new int[]{clientsCount[0] - toServe, clientsCount[1]});
                }
            }
            currentTime++;
        }

        while (!queue.isEmpty()) {
            int[] clientsCount = queue.poll();

            sum += (long) (currentTime + 1 - clientsCount[1]) * clientsCount[0];
        }

        writer.write(Long.toString(sum));

        writer.close();
        reader.close();
    }
}