import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) ->
            o1.num == o2.num ? o1.index - o2.index : o1.num - o2.num);

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            pq.add(new Node(arr[i], i));
        }

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                int index = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                pq.add(new Node(num, index));
                arr[index] = num;
            }
            else{
                while(!pq.isEmpty()){
                    Node node = pq.poll();
                    if(node.num == arr[node.index]){
                        sb.append(node.index).append('\n');
                        pq.add(node);
                        break;
                    }
                }
            }
        }

        System.out.println(sb);
    }

    static class Node{
        int num, index;

        public Node(int num, int index){
            this.num = num;
            this.index = index;
        }
    }
}