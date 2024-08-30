import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};
    static boolean[] desert = new boolean[101];
    static int N;
    static int[][] arr;
    static int sum = 0;
    static int answer;
    static int startX, startY;
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for(int t = 1; t <= T; t++) {
            answer = -1;
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//          시작위치를 저장하고 함수를 통해 시계방향으로 돌것임.
            for(int i = 0; i < N-2; i++) {
                for(int j = 1; j < N-1; j++) {
                    startX = j;
                    startY = i;
                    sum++;
                    desert[arr[i][j]] = true;
                    func(i, j, 0);
                    desert[arr[i][j]] = false;
                    sum--;
                }
            }
            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }
         
        System.out.println(sb);
    }
     
    static void func(int y, int x, int d) {
//      오른쪽아래로 가거나, 왼쪽아래로 감
        if(d == 0) {
            if(y+dy[0] < N-1 && x+dx[0] <= N-1) {
                int Y = y+dy[0];
                int X = x+dx[0];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 0);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
            }
            if(y+dy[1] <= N-1 && x+dx[1] > 0) {
                int Y = y+dy[1];
                int X = x+dx[1];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 1);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
            }
        }
//      왼쪽아래로 가거나, 왼쪽 위로감
        else if(d == 1) {
            if(y+dy[1] <= N-1 && x+dx[1] > 0) {
                int Y = y+dy[1];
                int X = x+dx[1];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 1);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
            }
            if(y+dy[2] > 0 && x+dx[2] >= 0) {
                int Y = y+dy[2];
                int X = x+dx[2];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 2);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
            }
             
        }
//      왼쪽 위로가거나, 오른쪽 위로 감
        else if(d == 2) {
            if(y+dy[2] > 0 && x+dx[2] >= 0) {
                int Y = y+dy[2];
                int X = x+dx[2];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 2);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
            }
            if(y+dy[3] >= 0 && x+dx[3] < N-1) {
                int Y = y+dy[3];
                int X = x+dx[3];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 3);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
//              시작위치를 만나면 업데이트
                if(Y == startY && X == startX) {
                    answer = Math.max(answer, sum);
                }
            }
        }
//      오른쪽 위로만 감
        else if(d == 3) {
            if(y+dy[3] >= 0 && x+dx[3] < N-1) {
                int Y = y+dy[3];
                int X = x+dx[3];
                if(!desert[arr[Y][X]]) {
                    desert[arr[Y][X]] = true;
                    sum++;
                    func(Y, X, 3);
                    sum--;
                    desert[arr[Y][X]] = false;
                }
//              시작위치를 만나면 업데이트
                if(Y == startY && X == startX) {
                    answer = Math.max(answer, sum);
                }
            }
        }
    }
}