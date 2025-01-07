import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();

        Stack<Node> stack = new Stack<>();

        int cur = 0;
        int prev = 0;
        for (int i = 0; i < N; i++) {
            char c = str.charAt(i);
            if(c == '('){
                stack.push(new Node(cur-1, prev));
                cur = 0;
            }
            else if(c == ')'){
                Node top = stack.pop();
                cur = top.length+cur*top.prev;
            }
            else {
                prev = Integer.parseInt(c+"");
                cur++;
            }
        }
        System.out.println(cur);
    }

    static class Node{
        int length, prev;

        public Node(int length, int prev) {
            this.length = length;
            this.prev = prev;
        }
    }
}