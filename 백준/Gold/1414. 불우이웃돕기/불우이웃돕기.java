import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Node;

public class Main {

    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        parent = new int[N];
        Arrays.fill(parent, -1);

        List<Node> edges = new ArrayList<>();

        int total = 0;

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                char ch = str.charAt(j);
                int cost = 0;
                if(ch == '0') continue;
                else if(Character.isLowerCase(ch)){
                    cost = ch - 'a' + 1;
                }
                else{
                    cost = ch - 'A' + 27;
                }
                total += cost;
                edges.add(new Node(i, j, cost));
            }
        }

        if(N == 1) {
            System.out.println(total);
            return;
        }

        edges.sort((o1, o2) -> o1.cost - o2.cost);
        int cnt = 0;
        for(Node now : edges){
            if(union(now.start, now.end)){
                total -= now.cost;
                if(++cnt == N-1) {
                    System.out.println(total);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    static class Node{
        int start, end, cost;
        public Node(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int find(int a){
        if(parent[a] < 0) return a;
        return parent[a] = find(parent[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parent[aRoot] += parent[bRoot];
        parent[bRoot] = aRoot;
        return true;
    }
}