package prefix.sum.pointers;

import java.io.*;

public class H {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        int[] nums = new int[n];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        long[] suffixSum = new long[n + 1];
        suffixSum[n] = 0;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) i * nums[i];
        }
        long minSum = sum;
        for (int i = 1; i < n; i++) {
            sum = sum - nums[i] + prefixSum[i] - suffixSum[i + 1];
            minSum = Math.min(minSum, sum);
        }

        writer.write(Long.toString(minSum));

        writer.close();
        reader.close();
    }
}