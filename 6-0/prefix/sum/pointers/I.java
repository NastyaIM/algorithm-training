package prefix.sum.pointers;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File("output.txt");
        PrintWriter fw = new PrintWriter(file);

        int n = Integer.parseInt(reader.readLine());
        String[] intensity = reader.readLine().split(" ");
        String[] usefulnes = reader.readLine().split(" ");

        String[] mood = reader.readLine().split(" ");

        Algorithm[] intensities = new Algorithm[n];
        Algorithm[] usefulness = new Algorithm[n];
        int[] moods = new int[n];

        for (int i = 0; i < n; i++) {
            intensities[i] = new Algorithm(i, Integer.parseInt(intensity[i]));
            usefulness[i] = new Algorithm(i, Integer.parseInt(usefulnes[i]));
            moods[i] = Integer.parseInt(mood[i]);
        }

        Algorithm[] copyUs = intensities.clone();

        Arrays.sort(intensities, (a1, a2) -> {
            if (a1.parameter != a2.parameter) {
                return Integer.compare(a2.parameter, a1.parameter);
            }
            if (usefulness[a1.id].parameter != usefulness[a2.id].parameter) {
                return Integer.compare(usefulness[a2.id].parameter, usefulness[a1.id].parameter);
            }
            return Integer.compare(a1.id, a2.id);
        });

        Arrays.sort(usefulness, (a1, a2) -> {
            if (a1.parameter != a2.parameter) {
                return Integer.compare(a2.parameter, a1.parameter);
            }
            if (Integer.parseInt(intensity[a1.id]) != Integer.parseInt(intensity[a2.id])) {
                return Integer.compare(copyUs[a2.id].parameter, copyUs[a1.id].parameter);
            }
            return Integer.compare(a1.id, a2.id);
        });

        Set<Integer> algorithms = new HashSet<>();
        int i = 0;
        int u = 0;

        for (int day = 0; day < n; day++) {
            if (moods[day] == 0) {
                while (i != n) {
                    int tmp = intensities[i++].id + 1;
                    algorithms.add(tmp);
                    if (algorithms.size() == day + 1) {
                        fw.print(tmp + " ");
                        if (usefulness[u].id == tmp - 1) {
                            u++;
                        }
                        break;
                    }
                }

            } else {
                while (u != n) {
                    int tmp = usefulness[u++].id + 1;
                    algorithms.add(tmp);
                    if (algorithms.size() == day + 1) {
                        fw.print(tmp + " ");
                        if (intensities[i].id == tmp - 1) {
                            i++;
                        }
                        break;
                    }
                }
            }
        }

        fw.close();
        reader.close();
    }
}

class Algorithm {
    int id;
    int parameter;

    public Algorithm(int id, int parameter) {
        this.id = id;
        this.parameter = parameter;
    }
}