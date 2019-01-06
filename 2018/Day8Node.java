import java.util.ArrayList;

public class Day8Node {
    int queue;
    int metaNums;
    Integer value = null;
    ArrayList<Day8Node> children = new ArrayList<>();
    ArrayList<Integer> values = new ArrayList<>();
    Day8Node(int a, int b) {
        queue = a;
        metaNums = b;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("metaNums: " + metaNums);
        result.append(" metadata entries (values): ");
        for(Integer i: values){
            result.append(i + ", ");
        }
        if(value != null){
            result.append(" value: " + value);
        }
        return result.toString();
    }
}
