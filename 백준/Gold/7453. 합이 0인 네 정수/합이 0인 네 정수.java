import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[i*N+j] = A[i] + B[j];
            }
        }

        int[] CD = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                CD[i*N+j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        long answer = 0;
        int left = 0;
        int right = N*N-1;

        while (left < N*N && right >= 0){

            int value1 = AB[left];
            long value1Cnt = 0;

            while(left < N*N && AB[left] == value1){
                value1Cnt++;
                left++;
            }

            int value2 = -value1;
            long value2Cnt = 0;

            while(right >= 0 && CD[right] > value2) right--;

            while(right >= 0 && CD[right] == value2){
                value2Cnt++;
                right--;
            }

            answer += value1Cnt * value2Cnt;
        }

        System.out.println(answer);
    }

}