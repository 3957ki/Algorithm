import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        TreeSet<Integer> set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            if(Integer.parseInt(st.nextToken()) == 1) set.add(i);
        }

        int pos = 0;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case 1:
                    int idx = Integer.parseInt(st.nextToken()) - 1;
                    if(set.contains(idx)) set.remove(idx);
                    else set.add(idx);
                    break;
                case 2:
                    int num = Integer.parseInt(st.nextToken());
                    pos = (pos + num) % N;
                    break;
                case 3:
                    Integer next = set.ceiling(pos);
                    if(next == null) next = set.ceiling(0);

                    sb.append(next == null ? -1 : (N + next - pos) % N).append('\n');
                    break;
            }
        }

        System.out.println(sb);
    }
}