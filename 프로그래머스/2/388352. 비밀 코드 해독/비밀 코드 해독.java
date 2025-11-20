class Solution {
    
    private int N, M;
    private int[] qBit;
    private int[] ans;
    
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        M = q.length;
        qBit = new int[M];
        this.ans = ans;
        
        for(int i = 0; i < M; i++){
            int bit = 0;
            
            for(int num : q[i]){
                bit |= 1 << num;
            }
            qBit[i] = bit;
        }
        
        return comb(1, 0, 0);
    }
    
    private int comb(int now, int cnt, int status){
        if(cnt == 5){
            for(int i = 0; i < M; i++){
                if(Integer.bitCount(status & qBit[i]) != ans[i]) return 0;
            }
            return 1;
        }
        
        int sum = 0;
        for(int i = now; i <= N; i++){
            sum += comb(i + 1, cnt + 1, 1 << i | status);
        }
        
        return sum;
    }
}