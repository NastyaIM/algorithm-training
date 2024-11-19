package stack.queue.deque;

import java.io.*;
import java.util.*;

public class I {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String[] ab = reader.readLine().split(" ");
        int a = Math.min(Integer.parseInt(ab[0]), Integer.parseInt(ab[1]));
        int b = Math.max(Integer.parseInt(ab[0]), Integer.parseInt(ab[1]));

        List<Rover> rovers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] pair = reader.readLine().split(" ");
            rovers.add(new Rover(i, Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
        }
        rovers.sort(Comparator.comparingInt(pair -> pair.time));

        Queue<Rover> one = new LinkedList<>();
        Queue<Rover> two = new LinkedList<>();
        Queue<Rover> three = new LinkedList<>();
        Queue<Rover> four = new LinkedList<>();

        for (Rover rover : rovers) {
            if (rover.roadNumber == 1) {
                one.add(rover);
            }
            if (rover.roadNumber == 2) {
                two.add(rover);
            }
            if (rover.roadNumber == 3) {
                three.add(rover);
            }
            if (rover.roadNumber == 4) {
                four.add(rover);
            }
        }

        int currentTime = 1;
        int[] answer = new int[rovers.size()];
        while (!one.isEmpty() || !two.isEmpty() || !three.isEmpty() || !four.isEmpty()) {

            List<Rover> tmp = new ArrayList<>();
            if (!one.isEmpty()) tmp.add(one.peek());
            if (!two.isEmpty()) tmp.add(two.peek());
            if (!three.isEmpty()) tmp.add(three.peek());
            if (!four.isEmpty()) tmp.add(four.peek());

            CurrentState currentState = new CurrentState(tmp, a, b, currentTime);
            List<Rover> roversToMove = currentState.getFirst();
            for (Rover rover : roversToMove) {
                answer[rover.id] = currentTime;
                if (!one.isEmpty() && one.peek().id == rover.id) {
                    one.poll();
                }
                if (!two.isEmpty() && two.peek().id == rover.id) {
                    two.poll();
                }
                if (!three.isEmpty() && three.peek().id == rover.id) {
                    three.poll();
                }
                if (!four.isEmpty() && four.peek().id == rover.id) {
                    four.poll();
                }
            }
            currentTime++;

        }

        for (int time : answer) {
            writer.write(time + "\n");
        }

        writer.close();
        reader.close();
    }

    static class CurrentState {
        Rover main1;
        Rover main2;
        Rover secondary1;
        Rover secondary2;
        int a;
        int b;

        public CurrentState(List<Rover> rovers, int a, int b, int currentTime) {
            this.a = a;
            this.b = b;
            for (Rover rover : rovers) {
                if (rover.time <= currentTime) {
                    if (rover.roadNumber == a) {
                        main1 = rover;
                    } else if (rover.roadNumber == b) {
                        main2 = rover;
                    } else {
                        if (secondary1 == null) {
                            secondary1 = rover;
                        } else {
                            secondary2 = rover;
                        }
                    }
                }
            }
        }

        public List<Rover> getFirst() {
            List<Rover> rovers = new ArrayList<>();
            if (Math.abs(b - a) == 2) {
                if (main1 != null) rovers.add(main1);
                if (main2 != null) rovers.add(main2);
                if (rovers.isEmpty()) {
                    if (secondary1 != null) rovers.add(secondary1);
                    if (secondary2 != null) rovers.add(secondary2);
                }
            } else {
                Rover rover;
                if (main1 != null || main2 != null) {
                    rover = choiceRoverEquivalentRoad(main1, main2);
                } else {
                    rover = choiceRoverEquivalentRoad(secondary1, secondary2);
                }
                if (rover != null) {
                    rovers.add(rover);
                }
            }
            return rovers;
        }

        private Rover choiceRoverEquivalentRoad(Rover one, Rover two) {
            if (two == null) {
                return one;
            } else if (one == null) {
                return two;
            } else {
                if (two.roadNumber - one.roadNumber == 1) {
                    return one;
                } else {
                    return two;
                }
            }
        }
    }

    static class Rover {
        int id;
        int roadNumber;
        int time;

        public Rover(int id, int roadNumber, int time) {
            this.id = id;
            this.roadNumber = roadNumber;
            this.time = time;
        }
    }
}