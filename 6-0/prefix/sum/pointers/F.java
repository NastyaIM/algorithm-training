package prefix.sum.pointers;

import java.io.*;
import java.util.Arrays;
public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        int[] nums = new int[n];
        String[] input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(nums);

        int[] suffixSum = new int[n + 1];
        suffixSum[n] = 0;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        int mod = 1000000007;

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = (suffixSum[i + 1] + nums[i]) % mod;
        }

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = (prefixSum[i - 1] + nums[i - 1]) % mod;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum = (int)(sum + ((((long) nums[i] * suffixSum[i + 1]) % mod) * prefixSum[i]) % mod) % mod;
        }

        writer.write(Integer.toString(sum));

        writer.close();
        reader.close();
    }
}