import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken()) + 2;
        long K = Long.parseLong(st.nextToken());

        long low = 1;
        long high = N/2;
        long mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            long value = mid * (N-mid);

            // k조각보다 많을 때
            if(value > K) high = mid - 1;

            // k조각보다 적을 때
            else if (value < K) low = mid + 1;

            // k조각과 일치할 때
            else {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
}