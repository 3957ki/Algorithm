import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] lazy, seg, arr;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        
        arr = new long[N+1];
        seg = new long[N*4];
        lazy = new long[N*4];
        
        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        init(1, N, 1);
        
        for(int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            
            if(cmd == 1) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update(1, N, 1, a, b, d);
            }
            else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(find(1, N, 1, a, b)).append('\n');
            }
        }
        System.out.println(sb);
    }

    static void propagation(int start, int end, int index) {
//        갱신된 값이 있다면
        if(lazy[index] != 0) {
            seg[index] += lazy[index]*(end-start+1);
            
//            자식이 있다면
            if(start != end) {
                lazy[index<<1]+=lazy[index];
                lazy[index<<1|1]+=lazy[index];
            }
            lazy[index] = 0;
        }
    }
    
    static long init(int start, int end, int index) {
        if(start == end) return seg[index] = arr[start];
        int mid = (start+end)/2;
        return seg[index] = init(start, mid, index<<1) + init(mid+1, end, index<<1|1);
    }
    
    static void update(int start, int end, int index, int left, int right, long diff) {
        propagation(start, end, index);
        if(end < left || start > right) return;
        if(start >= left && end <= right) {
            seg[index] += diff*(end-start+1);
            if(start != end) {
                lazy[index<<1] += diff;
                lazy[index<<1|1] += diff;
            }
            return;
        }
        
        int mid = (start+end)/2;
        update(start, mid, index<<1, left, right, diff);
        update(mid+1, end, index<<1|1, left, right, diff);
        seg[index] = seg[index<<1] + seg[index<<1|1];
    }
    
    static long find(int start, int end, int index, int left, int right) {
        propagation(start, end, index);
        if(end < left || start > right) return 0;
        if(start >= left && end <= right) return seg[index];
        int mid = (start+end)/2;
        return find(start, mid, index<<1, left, right)+find(mid+1, end, index<<1|1, left, right);
    }
    
    
}