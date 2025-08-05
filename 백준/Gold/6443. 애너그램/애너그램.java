import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            String str = br.readLine();

            int[] arr = new int[26];
            int L = str.length();
            for(int j = 0; j < L; j++){
                arr[str.charAt(j) - 'a']++;
            }
            DFS(new StringBuilder(), 0, arr, L);
        }

        System.out.println(sb);
    }

    static void DFS(StringBuilder now, int cnt, int[] arr, int L){

        if(cnt == L){
            sb.append(now).append('\n');
            return;
        }

        for(int i = 0; i < 26; i++){
            if(arr[i] == 0) continue;
            arr[i]--;
            now.append((char)(i + 'a'));
            DFS(now, cnt + 1, arr, L);
            arr[i]++;
            now.deleteCharAt(now.length() - 1);
        }
    }
}