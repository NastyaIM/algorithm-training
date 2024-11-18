package prefix.sum.pointers;

import java.io.*;
import java.util.Arrays;
public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int r = Integer.parseInt(nk[1]);

        int[] directions = new int[n];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            directions[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(directions);

        long days = findDaysCount(n, r, directions);

        writer.write(Long.toString(days));

        writer.close();
        reader.close();
    }

    static long findDaysCount(int n, int r, int[] directions) {
        long days = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j < n) {
                if (directions[j++] - directions[i] <= r) {
                    days++;
                } else {
                    break;
                }
            }
        }
        return days;
    }
}
