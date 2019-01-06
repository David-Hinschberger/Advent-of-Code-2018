import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5 {
  static int part1() throws FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day5.txt"));
    StringBuilder sb = new StringBuilder(sc.nextLine());
    react(sb);
    System.out.println(sb);
    return sb.length();
  }
  //just need to try all a-z of p1 after removing 1 char
  static int part2() throws  FileNotFoundException {
    Scanner sc = new Scanner(new File("./2018inputs/day5.txt"));
    String original = sc.nextLine();
    int shortest = Integer.MAX_VALUE;
    for (int i = 'A'; i < 'Z' + 1; i++) {
      String regex = "[" +("" +(char) i) + ("" +((char) (i+32)))+ "]";
      String potentialSol = original.replaceAll(regex, "");
      StringBuilder sb = new StringBuilder(potentialSol);
      react(sb);
      if(sb.length() < shortest){
        shortest = sb.length();
      }
    }
    return shortest;
  }

  private static void react(StringBuilder sb) {
    int lenBefore;
    do{
      lenBefore = sb.length();
      int lenM1 = lenBefore - 1;
      for (int j = 0; j < lenM1; j++) {
        if(Math.abs(sb.charAt(j) - sb.charAt(j+1)) == 32){
          sb.delete(j, j+2);
          lenM1 -= 2;
        }
      }
    } while(lenBefore > sb.length());
  }
}
