package prefix.sum.pointers;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        String[] nums = reader.readLine().split(" ");
        int lastSum = 0;

        for (String num : nums) {
            int currentNum = Integer.parseInt(num);
            lastSum += currentNum;
            writer.write(lastSum + " ");
        }

        reader.close();
        writer.close();
    }
}