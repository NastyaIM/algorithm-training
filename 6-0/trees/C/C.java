package trees.C;

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("6-0/trees/C/input.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                allLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = Integer.parseInt(allLines.get(0));

        Map<String, Person> pairs = new TreeMap<>();

        for (int i = 1; i < n; i++) {
            String[] pair = allLines.get(i).split(" ");
            String child = pair[0];
            String parent = pair[1];
            if (!pairs.containsKey(child)) {
                pairs.put(child, new Person(child, parent));
            } else {
                Person person = pairs.get(child);
                person.parent = parent;
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
            if (person.parent == null) {
                root = person;
            }
        }

        TarjanLCA lca = new TarjanLCA(root);

        for (int i = n; i < allLines.size(); i++) {
            String[] uv = allLines.get(i).split(" ");
            Person u = pairs.get(uv[0]);
            Person v = pairs.get(uv[1]);
            lca.addQuery(u, v);
        }

        for (String ans : lca.processQueries()) {
            writer.write(ans + "\n");
        }

        writer.close();
        //reader.close();
    }

    static class Person {
        String name;
        String parent;
        List<Person> children;

        Person(String name, String parent) {
            this.name = name;
            this.parent = parent;
            children = new ArrayList<>();
        }

        void addChild(Person child) {
            children.add(child);
        }
    }

    static class TarjanLCA {
        private Map<Person, Person> parent;
        private Map<Person, Integer> depth;
        private Map<Person, Person> ancestor;
        private List<List<Person>> queries;
        private Set<Person> visited;

        public TarjanLCA(Person root) {
            parent = new HashMap<>();
            depth = new HashMap<>();
            ancestor = new HashMap<>();
            queries = new ArrayList<>();
            visited = new HashSet<>();
            dfs(root, null, 0);
        }

        private void dfs(Person node, Person p, int d) {
            parent.put(node, p);
            depth.put(node, d);
            ancestor.put(node, node);
            visited.add(node);

            for (Person child : node.children) {
                dfs(child, node, d + 1);
                union(node, child);
            }
        }

        private void union(Person u, Person v) {
            if (visited.contains(v)) {
                ancestor.put(v, u);
            }
            ancestor.put(u, ancestor.get(v));
        }

        public void addQuery(Person u, Person v) {
            queries.add(new ArrayList<>() {{
                add(u);
                add(v);
            }});
        }

        public List<String> processQueries() {
            List<String> answer = new ArrayList<>();
            for (List<Person> query : queries) {
                if (visited.contains(query.get(0))) {
                    answer.add(findLCA(query.get(0), query.get(1)).name);
                }
            }
            return answer;
        }


        private Person findLCA(Person u, Person v) {
            while (u != v) {
                if (depth.get(u) > depth.get(v)) {
                    u = ancestor.get(u);
                } else {
                    v = ancestor.get(v);
                }
            }
            return u;
        }
    }
}