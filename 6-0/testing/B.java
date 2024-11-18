package testing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int blueTShCount = Integer.parseInt(reader.readLine());
        int redTShCount = Integer.parseInt(reader.readLine());
        int blueSCount = Integer.parseInt(reader.readLine());
        int redSCount = Integer.parseInt(reader.readLine());

        Pair result = findTShirtsAndSocksCount(blueTShCount, redTShCount, blueSCount, redSCount);
        writer.write(result.tShirt + " " + result.sock);
        reader.close();
        writer.close();
    }

    private static Pair findTShirtsAndSocksCount(int a, int b, int c, int d) {
        int abCount = a + b;
        int cdCount = c + d;
        List<Pair> allPairs = new ArrayList<>();
        if (a + 1 <= abCount && c + 1 <= cdCount) {
            allPairs.add(new Pair(a + 1, c + 1));
        }
        if (b + 1 <= abCount && d + 1 <= cdCount) {
            allPairs.add(new Pair(b + 1, d + 1));
        }
        if (Math.max(a, b) + 1 <= abCount) {
            allPairs.add(new Pair(Math.max(a, b) + 1, 1));
        }
        if (Math.max(c, d) + 1 <= cdCount) {
            allPairs.add(new Pair(1, Math.max(c, d) + 1));
        }

        Pair result = allPairs.get(0);
        for (int i = 1; i < allPairs.size(); i++) {
            if (allPairs.get(i).sum() < result.sum()) {
                result = allPairs.get(i);
            }
        }
        return result;
    }

    private static class Pair {
        int tShirt;
        int sock;

        public Pair(int tShirt, int sock) {
            this.tShirt = tShirt;
            this.sock = sock;
        }

        public int sum() {
            return tShirt + sock;
        }
    }
}