import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        K: for(int a = 1; a < 100000; a++) {
            int b = N - a;
            if(b <= 0) continue;
            String A = String.valueOf(a);
            String B = String.valueOf(b);
            if(A.length() + B.length() > 10) continue;
            Set<Character> set = new HashSet<>();
            for(int i = 0; i < A.length(); i++){
                if(set.contains(A.charAt(i))) continue K;
                set.add(A.charAt(i));
            }
            for(int i = 0; i < B.length(); i++){
                if(set.contains(B.charAt(i))) continue K;
                set.add(B.charAt(i));
            }
            System.out.println(A+" + " + B);
            System.exit(0);
        }
        System.out.println(-1);
    }

}