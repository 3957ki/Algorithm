import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();

        int answer = 0;

        for (int s = 0; s < N; s++) {
            String pattern = str.substring(s, N);
            int n = pattern.length();
            int[] pi = new int[n];
            int j = 0;

            for (int i = 1; i < n; i++) {
                while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                    j = pi[j - 1];
                }

                if (pattern.charAt(i) == pattern.charAt(j)) {
                    j++;
                    pi[i] = j;
                    answer = Math.max(answer, j);
                }
            }
        }

        System.out.println(answer);
    }

}