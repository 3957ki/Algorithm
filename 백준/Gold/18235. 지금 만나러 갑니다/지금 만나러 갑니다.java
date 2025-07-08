import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[N+1];  // 오리
        int[] arr2 = new int[N+1];  // 육리
        Arrays.fill(arr1, Integer.MAX_VALUE);
        Arrays.fill(arr2, Integer.MAX_VALUE);
        arr1[A] = 0;
        arr2[B] = 0;

        // BFS
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(arr1, A));
        queue.add(new Node(arr2, B));

        int day = 0;    // 날짜
        while(!queue.isEmpty()){
            int L = queue.size();


            while(L-- > 0){
                Node now = queue.poll();

                int left = now.pos - (1 << day);
                int right = now.pos + (1 << day);

                // 왼쪽 가능
                if(left > 0){
                    now.arr[left] = day+1;
                    queue.add(new Node(now.arr, left));

                    if(arr1[left] == arr2[left]){
                        System.out.println(day+1);
                        return;
                    }
                }

                // 오른쪽 가능
                if(right <= N){
                    now.arr[right] = day+1;
                    queue.add(new Node(now.arr, right));

                    if(arr1[right] == arr2[right]){
                        System.out.println(day+1);
                        return;
                    }
                }
            }
            day++;

        }

        System.out.println(-1);
    }

    static class Node{
        int[] arr;
        int pos;

        public Node(int[] arr, int pos) {
            this.arr = arr;
            this.pos = pos;
        }
    }

}