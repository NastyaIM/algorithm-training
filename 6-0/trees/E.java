package trees;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        Map<Integer, List<Integer>> input = new TreeMap<>();
        for (int i = 0; i < n - 1; i++) {
            String[] line = reader.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            if (!input.containsKey(a)) {
                input.put(a, new ArrayList<>());
            }
            input.get(a).add(b);
        }

        Map<Integer, Node> pairs = new TreeMap<>();

        for (Integer a : input.keySet()) {

            List<Integer> children = new ArrayList<>();
            if (input.containsKey(a)) {
                children = input.get(a);
            }

            for (Integer b : children) {
                if (!pairs.containsKey(b)) {
                    pairs.put(b, new Node(b, a));
                } else {
                    Node node = pairs.get(b);
                    node.setParent(a);
                    pairs.put(b, node);
                }
                if (pairs.containsKey(a)) {
                    Node node = pairs.get(a);
                    node.addChild(pairs.get(b));
                    pairs.put(a, node);
                } else {
                    Node node = new Node(a, null);
                    node.addChild(pairs.get(b));
                    pairs.put(a, node);
                }
            }
        }

        Node root = null;

        for (Map.Entry<Integer, Node> a : pairs.entrySet()) {
            if (a.getKey() == 1) {
                root = a.getValue();
            }
        }

        List<List<Node>> levels = new ArrayList<>();
        levels.add(List.of(root));
        List<Node> lastLevel = new ArrayList<>();
        lastLevel.add(root);

        while (true) {
            List<Node> currentLevel = new ArrayList<>();
            for (Node node : lastLevel) {
                if (node != null && node.children != null)
                    currentLevel.addAll(node.children);
            }
            if (currentLevel.isEmpty()) {
                break;
            }
            lastLevel.clear();
            lastLevel.addAll(currentLevel);
            levels.add(currentLevel);
        }
        System.out.println();

        for (int i = levels.size() - 2; i >= 0; i--) {
            for (Node node : levels.get(i)) {
                if (node.children != null) {
                    for (Node child : node.children) {
                        if (child != null) {
                            node.setDescendantsCount(node.getDescendantsCount() + child.getDescendantsCount());
                        } else {
                            node.setDescendantsCount(1);
                        }
                    }
                } else {
                    node.setDescendantsCount(1);
                }
            }
        }

        for (Map.Entry<Integer, Node> entry : pairs.entrySet()) {
            writer.write(entry.getValue().getDescendantsCount() + 1 + " ");
        }

        writer.close();
        reader.close();
    }

    private static class Node {
        private Integer val;
        private Integer parent;
        private List<Node> children;
        private Integer descendantsCount;

        Node(Integer val, Integer parent) {
            this.val = val;
            this.children = new ArrayList<>();
            this.parent = parent;
            this.descendantsCount = 0;
        }

        void addChild(Node child) {
            children.add(child);
            descendantsCount++;
        }

        Integer getDescendantsCount() {
            return descendantsCount;
        }

        void setDescendantsCount(Integer count) {
            this.descendantsCount = count;
        }

        void setParent(Integer parent) {
            this.parent = parent;
        }

        Integer getParent() {
            return parent;
        }

        List<Node> getChildren() {
            return children;
        }
    }
}