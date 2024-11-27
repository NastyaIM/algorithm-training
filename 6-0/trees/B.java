package trees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        Map<String, Person> pairs = new TreeMap<>();

        for (int i = 0; i < n - 1; i++) {
            String[] pair = reader.readLine().split(" ");
            String child = pair[0];
            String parent = pair[1];
            if (!pairs.containsKey(child)) {
                pairs.put(child, new Person(child, parent));
            } else {
                Person person = pairs.get(child);
                person.setParent(parent);
                pairs.put(child, person);
            }
            if (pairs.containsKey(parent)) {
                Person person = pairs.get(parent);
                person.addChild(pairs.get(child));
                pairs.put(parent, person);
            } else {
                Person person = new Person(parent, null);
                person.addChild(pairs.get(child));
                pairs.put(parent, person);
            }

        }

        Person root = null;

        for (Person person : pairs.values()) {
            if (person.getParent() == null) {
                root = person;
            }
        }

        List<List<Person>> levels = new ArrayList<>();
        levels.add(List.of(root));
        List<Person> lastLevel = new ArrayList<>();
        lastLevel.add(root);

        while (true) {
            List<Person> currentLevel = new ArrayList<>();
            for (Person person : lastLevel) {
                currentLevel.addAll(person.getChildren());
            }
            if (currentLevel.isEmpty()) {
                break;
            }
            lastLevel.clear();
            lastLevel.addAll(currentLevel);
            levels.add(currentLevel);
        }

        for (int i = levels.size() - 2; i >= 0; i--) {
            for (Person person : levels.get(i)) {
                for (Person child : person.getChildren()) {
                    person.setDescendantsCount(person.getDescendantsCount() + child.getDescendantsCount());
                }
            }
        }

        for (Map.Entry<String, Person> entry : pairs.entrySet()) {
            writer.write(entry.getKey() + " " + entry.getValue().getDescendantsCount() + "\n");
        }

        writer.close();
        reader.close();
    }

    private static class Person {
        private String name;
        private String parent;
        private List<Person> children;
        private Integer descendantsCount;

        Person(String name, String parent) {
            this.name = name;
            this.children = new ArrayList<>();
            this.parent = parent;
            this.descendantsCount = 0;
        }

        void addChild(Person child) {
            children.add(child);
            descendantsCount++;
        }

        Integer getDescendantsCount() {
            return descendantsCount;
        }

        void setDescendantsCount(Integer count) {
            this.descendantsCount = count;
        }

        void setParent(String parent) {
            this.parent = parent;
        }

        String getParent() {
            return parent;
        }

        List<Person> getChildren() {
            return children;
        }
    }
}