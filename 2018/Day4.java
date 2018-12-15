import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
  static int part1() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day4.txt"));
    ArrayList<String> al = new ArrayList<String>();
    while (sc.hasNextLine()) {
      al.add(sc.nextLine());
    }
    al.sort((x, y) -> Integer.parseInt(x.split("-")[0].substring(1)) > Integer.parseInt(y.split("-")[0].substring(1)) ? -1 :
        Integer.parseInt(x.split("-")[0].substring(1)) > Integer.parseInt(y.split("-")[0].substring(1)) ? 1 :
            Integer.parseInt(x.split("-")[1]) < Integer.parseInt(y.split("-")[1]) ? -1 :
                Integer.parseInt(x.split("-")[1]) > Integer.parseInt(y.split("-")[1]) ? 1 :
                    Integer.parseInt(x.split("-")[2].split(" ")[0]) < Integer.parseInt(y.split("-")[2].split(" ")[0]) ? -1 :
                        Integer.parseInt(x.split("-")[2].split(" ")[0]) > Integer.parseInt(y.split("-")[2].split(" ")[0]) ? 1 :
                            Integer.parseInt(x.split(" ")[1].split(":")[0]) < Integer.parseInt(y.split(" ")[1].split(":")[0]) ? -1 :
                                Integer.parseInt(x.split(" ")[1].split(":")[0]) > Integer.parseInt(y.split(" ")[1].split(":")[0]) ? 1 :
                                    Integer.parseInt(x.split(":")[1].split("]")[0]) < Integer.parseInt(y.split(":")[1].split("]")[0]) ? -1 :
                                        Integer.parseInt(x.split(":")[1].split("]")[0]) > Integer.parseInt(y.split(":")[1].split("]")[0]) ? 1 : 0
    );
    int currGuard = 0;
    HashMap<Integer, Integer> hm = new HashMap<>();
    Pattern p = Pattern.compile(".*#(\\d+).*");
    for (int i = 0, alSize = al.size(); i < alSize; i++) {
      String s = al.get(i);
      if (s.contains("Guard")) {
        Matcher m = p.matcher(s);
        if (m.find())
          currGuard = Integer.parseInt(m.group(1));
      } else {
        hm.put(currGuard, (hm.containsKey(currGuard) ? hm.get(currGuard) : 0) + Integer.parseInt(al.get(++i).split(":")[1].split("]")[0]) - Integer.parseInt(s.split(":")[1].split("]")[0]));
      }
    }
    int guard = 0;
    int guardsleep = 0;
    for (int i : hm.keySet()) {
      if (hm.get(i) > guardsleep) {
        guardsleep = hm.get(i);
        guard = i;
      }
    }
    int guardSleepFreq[] = new int[60];
    for (int i = 0, alSize = al.size(); i < alSize; i++) {
      String s = al.get(i);
      if (s.contains("Guard #" + guard)) {
        do {
          for (int j = Integer.parseInt(al.get(++i).split(":")[1].split("]")[0]), endMinute = Integer.parseInt(al.get(++i).split(":")[1].split("]")[0]); j < endMinute; j++) {
            guardSleepFreq[j]++;
          }
        } while (!al.get(i + 1).contains("Guard "));
      }
    }
    int mostMin = 0;
    int mostFreq = 0;
    for (int i = 0; i < guardSleepFreq.length; i++) {
      if (guardSleepFreq[i] > mostFreq) {
        mostFreq = guardSleepFreq[i];
        mostMin = i;
      }
    }
    return mostMin * guard;
  }

  static int part2() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day4.txt"));
    ArrayList<String> al = new ArrayList<String>();
    while (sc.hasNextLine()) {
      al.add(sc.nextLine());
    }
    al.sort((x, y) -> Integer.parseInt(x.split("-")[0].substring(1)) > Integer.parseInt(y.split("-")[0].substring(1)) ? -1 :
        Integer.parseInt(x.split("-")[0].substring(1)) > Integer.parseInt(y.split("-")[0].substring(1)) ? 1 :
            Integer.parseInt(x.split("-")[1]) < Integer.parseInt(y.split("-")[1]) ? -1 :
                Integer.parseInt(x.split("-")[1]) > Integer.parseInt(y.split("-")[1]) ? 1 :
                    Integer.parseInt(x.split("-")[2].split(" ")[0]) < Integer.parseInt(y.split("-")[2].split(" ")[0]) ? -1 :
                        Integer.parseInt(x.split("-")[2].split(" ")[0]) > Integer.parseInt(y.split("-")[2].split(" ")[0]) ? 1 :
                            Integer.parseInt(x.split(" ")[1].split(":")[0]) < Integer.parseInt(y.split(" ")[1].split(":")[0]) ? -1 :
                                Integer.parseInt(x.split(" ")[1].split(":")[0]) > Integer.parseInt(y.split(" ")[1].split(":")[0]) ? 1 :
                                    Integer.parseInt(x.split(":")[1].split("]")[0]) < Integer.parseInt(y.split(":")[1].split("]")[0]) ? -1 :
                                        Integer.parseInt(x.split(":")[1].split("]")[0]) > Integer.parseInt(y.split(":")[1].split("]")[0]) ? 1 : 0
    );
    int currGuard = 0;
    HashMap<Integer, ArrayList<String>> hm = new HashMap<>();
    Pattern p = Pattern.compile(".*#(\\d+).*");
    for (int i = 0, alSize = al.size(); i < alSize; i++) {
      String s = al.get(i);
      if (s.contains("Guard")) {
        Matcher m = p.matcher(s);
        if (m.find())
          currGuard = Integer.parseInt(m.group(1));
        hm.putIfAbsent(currGuard, new ArrayList<String>());
        while (i + 1 < alSize && !al.get(i + 1).contains("Guard")) {
          hm.get(currGuard).add(al.get(++i));
          hm.get(currGuard).add(al.get(++i));
        }
      }
    }
    int mostMin = 0;
    int mostFreq = 0;
    int guardNum = 0;
    for (Integer guard : hm.keySet()) {
      int guardSleepFreq[] = new int[60];
      ArrayList<String> list = hm.get(guard);
      for (int i = 0, listSize = list.size(); i < listSize; i +=2) {
        for (int j = Integer.parseInt(list.get(i).split(":")[1].split("]")[0]), endMinute = Integer.parseInt(list.get(1 + i).split(":")[1].split("]")[0]); j < endMinute; j++) {
          if (++guardSleepFreq[j] > mostFreq) {
            mostFreq = guardSleepFreq[j];
            mostMin = j;
            guardNum = guard;
          }
        }
      }
    }
    return mostMin * guardNum;
  }
}
