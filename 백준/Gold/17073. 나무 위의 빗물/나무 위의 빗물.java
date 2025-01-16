import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        double W = Double.parseDouble(st.nextToken());

        int[] counts = new int[N+1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            counts[start]++;
            counts[end]++;
        }

        int leaf = 0;
        for (int i = 2; i <= N; i++) {
            if(counts[i] == 1) leaf++;
        }

        System.out.println(W/leaf);
    }

}