import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 먹을 수 있는 5000원 음식 개수
        int cnt = (M - 1000 * N) / 4000;

        int[][] arr = new int[N][2];

        // 이득인 5000원 음식 list
        List<Integer> list = new ArrayList<>();

        int answer = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][0] = Integer.parseInt(st.nextToken());

            if(arr[i][0] >= arr[i][1]) answer += arr[i][0];
            else list.add(i);
        }

        int L = list.size();

        if(L <= cnt){
			for (int idx : list) {
				answer += arr[idx][1];
			}
        }
        else{
            list.sort((o1, o2) -> (arr[o2][1] - arr[o2][0]) - (arr[o1][1] - arr[o1][0]));
            for(int i = 0; i < cnt; i++){
                int idx = list.get(i);
                answer += arr[idx][1];
            }
            for(int i = cnt; i < L; i++){
                int idx = list.get(i);
                answer += arr[idx][0];
            }
        }

        System.out.println(answer);
    }
}