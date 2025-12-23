import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] list = new List[N+1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            while(cnt-- > 0){
                int num = Integer.parseInt(st.nextToken());
                list[i].add(num);
                list[num].add(i);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];

        PriorityQueue<Integer>[] team = new PriorityQueue[2];
        team[0] = new PriorityQueue<>((o1, o2) -> o1 - o2);
        team[1] = new PriorityQueue<>((o1, o2) -> o1 - o2);

        for(int i = 1; i <= N; i++){
            if(visited[i]) continue;
            visited[i] = true;

            int teamNum = 0;
            team[0].add(i);
            queue.add(i);
            while(!queue.isEmpty()){
                teamNum ^= 1;
                int L = queue.size();

                while(L-- > 0){
                    int now = queue.poll();
                    for(int next : list[now]){
                        if(visited[next]) continue;
                        visited[next] = true;
                        team[teamNum].add(next);
                        queue.add(next);
                    }
                }
            }
        }

        for(int i = 0; i < 2; i++){
            sb.append(team[i].size()).append('\n');
            while(!team[i].isEmpty()){
                sb.append(team[i].poll()).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}