import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine()) != null){
            int X = Integer.parseInt(input)*10000000;
            int N = Integer.parseInt(br.readLine());

            Map<Integer, Integer> map = new HashMap<>();
            int[] arr = new int[N];

            int[] answer = new int[2];
            int maxDiff = -1;

            for (int i = 0; i < N; i++) {
                int block = Integer.parseInt(br.readLine());
                arr[i] = block;
                map.put(block, map.getOrDefault(block, 0) + 1);

                if(map.containsKey(X-block)) {
                    if(X - block == block && map.get(block) < 2) continue;
                    int diff = Math.abs(X-2*block);
                    if(diff > maxDiff) {
                        maxDiff = diff;
                        answer[0] = Math.min(X-block, block);
                        answer[1] = Math.max(X-block, block);
                    }
                }
            }

            if(maxDiff == -1) System.out.println("danger");
            else System.out.println("yes " + answer[0] + " " + answer[1]);
        }

    }
}