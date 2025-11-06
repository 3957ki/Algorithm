class Solution {
    public long solution(int w, int h) {
        
        int cnt = GCD(w, h);
        int cut = w / cnt + h / cnt - 1;
        
        return (long)w * h - (long)cnt * cut;
    }
    
    private int GCD(int a, int b){
        if(a < b){
            int temp = a;
            a = b;
            b = temp;
        }
        
        while(b > 0){
            int val = a % b;
            a = b;
            b = val;
        }
        
        return a;
    }
}