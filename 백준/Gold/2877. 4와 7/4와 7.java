import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        String str = Integer.toBinaryString(K+1);

        int L = str.length();

        for (int i = 1; i < L; i++) {
            char c = str.charAt(i);
            if (c == '0') sb.append('4');
            else sb.append('7');
        }

        System.out.println(sb);
    }
}