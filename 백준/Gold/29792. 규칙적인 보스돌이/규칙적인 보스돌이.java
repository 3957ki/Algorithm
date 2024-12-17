import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

//      캐릭터별 데미지
        long[] characters = new long[N];
        for (int i = 0; i < N; i++) {
            characters[i] = Long.parseLong(br.readLine());
        }

//      보스 리스트
        Boss[] boss = new Boss[K];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            long hp = Long.parseLong(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            boss[i] = new Boss(hp, value);
        }

        List<Integer> list = new ArrayList<Integer>();

        for(long dmg : characters){
            int[] dp = new int[901];
            for(Boss b : boss){
                long cnt = (long)((b.hp+dmg-1)/dmg);
                for(int i = 900; i >= cnt; i--){
                    dp[i] = Math.max(dp[i], dp[i-(int)cnt]+b.value);
                }
            }
            list.add(dp[900]);
        }

        Collections.sort(list, (o1, o2) -> o2-o1);
        int answer = 0;
        for(int i = 0; i < M; i++){
            answer += list.get(i);
        }

        System.out.println(answer);

    }

    static class Boss{
        long hp;
        int value;

        public Boss(long hp, int value) {
            this.hp = hp;
            this.value = value;
        }
    }
}