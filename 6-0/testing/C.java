package testing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C {
    public static final String[][] letterI = {{"#"}};
    public static final String[][] letterO = {{"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}};
    public static final String[][] letterC = {{"#", "#"}, {"#", "."}, {"#", "#"}};
    public static final String[][] letterL = {{"#", "."}, {"#", "#"}};
    public static final String[][] letterH = {{"#", ".", "#"}, {"#", "#", "#"}, {"#", ".", "#"}};
    public static final String[][] letterP = {{"#", "#", "#"}, {"#", ".", "#"}, {"#", "#", "#"}, {"#", ".", "."}};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(reader.readLine());

        String[][] mtx = new String[n][n];

        for (int i = 0; i < n; i++) {
            String[] row = reader.readLine().split("");
            System.arraycopy(row, 0, mtx[i], 0, n);
        }

        List<String[]> mtxWithoutGarbage = removeUnnecessaryColumnsAndRows(n, mtx);
        writer.write(findWhatLetterItIs(mtxWithoutGarbage));

        reader.close();
        writer.close();
    }

    private static String findWhatLetterItIs(List<String[]> mtx) {
        if (mtx.isEmpty()) {
            return "X";
        }

        int rowsCount = mtx.size();
        int columnsCount = mtx.get(0).length;

        if (rowsCount == letterI.length && columnsCount == letterI[0].length) {
            if (areEqual(mtx, letterI)) {
                return "I";
            }
        }
        if (rowsCount == letterO.length && columnsCount == letterO[0].length) {
            if (areEqual(mtx, letterO)) {
                return "O";
            }
        }
        if (rowsCount == letterC.length && columnsCount == letterC[0].length) {
            if (areEqual(mtx, letterC)) {
                return "C";
            }
        }
        if (rowsCount == letterL.length && columnsCount == letterL[0].length) {
            if (areEqual(mtx, letterL)) {
                return "L";
            }
        }
        if (rowsCount == letterH.length && columnsCount == letterH[0].length) {
            if (areEqual(mtx, letterH)) {
                return "H";
            }
        }
        if (rowsCount == letterP.length && columnsCount == letterP[0].length) {
            if (areEqual(mtx, letterP)) {
                return "P";
            }
        }
        return "X";
    }

    private static boolean areEqual(List<String[]> matrixOne, String[][] matrixTwo) {
        for (int i = 0; i < matrixOne.size(); i++) {
            for (int j = 0; j < matrixOne.get(0).length; j++) {
                if (!matrixOne.get(i)[j].equals(matrixTwo[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<String[]> removeUnnecessaryColumnsAndRows(int n, String[][] mtx) {
        List<String[]> mtxWithoutSomeRows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean isOnlyElement = Arrays.stream(mtx[i]).allMatch(s -> s.equals("."));
            if ((i == 0 || !Arrays.equals(mtx[i], mtx[i - 1])) && !isOnlyElement) {
                mtxWithoutSomeRows.add(mtx[i]);
            }
        }
        List<Integer> numbersColumnsToRemove = findColumnsToRemove(n, mtxWithoutSomeRows);

        List<String[]> mtxWithoutGarbage = new ArrayList<>();

        for (String[] row : mtxWithoutSomeRows) {
            List<String> newRow = new ArrayList<>();
            for (int j = 0; j < row.length; j++) {
                if (!numbersColumnsToRemove.contains(j)) {
                    newRow.add(row[j]);
                }
            }
            mtxWithoutGarbage.add(newRow.toArray(new String[0]));
        }
        return mtxWithoutGarbage;
    }

    private static List<Integer> findColumnsToRemove(int n, List<String[]> mtxWithoutSomeRows) {
        List<Integer> numbersColumnsToRemove = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            boolean isUnique = true;
            boolean isDots = true;
            for (String[] row : mtxWithoutSomeRows) {
                String val = row[j];
                if (val.equals("#")) {
                    isDots = false;
                }
                if (j == 0 || !val.equals(row[j - 1])) {
                    isUnique = false;
                }
            }
            if (isDots || isUnique) {
                numbersColumnsToRemove.add(j);
            }
        }
        return numbersColumnsToRemove;
    }
}
