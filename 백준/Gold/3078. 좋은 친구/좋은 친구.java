import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] counts = new int[21];

        for (int i = 0; i < K; i++) {
            int now = br.readLine().length();
            queue.add(now);
            counts[now]++;
        }

        long answer = 0;
        int L = N-K;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            counts[now]--;

            if(L-- > 0){
                int next = br.readLine().length();
                queue.add(next);
                counts[next]++;
            }

            answer += counts[now];
        }

        System.out.println(answer);
    }
}