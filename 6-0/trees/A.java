package trees;

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Map<String, List<String>> pairs = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            String[] pair = reader.readLine().split(" ");
            String child = pair[0];
            String parent = pair[1];
            if (pairs.containsKey(parent)) {
                pairs.get(parent).add(child);
            } else {
                pairs.put(parent, new ArrayList<>() {{
                    add(child);
                }});
            }
        }

        String root = null;
        Person parent = null;

        for (String parentName : pairs.keySet()) {
            boolean flag = false;
            for (List<String> person : pairs.values()) {
                if (person.contains(parentName)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                root = parentName;
                break;
            }
        }


        Map<String, Integer> people = new TreeMap<>();
        people.put(root, 0);
        int level = 1;
        List<String> currentLevel = new ArrayList<>();
        List<String> lastLevel = new ArrayList<>();
        lastLevel.add(root);

        while (!lastLevel.isEmpty()) {
            for (String person : lastLevel) {
                if (pairs.get(person) != null) {
                    currentLevel.addAll(pairs.get(person));
                }
            }
            lastLevel.clear();
            for (String current : currentLevel) {
                people.put(current, level);
                lastLevel.add(current);
            }
            level++;
            currentLevel.clear();
        }


        for (Map.Entry<String, Integer> entry : people.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


        writer.close();
        reader.close();
    }

    private static class Person {
        String name;
        Person parent;

        Person(String name, Person parent) {
            this.name = name;
            this.parent = parent;
        }
    }
}