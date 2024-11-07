import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, parents[];

    static void make() {
        parents = new int[n];
        Arrays.fill(parents, -1);
    }

    static int findset(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = findset(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findset(a);
        int bRoot = findset(b);
        if( aRoot == bRoot ) return false;

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        make();

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a, b)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}