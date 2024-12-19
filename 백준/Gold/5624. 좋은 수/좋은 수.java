import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        HashSet<Integer> set = new HashSet<>();
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());

            for(int j = 0; j < i; j++){
                if(set.contains(arr[i] - arr[j])) {
                    answer++;
                    break;
                }
            }

            for(int j = 0; j <= i; j++){
                set.add(arr[j]+arr[i]);
            }

        }

        System.out.println(answer);
    }

}