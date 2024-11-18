package prefix.sum.pointers;

import java.io.*;

public class G {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nc = reader.readLine().split(" ");
        int n = Integer.parseInt(nc[0]);
        long c = Long.parseLong(nc[1]);
        char[] input = reader.readLine().toCharArray();

        int maxStrLength = findMaxStrLength(n, input, c);

        writer.write(Integer.toString(maxStrLength));

        writer.close();
        reader.close();
    }

    private static int findMaxStrLength(int n, char[] input, long c) {
        long currentC = 0;
        int aCount = 0;
        int bCount = 0;
        int maxStrLength = 0;
        int l = 0;
        for (int r = 0; r < n; r++) {
            if (input[r] == 'a') {
                aCount++;
            }
            if (input[r] == 'b') {
                bCount++;
                currentC += aCount;
            }
            while (currentC > c) {
                if (input[l] == 'a') {
                    aCount--;
                    currentC -= bCount;
                }
                if (input[l] == 'b') {
                    bCount--;
                }
                l++;
            }
            maxStrLength = Math.max(maxStrLength, r - l + 1);
        }
        return maxStrLength;
    }
}
