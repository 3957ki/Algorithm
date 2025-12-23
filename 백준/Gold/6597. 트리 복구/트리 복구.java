import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int now;
    static String pre, in;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String input = "";
        while((input = br.readLine()) != null && !(input.isEmpty())){
            StringTokenizer st = new StringTokenizer(input);
            pre = st.nextToken();
            in = st.nextToken();
            now = 0;

            func(0, pre.length()-1);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void func(int start, int end) {
        char preC = pre.charAt(now++);

        if(start == end) {
            sb.append(preC);
            return;
        }

        for(int i = start; i <= end; i++) {
            if(preC == in.charAt(i)) {
                if(start <= i-1) func(start, i-1);
                if(i+1 <= end) func(i+1, end);
                sb.append(preC);
                break;
            }
        }
    }
}