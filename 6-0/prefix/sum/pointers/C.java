package prefix.sum.pointers;

import java.io.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int r = Integer.parseInt(nk[1]);

        int[] positions = new int[n];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.parseInt(input[i]);
        }

        long count = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            while (j < n && positions[j] - positions[i] <= r) {
                j++;
            }
            count += (n - j);
        }

        writer.write(Long.toString(count));

        writer.close();
        reader.close();
    }
}