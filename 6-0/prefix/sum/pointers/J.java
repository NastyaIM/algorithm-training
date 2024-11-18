package prefix.sum.pointers;

import java.io.*;

public class J {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String[] iEvidence = reader.readLine().split(" ");
        String[] mk = reader.readLine().split(" ");
        int m = Integer.parseInt(mk[0]);
        int k = Integer.parseInt(mk[1]);
        String[] iExperiments = reader.readLine().split(" ");

        int[] evidence = new int[n];
        for (int i = 0; i < n; i++) {
            evidence[i] = Integer.parseInt(iEvidence[i]);
        }

        int[] kk = new int[n];
        kk[0] = 0;
        for (int i = 1; i < n; i++) {
            if (evidence[i] <= evidence[i - 1]) {
                kk[i] = kk[i - 1] + 1;
            } else {
                kk[i] = kk[i - 1];
            }
        }

        int[] koeffs = new int[n];
        koeffs[0] = 0;
        for (int i = 1; i < n; i++) {
            int currentIndex = i;
            if (evidence[currentIndex] >= evidence[currentIndex - 1]) {
                currentIndex = koeffs[i - 1];
                while (kk[i] - kk[currentIndex] > k) {
                    currentIndex++;
                }
            }
            koeffs[i] = currentIndex;
        }


        for (int i = 0; i < m; i++) {
            int exp = Integer.parseInt(iExperiments[i]) - 1;
            writer.write(koeffs[exp] + 1 + " ");
        }

        writer.close();
        reader.close();
    }
}