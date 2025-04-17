import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] HI = new int[N];
        int[] ARC = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            HI[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            ARC[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(HI);
        Arrays.sort(ARC);

        long win = 0;
        long defeat = 0;
        long draw = 0;

        for (int i = 0; i < N; i++) {
            int low = 0;
            int high = M - 1;
            int mid;
            int max = -1;

            while (low <= high) {
                mid = (low + high) >> 1;

                // 승리
                if (ARC[mid] < HI[i]) {
                    low = mid + 1;
                    max = Math.max(max, mid);
                }
                // 패배, 무승부
                else if (ARC[mid] >= HI[i]) {
                    high = mid - 1;
                }
            }

            // 승수
            win += max + 1;

            low = max + 1;
            high = M - 1;
            int min = M;

            while (low <= high) {
                mid = (low + high) >> 1;

                // 무승부
                if (ARC[mid] == HI[i]) {
                    low = mid + 1;
                }
                // 패배
                else {
                    min = Math.min(min, mid);
                    high = mid - 1;
                }
            }

            // 패배수
            defeat += M - min;
        }

        draw = (long) N * M - win - defeat;

        sb.append(win).append(' ').append(defeat).append(' ').append(draw);
        System.out.println(sb);
    }
}