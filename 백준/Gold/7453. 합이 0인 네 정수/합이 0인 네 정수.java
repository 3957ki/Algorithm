import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] CD;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

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

        CD = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                CD[i*N+j] = C[i] + D[j];
            }
        }

        Arrays.sort(CD);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = -(A[i] + B[j]);
                int low = lowerBound(value);
                int high = upperBound(value, low);

                if(low != Integer.MAX_VALUE && high != Integer.MIN_VALUE) {
                    answer += high - low + 1;
                }
            }
        }

        System.out.println(answer);
    }

    static int lowerBound(int value) {

        int low = 0;
        int high = N*N-1;
        int mid;
        int min = Integer.MAX_VALUE;

        while(low <= high) {
            mid = (low + high)/2;
            if(CD[mid] > value) {
                high = mid - 1;
            }
            else if(CD[mid] < value) {
                low = mid + 1;
            }
            else {
                min = Math.min(min, mid);
                high = mid - 1;
            }
        }

        return min;
    }

    static int upperBound(int value, int min) {
        int low = min;
        int high = N*N-1;
        int mid;
        int max = Integer.MIN_VALUE;

        while(low <= high) {
            mid = (low + high)/2;
            if(CD[mid] > value) {
                high = mid - 1;
            }
            else if(CD[mid] < value) {
                low = mid + 1;
            }
            else {
                max = Math.max(max, mid);
                low = mid + 1;
            }
        }

        return max;
    }

}