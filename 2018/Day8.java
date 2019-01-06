import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * Tree would probably be easiest to solve this, but probably less efficient
 */
public class Day8 {

    static int part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./2018inputs/day8.txt"));
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> numsToAdd = new Stack<>();
        int sum = 0;
        while (sc.hasNextInt()) {
            if(stack.size() > 0 && stack.peek() == 0){
                int addCount = numsToAdd.pop();
                for (int i = 0; i < addCount; i++) {
                    sum += sc.nextInt();
                }
                stack.pop();
                if(stack.size() > 0) {
                    stack.push(stack.pop() - 1);
                    continue;
                } else{
                    break;
                }
            }
            stack.push(sc.nextInt());
            numsToAdd.push(sc.nextInt());
        }
        return sum;
    }

    static int part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./2018inputs/day8.txt"));
        Stack<Day8Node> stack = new Stack<>();
        ArrayList<Day8Node> allNodes = new ArrayList<>();
        int sum = 0;
        int i = 0;
        while (sc.hasNextInt()) {
            if(stack.size() > 0 && stack.peek().queue == 0){
                Day8Node curr = stack.pop();
                for (int j = 0; j < curr.metaNums; j++) {
                    curr.values.add(sc.nextInt());
                }
                if(stack.size() > 0) {
                    stack.peek().queue--;
                    stack.peek().children.add(curr);
                } else{
                    break;
                }
            } else {
                stack.push(new Day8Node(sc.nextInt(), sc.nextInt()));
                allNodes.add(stack.peek());
            }
        }
        return calcSum(allNodes.get(0));
    }

    static int calcSum(Day8Node n){
        if(n.value != null){
            return n.value;
        }
        if(n.children.size() == 0){
            n.value = n.values.stream().mapToInt(Integer::intValue).sum();
        } else {
            n.value = 0;
            for (int i = 0; i < n.values.size(); i++) {
                int index = n.values.get(i);
                if(index > n.children.size()){
                    continue;
                }
                n.value += calcSum(n.children.get(index -1));
            }
        }
        return n.value;
    }
}
