package prefix.sum.pointers;

import java.io.*;
import java.util.Arrays;

public class E {
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

        int j = n / 2;
        int i = n % 2 == 0 ? j - 1 : j;
        int k = 0;

        int[] res = findAllMedians(n, i, j, k, nums);

        for (int num : res) {
            writer.write(num + " ");
        }

        writer.close();
        reader.close();
    }

    private static int[] findAllMedians(int n, int i, int j, int k, int[] nums) {
        int[] res = new int[n];
        while (i != -1 || j != n) {
            if (i == -1) {
                res[k] = nums[j++];
            } else if (j == n) {
                res[k] = nums[i--];
            } else if (i == j) {
                res[k] = nums[i--];
                j++;
            } else if (nums[i] == nums[j]) {
                res[k++] = nums[i--];
                res[k] = nums[j++];
            } else {
                if (i == n - 1 - j) {
                    res[k] = (nums[i] < nums[j]) ? nums[i--] : nums[j++];
                } else if (i < n - 1 - j) {
                    res[k] = nums[j++];
                } else {
                    res[k] = nums[i--];
                }
            }
            k++;
        }
        return res;
    }
}