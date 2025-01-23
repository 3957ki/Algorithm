import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int N;
    static String[] arr;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        N = str.length();
        arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = str.substring(i, i + 1);
        }

        Node root = new Node();
        for (int i = 0; i < N; i++) {
            DFS(root, arr[i], i-1, i+1, false);
        }

        System.out.println(answer);

    }

    static void DFS(Node now, String num, int left, int right, boolean isValid) {

        if(!now.map.containsKey(num)) {
            now.map.put(num, new Node());
            isValid = true;
        }

        if(left < 0 && right >= N && isValid) answer++;

        else{
            if(left >= 0) {
                DFS(now.map.get(num), arr[left]+num, left-1, right, isValid);
            }

            if(right < N) {
                DFS(now.map.get(num), num+arr[right], left, right+1, isValid);
            }
        }
    }

    static class Node {
        Map<String, Node> map = new HashMap<>();
    }
}