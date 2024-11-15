import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Ml = Integer.parseInt(st.nextToken());
        int Mk = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        
        // ArrayDeque 사용 (LinkedList보다 빠름)
        Deque<Integer> noGunZone = new ArrayDeque<>();
        
        boolean possible = true;
        int zombieHp;
        
        for (int i = 0; i < L && possible; i++) {
            zombieHp = Integer.parseInt(br.readLine());
            
            // 기관총 사거리를 벗어난 지점 제거
            while (!noGunZone.isEmpty() && noGunZone.peekFirst() <= i - Ml) {
                noGunZone.pollFirst();
            }
            
            // 현재 위치에서의 실제 데미지 계산
            // noGunZone.size()는 기관총을 쏘지 않은 구간의 개수
            int damage = Mk * (Math.min(i + 1, Ml) - noGunZone.size());
            
            if (zombieHp > damage) {
                if (C > 0) {
                    C--;
                    noGunZone.offerLast(i);  // 수류탄 사용 위치 기록
                } else {
                    possible = false;
                }
            }
        }
        
        System.out.println(possible ? "YES" : "NO");
        br.close();
    }
}