package trees.D;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class D {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("6-0/trees/D/input.txt"))){
            String line = reader.readLine();
            while (line != null) {
                allLines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tree tree = new Tree();

        for (String allLine : allLines) {
            String[] com = allLine.split(" ");
            if (com[0].equals("ADD")) {
                writer.write(tree.insert(Integer.parseInt(com[1])) + "\n");
            } else if (com[0].equals("SEARCH")) {
                writer.write(tree.find(Integer.parseInt(com[1])) + "\n");
            } else {
                writer.write(tree.printTree());
            }
        }

        writer.close();
        //reader.close();
    }

    static class Node {
        int val;
        Node leftChild;
        Node rightChild;

        Node(int val, Node leftChild, Node rightChild) {
            this.val = val;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    static class Tree {
        Node root;

        public String find(int key) {
            if (root == null) {
                return "NO";
            }
            Node current = root;
            while (current.val != key) {
                if (key < current.val) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
                if (current == null) {
                    return "NO";
                }
            }
            return "YES";
        }

        public String insert(int val) {
            Node newNode = new Node(val, null, null);
            if (root == null) {
                root = newNode;
                return "DONE";
            } else {
                Node current = root;
                Node parent;
                while (true) {
                    parent = current;
                    if (val < current.val) {
                        current = current.leftChild;
                        if (current == null) {
                            parent.leftChild = newNode;
                            return "DONE";
                        }
                    } else if (val > current.val) {
                        current = current.rightChild;
                        if (current == null) {
                            parent.rightChild = newNode;
                            return "DONE";
                        }
                    } else {
                        return "ALREADY";
                    }
                }
            }
        }

        public String printTree() {
            StringBuilder result = new StringBuilder();
            preOrder(root, 0, result);
            return result.toString();
        }

        private void preOrder(Node localRoot, int level, StringBuilder result) {
            if (localRoot == null) {
                return;
            }

            preOrder(localRoot.leftChild, level + 1, result);
            result.append(".".repeat(level)).append(localRoot.val).append("\n");
            preOrder(localRoot.rightChild, level + 1, result);

        }
    }
}