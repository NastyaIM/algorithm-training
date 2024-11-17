package testing;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int x1 = Integer.parseInt(reader.readLine());
        int y1 = Integer.parseInt(reader.readLine());
        int x2 = Integer.parseInt(reader.readLine());
        int y2 = Integer.parseInt(reader.readLine());
        int x = Integer.parseInt(reader.readLine());
        int y = Integer.parseInt(reader.readLine());

        writer.write(selectAngle(x, y, x1, y1, x2, y2));
        reader.close();
        writer.close();
    }

    private static String selectAngle(int x, int y, int x1, int y1, int x2, int y2) {
        StringBuilder angle = new StringBuilder();
        if (y < y1) {
            angle.append("S");
        }
        if (y > y2) {
            angle.append("N");
        }
        if (x < x1) {
            angle.append("W");
        }
        if (x > x2) {
            angle.append("E");
        }
        return angle.toString();
    }
}