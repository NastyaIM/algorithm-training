package prefix.sum.pointers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class B {
    static final Map<Integer, Integer> sumCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String[] numbers = reader.readLine().split(" ");

        int carsCount = findCarsCount(getPrefixSumArr(n, numbers), k);

        writer.write(Integer.toString(carsCount));

        reader.close();
        writer.close();
    }

    private static int findCarsCount(int[] prefixSum, int k) {
        int count = 0;

        for (int pi : prefixSum) {
            Integer sumC = sumCount.get(pi - k);
            if (sumC != null) {
                count += sumC;
            }
            if (!sumCount.containsKey(pi)) {
                sumCount.put(pi, 1);
            } else {
                sumCount.put(pi, sumCount.get(pi) + 1);
            }
        }
        return count;
    }

    private static int[] getPrefixSumArr(int n, String[] numbers) {
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < prefixSum.length; i++) {
            int currentNum = Integer.parseInt(numbers[i - 1]);
            prefixSum[i] = prefixSum[i - 1] + currentNum;
        }
        return prefixSum;
    }
}