import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day7 {
    static final char maxChar= 'Z';
    static final int solutionLength = maxChar - 64;
    static final int numWorkers = 5;
    static final int timeOffset = 60;
    static String part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./2018inputs/day7.txt"));
        //hashmap of the count of right appearance
        //hashmap: key = letter, value= list(what it is on the right)
        HashMap<Character, Integer> requiredCount = new HashMap<>();
        HashMap<Character, ArrayList<Character>> link = new HashMap<>();
        StringBuilder solution = new StringBuilder();
        //reading in the data
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (requiredCount.putIfAbsent(line.charAt(36), 1) != null) {
                requiredCount.replace(line.charAt(36), 1 + requiredCount.get(line.charAt(36)));
            }
            link.putIfAbsent(line.charAt(5), new ArrayList<>());
            link.get(line.charAt(5)).add(line.charAt(36));
        }
        loop:
        while (solution.length() < 26) {
            for (char i = 'A'; i <= 'Z'; i++) {
                if (!requiredCount.containsKey(i) || 0 == requiredCount.get(i)) {
                    if (requiredCount.putIfAbsent(i, Integer.MAX_VALUE) != null) {
                        requiredCount.replace(i, Integer.MAX_VALUE);
                    }
                    solution.append(i);
                    if (link.containsKey(i)) {
                        for (Character c : link.get(i)) {
                            requiredCount.replace(c, requiredCount.get(c) - 1);
                        }
                    }
                    continue loop;
                }
            }
        }
        return solution.toString();
    }

    static int part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./2018inputs/day7.txt"));
        String[] workers = new String[numWorkers];
        //hashmap of the count of right appearance
        //hashmap: key = letter, value= list(what it is on the right)
        HashMap<Character, Integer> requiredCount = new HashMap<>();
        HashMap<Character, ArrayList<Character>> link = new HashMap<>();
        StringBuilder solution = new StringBuilder();
        //reading in the data
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (requiredCount.putIfAbsent(line.charAt(36), 1) != null) {
                requiredCount.replace(line.charAt(36), 1 + requiredCount.get(line.charAt(36)));
            }
            link.putIfAbsent(line.charAt(5), new ArrayList<>());
            link.get(line.charAt(5)).add(line.charAt(36));
        }
        //System.out.printf("Second\tWorker 1\tWorker 2\tWorker 3\tWorker 4\tWorker 5\tDone\n");
        int time;
        for (time = 0; solution.length() < solutionLength || !workersDone(workers); time++) {
            loop:
            for (int workerIndex = 0; workerIndex < numWorkers; workerIndex++) {
                if (workers[workerIndex] == null) {
                    for (char i = 'A'; i <= maxChar; i++) {
                        if (!requiredCount.containsKey(i) || 0 == requiredCount.get(i)) {
                            if (requiredCount.putIfAbsent(i, Integer.MAX_VALUE) != null) {
                                requiredCount.replace(i, Integer.MAX_VALUE);
                            }
                            //solution.append(i);
                            workers[workerIndex] = i + String.valueOf(i - 64 + timeOffset);
                            continue loop;
                        }
                    }
                }
            }            //System.out.printf("%d\t\t\t%c\t\t\t%c\t\t\t%c\t\t\t%c\t\t\t%c\t\t\t%s\n", time, workers[0] == null ? '.' : workers[0].charAt(0), workers[1] == null ? '.' : workers[1].charAt(0),workers[2] == null ? '.' : workers[2].charAt(0),workers[3] == null ? '.' : workers[3].charAt(0),workers[4] == null ? '.' : workers[4].charAt(0), solution.toString());
            for (int workerIndex = 0; workerIndex < numWorkers; workerIndex++) {
                //ignore idle worker
                if(workers[workerIndex] == null){
                    continue;
                }
                int timeLeft = Integer.parseInt(workers[workerIndex].substring(1));
                if(--timeLeft == 0){
                    if (link.containsKey(workers[workerIndex].charAt(0))) {
                        for (Character c : link.get(workers[workerIndex].charAt(0))) {
                            requiredCount.replace(c, requiredCount.get(c) - 1);
                        }
                    }
                    solution.append(workers[workerIndex].charAt(0));
                    workers[workerIndex] = null;
                } else {
                    workers[workerIndex] = workers[workerIndex].charAt(0) + String.valueOf(timeLeft);
                }
            }
        }
        return time;
    }
    static boolean workersDone(String[] arr){
        for (int i = 0; i < numWorkers; i++) {
            if(arr[i] != null){
                return false;
            }
        }
        return true;
    }
}