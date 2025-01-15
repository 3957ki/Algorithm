import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited = new boolean[11];
    static List<Integer>[] position;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            max = 0;
            position = new List[11];
            for (int i = 0; i < 11; i++) {
                position[i] = new ArrayList<>();
            }

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    position[j].add(num);
                }
            }

            DFS(0, 0);
            sb.append(max).append('\n');
        }
        System.out.println(sb);
    }

    // num번 포지션
    static void DFS(int num, int sum) {

        if(num == 11){
            max = Math.max(max, sum);
            return;
        }

        // i번째 선수
        for (int i = 0; i < 11; i++) {
            if(visited[i] || position[num].get(i) == 0) continue;
            visited[i] = true;
            DFS(num+1, sum+position[num].get(i));
            visited[i] = false;
        }
    }
}