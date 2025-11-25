import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[T+1];
        for(int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0,0, W));
        int[][][] visited = new int[T+1][W+1][2];
        for(int i = 1; i <= T; i++) {
            for(int j = 0; j <= W; j++) {
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }

        int dst = 1;
        while(!queue.isEmpty()) {
            int L = queue.size();
            while(L-- > 0) {
                Node now = queue.poll();
                int sum = now.sum;
                sum += arr[dst] == now.pos ? 1 : 0;
                if(visited[dst][now.cnt][now.pos] < sum) {
                    visited[dst][now.cnt][now.pos] = sum;
                    queue.add(new Node(now.pos, sum, now.cnt));
                }

                // 이동 가능
                if(now.cnt > 0){
                    int pos = now.pos ^ 1;
                    sum = now.sum;
                    sum += arr[dst] == now.pos ? 0 : 1;
                    int cnt = now.cnt - 1;

                    if(visited[dst][cnt][pos] < sum) {
                        visited[dst][cnt][pos] = sum;
                        queue.add(new Node(pos, sum, cnt));
                    }
                }


            }

            if(dst++ == T) break;
        }

        int answer = 0;
        for(int i = 0; i <= W; i++){
            answer = Math.max(answer, Math.max(visited[T][i][0], visited[T][i][1]));
        }

        System.out.println(answer);
    }

    static class Node {
        int pos, sum, cnt;
        Node(int pos, int sum, int cnt) {
            this.pos = pos;
            this.sum = sum;
            this.cnt = cnt;
        }
    }
}