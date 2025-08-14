class Solution {
    public int solution(int[] a) {
        
        int N = a.length;
        int[] left = new int[N];
        int[] right = new int[N];
        
        left[0] = a[0];
        right[N-1] = a[N-1];
        
        for(int i = 1; i < N; i++){
            left[i] = Math.min(a[i], left[i-1]);
            right[N-1-i] = Math.min(a[N-1-i], right[N-i]);
        }
        
        int answer = N;
        for(int i = 1; i < N-1; i++){
            if(left[i-1] < a[i] && right[i+1] < a[i]) answer--;
        }
        
        return answer;
    }
}