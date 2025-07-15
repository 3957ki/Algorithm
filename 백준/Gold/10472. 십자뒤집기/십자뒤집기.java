import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int bit = 0;
        int[] dx = {-1, 1, 0, 0, 0};
        int[] dy = {0, 0, 0, -1, 1};

        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[1<<9];
        Arrays.fill(visited, Integer.MAX_VALUE);
        queue.add(0);
        visited[0] = 0;

        int cnt = 0;
        while(!queue.isEmpty()){
            cnt++;
            int L = queue.size();

            while(L-- > 0){
                int now = queue.poll();

                for(int i = 0; i < 9; i++){
                    int next = now;
                    int iy = i / 3;
                    int ix = i % 3;

                    for(int j = 0; j < 5; j++){
                        int ny = iy + dy[j];
                        int nx = ix + dx[j];
                        if(ny >= 0 && ny < 3 && nx >= 0 && nx < 3){
                            next ^= (1 << (ny * 3 + nx));
                        }
                    }
                    if(visited[next] > cnt){
                        visited[next] = cnt;
                        queue.add(next);
                    }
                }
            }
        }

        while (T-- > 0) {
            bit = 0;

            for(int i = 0; i < 3; i++){
                String str = br.readLine();
                for(int j = 0; j < 3; j++){
                    bit = bit << 1;
                    if(str.charAt(j) == '*') bit |= 1;
                }
            }

            sb.append(visited[bit]).append('\n');
        }

        System.out.println(sb);
    }

}