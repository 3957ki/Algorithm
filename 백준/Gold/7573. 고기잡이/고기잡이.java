import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int I = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Fish[] fish = new Fish[M];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            fish[i] = new Fish(y, x);
        }

        int answer = 0;

        for(Fish fy : fish){
            for(Fish fx : fish){
                for(int i = 1; i < I/2; i++){
                    // 그물 모양 가능
                    if((I - i*2) % 2 == 0 && I - i*2 > 0){
                        int j = (I - i*2) / 2;

                        // 그물 시작 위치
                        int y = fy.y;
                        int x = fx.x;

                        // 그물 끝 위치
                        int ny = y + i;
                        int nx = x + j;

                        // 그물 끝 지점이 경계를 지나고 위나 왼쪽으로 땡길 공간도 없으면 continue
                        if(ny > N) {
                            y -= (ny - N);
                            ny = N;
                            if(y <= 0) continue;
                        }
                        if(nx > N) {
                            x -= (nx - N);
                            nx = N;
                            if(x <= 0) continue;
                        }

                        int cnt = 0;
                        for(Fish k : fish){
                            // 그물 안에 있으면 잡기
                            if(k.y >= y && k.y <= ny && k.x >= x && k.x <= nx) cnt++;
                        }

                        answer = Math.max(answer, cnt);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static class Fish{
        int y, x;
        Fish(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}