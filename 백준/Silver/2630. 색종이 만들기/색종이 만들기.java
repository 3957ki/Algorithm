import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int wCnt = 0;
    static int bCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        
        for(int i = 1; i <= N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 1; j <= N; j++) {
            	arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(arr[1][1] == 0) wCnt++;
    	if(arr[1][1] == 1) bCnt++;
        A : for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(arr[1][1] != arr[i][j]) {
                	if(arr[1][1] == 0) wCnt--;
                	if(arr[1][1] == 1) bCnt--;
                    func(1, 1, N, N);
                    break A;
                }
            }
        }
        System.out.println(wCnt);
        System.out.println(bCnt);
    }
    
    static void func(int y1, int x1, int y2, int x2) {
    	if(arr[y1][x1] == 0) wCnt++;
    	if(arr[y1][x1] == 1) bCnt++;
        A : for(int i = y1; i <= (y1+y2)/2; i++) {
            for(int j = x1; j <= (x1+x2)/2; j++) {
                if(arr[y1][x1] != arr[i][j]) {
                	if(arr[y1][x1] == 0) wCnt--;
                	if(arr[y1][x1] == 1) bCnt--;
                    func(y1, x1, (y1+y2)/2, (x1+x2)/2);
                    break A;
                }
            }
        }
        
        if(arr[(y1+y2)/2+1][x1] == 0) wCnt++;
    	if(arr[(y1+y2)/2+1][x1] == 1) bCnt++;
        B : for(int i = (y1+y2)/2+1; i <= y2; i++) {
            for(int j = x1; j <= (x1+x2)/2; j++) {
                if(arr[(y1+y2)/2+1][x1] != arr[i][j]) {
                	if(arr[(y1+y2)/2+1][x1] == 0) wCnt--;
                	if(arr[(y1+y2)/2+1][x1] == 1) bCnt--;
                    func((y1+y2)/2+1, x1, y2, (x1+x2)/2);
                    break B;
                }
            }
        }
        
        if(arr[y1][(x1+x2)/2+1] == 0) wCnt++;
    	if(arr[y1][(x1+x2)/2+1] == 1) bCnt++;
        C : for(int i = y1; i <= (y1+y2)/2; i++) {
            for(int j = (x1+x2)/2+1; j <= x2; j++) {
                if(arr[y1][(x1+x2)/2+1] != arr[i][j]) {
                	if(arr[y1][(x1+x2)/2+1] == 0) wCnt--;
                	if(arr[y1][(x1+x2)/2+1] == 1) bCnt--;
                    func(y1, (x1+x2)/2+1, (y1+y2)/2, x2);
                    break C;
                }
            }
        }
        
        if(arr[(y1+y2)/2+1][(x1+x2)/2+1] == 0) wCnt++;
    	if(arr[(y1+y2)/2+1][(x1+x2)/2+1] == 1) bCnt++;
        D : for(int i = (y1+y2)/2+1; i <= y2; i++) {
            for(int j = (x1+x2)/2+1; j <= x2; j++) {
                if(arr[(y1+y2)/2+1][(x1+x2)/2+1] != arr[i][j]) {
                	if(arr[(y1+y2)/2+1][(x1+x2)/2+1] == 0) wCnt--;
                	if(arr[(y1+y2)/2+1][(x1+x2)/2+1] == 1) bCnt--;
                    func((y1+y2)/2+1, (x1+x2)/2+1, y2, x2);
                    break D;
                }
            }
        }
        return;
    }
}