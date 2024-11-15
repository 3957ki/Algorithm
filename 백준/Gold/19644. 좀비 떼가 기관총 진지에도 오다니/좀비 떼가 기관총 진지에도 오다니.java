import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Ml = Integer.parseInt(st.nextToken());
        int Mk = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        
//      수류탄 사용 위치
        Queue<Integer> noGunZone = new ArrayDeque<>();
        
        for (int i = 0; i < L; i++) {
            int zombieHp = Integer.parseInt(br.readLine());
            
            // 기관총 사거리를 벗어난 지점 제거
            while (!noGunZone.isEmpty() && noGunZone.peek() <= i - Ml) {
                noGunZone.poll();
            }
            
            // 현재 위치에서의 실제 데미지 계산
            int damage = Mk * (Math.min(i + 1, Ml) - noGunZone.size());
            
            if (zombieHp > damage) {
                if (C-- > 0) {
                    noGunZone.add(i);  // 수류탄 사용 위치 기록
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        
        System.out.println("YES");
    }
}