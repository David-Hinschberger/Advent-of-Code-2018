import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Day3 {

  static int part1() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day3.txt"));
    int[][] arr = new int[10000][10000];
    String line;
    int totalcount = 0;
    while (sc.hasNextLine()) {
      line = sc.nextLine();
      String brokenLine[] = line.split(" ");
      int startx = Integer.parseInt(brokenLine[2].split(",")[0]);
      int starty = Integer.parseInt(brokenLine[2].split(",")[1].split(":")[0]);
      int x = Integer.parseInt(brokenLine[3].split("x")[0]);
      int y = Integer.parseInt(brokenLine[3].split("x")[1]);
      for (int i = startx; i < startx + x; i++) {
        for (int j = starty; j < starty + y; j++) {
          if (arr[i][j] == 1)
            totalcount++;
          arr[i][j]++;
        }
      }
    }
    return totalcount;
  }

  static int part2() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day3.txt"));
    int[][] arr = new int[10000][10000];
    String line;
    HashSet<Integer> hs = new HashSet<Integer>();
    while (sc.hasNextLine()) {
      line = sc.nextLine();
      String brokenLine[] = line.split(" ");
      int startx = Integer.parseInt(brokenLine[2].split(",")[0]);
      int starty = Integer.parseInt(brokenLine[2].split(",")[1].split(":")[0]);
      int x = Integer.parseInt(brokenLine[3].split("x")[0]);
      int y = Integer.parseInt(brokenLine[3].split("x")[1]);
      //add id
      hs.add(Integer.parseInt(brokenLine[0].split("#")[1]));
      for (int i = startx; i < startx + x; i++) {
        for (int j = starty; j < starty + y; j++) {
          //if hs contains id, remove
          if (arr[i][j] > 0) {
            hs.remove(arr[i][j]);
            hs.remove(Integer.parseInt(brokenLine[0].split("#")[1]));
          }
          arr[i][j] = Integer.parseInt(brokenLine[0].split("#")[1]);
        }
      }
    }
    Iterator iter = hs.iterator();
    return Integer.parseInt(iter.next().toString());
  }
}
