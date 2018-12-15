import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {

  static String part2() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day2"));
    ArrayList<String> input = new ArrayList<String>();
    while(sc.hasNextLine()){
      input.add(sc.nextLine());
    }
    String box1 = "";
    String box2 = "";
    firstLoop:
    for (int i = 2; i < input.size(); i++) {
      for (int j = i+1; j < input.size(); j++) {
        int diff = 0;
        for (int k = 0; k < input.get(i).length() && diff < 2; k++) {
          if(input.get(i).charAt(k) != input.get(j).charAt(k)){
            diff++;
          }
        }
        if(diff == 1){
          box1 = input.get(i);
          box2 = input.get(j);
          break firstLoop;
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < box1.length(); j++) {
      if(box1.charAt(j) == box2.charAt(j))
        sb.append(box1.charAt(j));
    }
    return sb.toString();
  }

  static int part1() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day2"));
    int twosCount = 0;
    int threesCount = 0;
    String line;
    while (sc.hasNextLine()) {
      line = sc.nextLine();
      HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
      for (int i = 0; i < line.length(); i++) {
        hm.put(line.charAt(i), hm.containsKey(line.charAt(i)) ? hm.get(line.charAt(i)) + 1 : 1);
      }
      boolean twoCounted = false;
      boolean threeCounted = false;
      for (Integer val : hm.values()) {
        switch (val) {
          case 2:
            if (!twoCounted) {
              twosCount++;
              twoCounted = !twoCounted;
            }
            break;
          case 3:
            if (!threeCounted) {
              threesCount++;
              threeCounted = !threeCounted;
            }
        }
      }
    }
    return twosCount * threesCount;
  }
}
